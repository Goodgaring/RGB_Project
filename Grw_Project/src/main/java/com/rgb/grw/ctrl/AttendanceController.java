package com.rgb.grw.ctrl;

import com.rgb.grw.dto.AttendanceDto;
import com.rgb.grw.dto.UserInfoDto;
import com.rgb.grw.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AttendanceController {

	@Autowired
	private IAttendanceService attendanceService;

	@PostMapping("/toggleAttendance.do")
	@ResponseBody
	public Map<String, Object> toggleAttendance(HttpSession session) {
		UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");
		if (loginDto == null) {
			return Map.of("message", "로그인 정보가 없습니다.");
		}
		String empNo = loginDto.getEmp_no();
		AttendanceDto todayAttendance = attendanceService.selectTodayAttendanceByEmpNo(empNo);
		Date now = new Date();

		boolean isClockedIn = todayAttendance != null && todayAttendance.getAtt_outtime() == null;

		if (isClockedIn) {
			// 퇴근 처리
			todayAttendance.setAtt_outtime(now);
			attendanceService.updateAttendance(todayAttendance);
			return Map.of("message", "퇴근 처리되었습니다.", "isClockedIn", false);
		} else {
			// 출근 상태 확인
			if (todayAttendance == null) {
				// 출근 기록이 없는 경우 새로 추가
				AttendanceDto newAttendance = new AttendanceDto();
				newAttendance.setEmp_no(empNo);
				newAttendance.setAtt_intime(now);
				attendanceService.insertAttendance(newAttendance);
				return Map.of("message", "출근 처리되었습니다.", "isClockedIn", true);
			} else {
				// 이미 출근 기록이 있는 경우
				return Map.of("message", "이미 출근 처리된 상태입니다.", "isClockedIn", isClockedIn);
			}
		}
	}

	@GetMapping("/checkAttendanceStatus.do")
	@ResponseBody
	public Map<String, Object> checkAttendanceStatus(HttpSession session) {
		UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");
		if (loginDto == null) {
			return Map.of("message", "로그인 정보가 없습니다.", "isClockedIn", false);
		}
		String empNo = loginDto.getEmp_no();
		AttendanceDto todayAttendance = attendanceService.selectTodayAttendanceByEmpNo(empNo);
		boolean isClockedIn = todayAttendance != null && todayAttendance.getAtt_outtime() == null;
		boolean canClockIn = todayAttendance == null
				|| todayAttendance.getAtt_intime() != null && todayAttendance.getAtt_outtime() == null;

		return Map.of("isClockedIn", isClockedIn, "canClockIn", canClockIn);
	}

}
