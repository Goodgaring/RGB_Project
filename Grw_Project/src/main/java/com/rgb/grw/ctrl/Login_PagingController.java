package com.rgb.grw.ctrl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rgb.grw.dto.AttendanceDto;
import com.rgb.grw.dto.UserInfoDto;
import com.rgb.grw.service.IAttendanceService;

@Controller
public class Login_PagingController {

	@Autowired
	private IAttendanceService attendanceService;

	// 서버 시작 페이지
	@RequestMapping("/")
	public ModelAndView start() {
		return new ModelAndView("login/login");
	}

	// 메인화면으로 이동
	@GetMapping(value = "/home.do")
	public String home(Model model, HttpSession session) {
		UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");
		if (loginDto == null) {
			// 로그인 정보가 없으면 로그인 페이지로 리다이렉트
			return "/login/login";
		}
		String empNo = loginDto.getEmp_no();
		Date now = new Date();

		// 단일 객체 조회
		AttendanceDto todayAttendance = attendanceService.selectTodayAttendanceByEmpNo(empNo);
		model.addAttribute("todayAttendance", todayAttendance);

		List<AttendanceDto> monthlyAttendance = attendanceService.selectMonthlyAttendanceByEmpNo(empNo, now);
		model.addAttribute("monthlyAttendance", monthlyAttendance);

		long normalCount = monthlyAttendance.stream().filter(a -> "정상".equals(a.getAtt_status())).count();
		long lateCount = monthlyAttendance.stream().filter(a -> "지각".equals(a.getAtt_status())).count();
		long earlyLeaveCount = monthlyAttendance.stream().filter(a -> "조퇴".equals(a.getAtt_status())).count();
		long absentCount = monthlyAttendance.stream()
				.filter(a -> "결근".equals(a.getAtt_status()) || a.getAtt_status() == null).count();

		model.addAttribute("normalCount", normalCount);
		model.addAttribute("lateCount", lateCount);
		model.addAttribute("earlyLeaveCount", earlyLeaveCount);
		model.addAttribute("absentCount", absentCount);
		model.addAttribute("currentMonth", new SimpleDateFormat("M").format(now)); // 현재 월

		return "/home";
	}

	// 비밀번호 찾기로 이동
	@GetMapping(value = "/passwordFind.do")
	public String passwordFind() {
		return "login/find_password";
	}

	// 비밀번호 변경으로 이동
	@GetMapping(value = "/passwordChange.do")
	public String passwordChange() {
		return "login/new_password";
	}

	// 사용자 근태 페이지
	@GetMapping("/myAttendanceList.do")
	public String getAttendanceList(HttpSession session, Model model,
			@RequestParam(value = "month", required = false) String month) throws ParseException {
		UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");
		if (loginDto == null) {
			return "redirect:/loginServlet.do";
		}
		String emp_no = loginDto.getEmp_no();

		// 현재 날짜와 선택된 달 처리
		Date date;
		if (month == null || month.isEmpty()) {
			date = new Date(); // 현재 날짜
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			date = sdf.parse(month + "-01"); // 선택된 달의 첫 날
		}

		// 데이터 가져오기
		List<AttendanceDto> monthlyAttendance = attendanceService.selectMonthlyAttendanceByEmpNo(emp_no, date);

		model.addAttribute("monthlyAttendance", monthlyAttendance);
		model.addAttribute("selectedMonth", month != null ? month : new SimpleDateFormat("yyyy-MM").format(new Date()));

		return "/myAttendanceList";
	}

	// 관리자 근태 페이지
	@GetMapping("/empAttendanceList.do")
	public String empGetAttendanceList(HttpSession session, Model model,
			@RequestParam(value = "date", required = false) String dateStr) throws ParseException {
		UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");
		if (loginDto == null) {
			return "redirect:/loginServlet.do";
		}

		String emp_no = loginDto.getEmp_no();

		Date date;
		if (dateStr == null || dateStr.isEmpty()) {
			date = new Date(); // 현재 날짜
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(dateStr); // 선택된 날짜
		}

		// 데이터 가져오기
		List<AttendanceDto> dailyAttendance = attendanceService.selectAllAttendanceForDay(date);

		model.addAttribute("dailyAttendance", dailyAttendance);
		model.addAttribute("selectedDate",
				dateStr != null ? dateStr : new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		return "/empAttendanceList";
	}

	// 휴가 페이지로로 이동
	@GetMapping(value = "/empVacationList.do")
	public String empVacationList() {
		return "/empVacationList";
	}

}
