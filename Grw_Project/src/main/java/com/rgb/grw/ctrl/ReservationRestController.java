package com.rgb.grw.ctrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgb.grw.dto.ReservationDto;
import com.rgb.grw.dto.UserInfoDto;
import com.rgb.grw.service.IReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationRestController {
	
	private final IReservationService service;
	
	/**
	 * 전체 예약목록을 조회 페이징 처리하여 관리자 조회
	 * @param session HTTP 세션에서 관리자 권한과 empno를 받아옴
	 * @param startDate 시작날짜
	 * @param endDate 끝 날짜
	 * @param page 현재 페이지
	 * @param countList 페이지당 게시글 수 
	 * @return 페이징된 예약 목록
	 */
	@GetMapping(value="/booklist/facility.do")
	public List<ReservationDto> booklist (HttpSession session) {
		
		String empno = ((UserInfoDto) session.getAttribute("loginDto")).getEmp_no();
		String auth = ((UserInfoDto) session.getAttribute("loginDto")).getAuth_no();
		
		
		Calendar today = Calendar.getInstance();
		//오늘 날짜
		Date startDate = today.getTime();
		//12일 후 날짜
		today.add(Calendar.DAY_OF_MONTH, 12);
		Date endDate = today.getTime();
		log.info("날짜 확인용",startDate, endDate );
		
		//params
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bk_empno", empno);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		
		
		List<ReservationDto> booklist = new ArrayList<ReservationDto>();
		if(auth.equals("FC00A")) {

			booklist = service.getBook(params);
		
		} else {
			booklist = service.getBookUser(params);
		}
		
		
		return booklist; 
	}
	
	
	/**
	 * 페이징 처리없이 날짜값만으로 예약전체정보가져오기
	 * @param session empno 값 받기
	 * @param startDate 오늘
	 * @param endDate 12일 후
	 * @return
	 */
	@GetMapping(value="/bookalllist/facility.do")
	public List<ReservationDto> bookalllist (HttpSession session, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		
		
	    // 세션에서 loginDto 가져오기
	    UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");
	    
	    String sessionEmp = loginDto.getEmp_no();
	    String sessionauth = loginDto.getAuth_no();
	    log.info("sessionEmp: {}", sessionEmp);
	    
	   
		Map<String, Object> bmap = new HashMap<String, Object>();
		bmap.put("startDate", startDate);
		bmap.put("endDate", endDate);
		
		log.info("GetAMapping 매핑 실행{}", bmap);
		
		//예약목록 조회
		List<ReservationDto> list = service.getAllBook(bmap);
		
		
		for(ReservationDto dto : list) {
			
			String stday = dto.getBk_stday().replace(" ", "T");
			dto.setBk_stday(stday);
			
			String edday = dto.getBk_edday().replace(" ", "T");
			dto.setBk_edday(edday);
			
			String regday = dto.getBk_regdate().replace(" ", "T");
			dto.setBk_regdate(regday);
			
			dto.setSessionEmp(sessionEmp);
			
			dto.setSessionauth(sessionauth);
			
			if(!(dto.getBk_apday()==null)) {
				
				String apday = dto.getBk_apday().replace(" ", "T");
				dto.setBk_apday(apday);
			}
			
		}
		
		log.info("전체 일정 리스트 {}", list);
		
		return list;
	}
	
	/**
	 * 자산예약신청하기
	 * @param map (fc_no,bk_empno,bk_title,bk_content,bk_stday,bk_edday,bk_name,bk_dep)
	 * @return 성공하면 true 실패하면 false
	 */
	@PostMapping(value="/addreservation/facility.do")
	public ResponseEntity<List<ReservationDto>> insertReservation(HttpSession session, @RequestBody Map<String, Object>map) {
		
		Calendar today = Calendar.getInstance();
		//오늘 날짜
		Date startDate = today.getTime();
		//12일 후 날짜
		today.add(Calendar.DAY_OF_MONTH, 12);
		Date endDate = today.getTime();
		
		UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");
		
		String bk_empno = loginDto.getEmp_no();
		String bk_name = loginDto.getEmp_name();
		String bk_dep = loginDto.getDep_no();
		String auth = loginDto.getAuth_no();
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		
		rmap.put("bk_empno", bk_empno);
		rmap.put("bk_name", bk_name);
		rmap.put("bk_dep", bk_dep);
		rmap.put("fc_no", map.get("fc_no"));
		rmap.put("bk_content", map.get("bk_content"));
		rmap.put("bk_title", map.get("bk_title"));
		rmap.put("bk_stday", map.get("bk_stday"));
		rmap.put("bk_edday", map.get("bk_edday"));
		
		//insert전 동일 시간대에 예약기록있는지 체크
		boolean insertSuccess = service.insertReservation(rmap);
		
		if(!insertSuccess) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			Map<String, Object> bookmap = new HashMap<String, Object>();
			List<ReservationDto> booklist = new ArrayList<ReservationDto>();
			bookmap.put("bk_empno", bk_empno);
			bookmap.put("startDate", startDate);
			bookmap.put("endDate", endDate);
			bookmap.put("bk_auth", auth);
			
			if(auth == "FC00A") {
				booklist = service.getBook(bookmap);
			} else {
				booklist = service.getBookUser(bookmap);
			}
			
			log.info("booklist:{}", booklist);
			return ResponseEntity.ok(booklist);
			
		}
		
	}
	
	//관리자 승인 / 반려
	@GetMapping(value="/approve/facility.do")
	public List<ReservationDto> approve(HttpSession session, @RequestParam Map<String, Object> map) {
		String se_name = ((UserInfoDto) session.getAttribute("loginDto")).getEmp_name();
		
		Calendar today = Calendar.getInstance();
		//오늘 날짜
		Date startDate = today.getTime();
		//12일 후 날짜
		today.add(Calendar.DAY_OF_MONTH, 12);
		Date endDate = today.getTime();
		
		Map<String, Object> updateparams = new HashMap<String, Object>();
		updateparams.put("bk_state", map.get("bk_state"));
		updateparams.put("bk_no", map.get("bk_no"));
		updateparams.put("se_name", se_name);
		log.info("params",updateparams);
		boolean result =service.updateBook(updateparams);
		
		Map<String, Object> reSelectMap = new HashMap<String, Object>();
		reSelectMap.put("startDate", startDate);
		reSelectMap.put("endDate", endDate);
		
		List<ReservationDto> list = new ArrayList<ReservationDto>();
		list = service.getBook(reSelectMap);
		
		return list;
	}
	
	//사용자 취소
	@GetMapping(value="/cancel/facility.do")
	public List<ReservationDto> cancel(HttpSession session, @RequestParam Map<String, Object> map) {
		String sessionEmpno = ((UserInfoDto) session.getAttribute("loginDto")).getEmp_no();
		
		Calendar today = Calendar.getInstance();
		//오늘 날짜
		Date startDate = today.getTime();
		//12일 후 날짜
		today.add(Calendar.DAY_OF_MONTH, 12);
		Date endDate = today.getTime();
		
		Map<String, Object> cancelParams = new HashMap<String, Object>();
		cancelParams.put("bk_no", map.get("bk_no"));
		log.info("params",cancelParams);
		
		boolean result =service.cancelBook(cancelParams);
		log.info("사용자 취소 : {}",result);
		
		Map<String, Object> reSelectMap = new HashMap<String, Object>();
		reSelectMap.put("startDate", startDate);
		reSelectMap.put("endDate", endDate);
		reSelectMap.put("bk_empno", sessionEmpno);
		
		List<ReservationDto> list = new ArrayList<ReservationDto>();
		list = service.getBookUser(reSelectMap);
		
		return list;
	}
}



