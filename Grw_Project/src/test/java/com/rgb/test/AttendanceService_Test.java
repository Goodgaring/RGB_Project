package com.rgb.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rgb.grw.dto.AttendanceDto;
import com.rgb.grw.service.IAttendanceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class AttendanceService_Test {

	@Autowired
	private IAttendanceService attendanceService;

	private AttendanceDto attendanceDto;
	private String emp_no;
	private Date date;

	@Before
	public void setUp() {
		emp_no = "000000"; // 테스트할 사원번호 설정
		date = new Date(); // 현재 날짜와 시간

		// AttendanceDto 초기화
		attendanceDto = new AttendanceDto();
		attendanceDto.setEmp_no(emp_no); // 사원 번호 설정
		attendanceDto.setAtt_intime(date); // 출근 시간 설정
		attendanceDto.setAtt_worktime(480); // 8시간 근무
		attendanceDto.setAtt_basetime(480); // 8시간 기준 근무
		attendanceDto.setAtt_late(0);

		// 초기화된 값 확인
		System.out.println("setUp 결과:");
		System.out.println("emp_no: " + emp_no);
		System.out.println("date: " + date);
		System.out.println("attendanceDto: " + attendanceDto);
	}

//    @Test
	public void testInsertAttendance() {
		int result = attendanceService.insertAttendance(attendanceDto);
		System.out.println("testInsertAttendance 결과: " + result);
		assertEquals("출근 기록 추가 실패", 1, result);
	}

//	@Test
	public void testUpdateAttendance_test() {
		// 출근 기록 삽입
		int insertResult = attendanceService.insertAttendance(attendanceDto);
		assertEquals("출근 기록 삽입 실패", 1, insertResult); // 삽입이 정상적으로 되었는지 확인
		System.out.println("출근 기록 삽입 성공: " + insertResult);

		// 퇴근 기록 업데이트
		attendanceDto.setAtt_outtime(new Date()); // 퇴근 시간 설정
		attendanceDto.setAtt_status("퇴근"); // 상태 변경

		// 삽입된 데이터 확인을 위한 로그 출력
		System.out.println("업데이트할 attendanceDto: " + attendanceDto);

		int updateResult = attendanceService.updateAttendance(attendanceDto);
		assertEquals("출근 기록 업데이트 실패", 1, updateResult); // 업데이트가 정상적으로 되었는지 확인
		System.out.println("퇴근 기록 업데이트 성공: " + updateResult);
	}

	// 메인테스트
//	@Test
	public void testUpdateAttendance() {
		// 출근 기록 삽입
		int insertResult = attendanceService.insertAttendance(attendanceDto);
		assertEquals("출근 기록 삽입 실패", 1, insertResult); // 삽입이 정상적으로 되었는지 확인
		System.out.println("출근 기록 삽입 성공: " + insertResult);

		// 퇴근 시간 설정: 출근 시간으로부터 8시간 후로 설정
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(attendanceDto.getAtt_intime()); // 출근 시간 설정
		calendar.add(Calendar.HOUR, 8); // 8시간 추가
		Date att_outtime = calendar.getTime(); // 퇴근 시간 생성

		attendanceDto.setAtt_outtime(att_outtime); // 퇴근 시간 설정
		attendanceDto.setAtt_status("퇴근"); // 상태 변경

		// 삽입된 데이터 확인을 위한 로그 출력
		System.out.println("업데이트할 attendanceDto: " + attendanceDto);

		int updateResult = attendanceService.updateAttendance(attendanceDto);
		assertEquals("출근 기록 업데이트 실패", 1, updateResult); // 업데이트가 정상적으로 되었는지 확인
		System.out.println("퇴근 기록 업데이트 성공: " + updateResult);
	}

//	@Test
	public void testSelectLatestAttendanceByEmpNo() {
		// 예제: 출근 기록을 추가할 때, 반드시 'att_intime'이 최신이어야 합니다.
		AttendanceDto attendanceDto = new AttendanceDto();
		attendanceDto.setEmp_no("testEmpNo");
		attendanceDto.setAtt_intime(new Date()); // 현재 시간 설정
		attendanceService.insertAttendance(attendanceDto);
		AttendanceDto result = attendanceService.selectLatestAttendanceByEmpNo("testEmpNo");
		System.out.println("testSelectLatestAttendanceByEmpNo 결과: " + result);
		assertNotNull("최신 출근 기록 조회 실패", result);
		assertEquals("사원 번호 불일치", "testEmpNo", result.getEmp_no());
		assertEquals("출근 상태 불일치", "출근", result.getAtt_status());
	}

//	@Test
	public void testSelectAttendanceForDay() {
		Date date = new Date(); // 현재 날짜로 설정
		AttendanceDto attendanceDto = new AttendanceDto();
		attendanceDto.setEmp_no("0000");
		attendanceDto.setAtt_intime(date);
		attendanceService.insertAttendance(attendanceDto);
		List<AttendanceDto> result = attendanceService.selectAttendanceForDay("testEmpNo", date);
		System.out.println("testSelectAttendanceForDay 결과: " + result);
		assertNotNull("특정 날짜 출근 기록 조회 실패", result);
		assertFalse("출근 기록이 없습니다", result.isEmpty());
	}

//	@Test
	public void testSelectTodayAttendanceByEmpNo() {
		Date today = new Date(); // 오늘 날짜로 설정
		AttendanceDto attendanceDto = new AttendanceDto();
		attendanceDto.setEmp_no("0000");
		attendanceDto.setAtt_intime(today);
		attendanceService.insertAttendance(attendanceDto);
		AttendanceDto result = attendanceService.selectTodayAttendanceByEmpNo("testEmpNo");
		System.out.println("testSelectTodayAttendanceByEmpNo 결과: " + result);
		assertNotNull("오늘의 출근 기록 조회 실패", result);
		assertEquals("사원 번호 불일치", "testEmpNo", result.getEmp_no());
	}

//    @Test
	public void testSelectTodayAttendanceStatusByEmpNo() {
		attendanceService.insertAttendance(attendanceDto);
		Map<String, String> result = attendanceService.selectTodayAttendanceStatusByEmpNo(emp_no);
		System.out.println("testSelectTodayAttendanceStatusByEmpNo 결과: " + result);
		assertNotNull("오늘의 출근 상태 조회 실패", result);
//		assertTrue("출근 상태가 없습니다", result.containsKey("STATUS"));
	}

	@Test
	public void testSelectMonthlyAttendanceByEmpNo() {
		Date date = new Date(); // 현재 날짜로 설정
		AttendanceDto attendanceDto = new AttendanceDto();
		attendanceDto.setEmp_no("0000");
		attendanceDto.setAtt_intime(date);
		attendanceService.insertAttendance(attendanceDto);
		List<AttendanceDto> result = attendanceService.selectMonthlyAttendanceByEmpNo("0000", date);
		System.out.println("testSelectMonthlyAttendanceByEmpNo 결과: " + result);
		assertNotNull("월별 출근 기록 조회 실패", result);
		assertFalse("출근 기록이 없습니다", result.isEmpty());
	}

//	@Test
	public void testSelectAllAttendanceForDay() {
		Date date = new Date(); // 현재 날짜로 설정
		AttendanceDto attendanceDto = new AttendanceDto();
		attendanceDto.setEmp_no("0000");
		attendanceDto.setAtt_intime(date);
		attendanceService.insertAttendance(attendanceDto);
		List<AttendanceDto> result = attendanceService.selectAllAttendanceForDay(date);
		System.out.println("testSelectAllAttendanceForDay 결과: " + result);
		assertNotNull("특정 날짜 모든 사원 출근 기록 조회 실패", result);
		assertFalse("출근 기록이 없습니다", result.isEmpty());
	}

}
