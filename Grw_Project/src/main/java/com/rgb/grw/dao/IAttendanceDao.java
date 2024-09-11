package com.rgb.grw.dao;

import com.rgb.grw.dto.AttendanceDto;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IAttendanceDao {

	// 출근 기록을 추가
	int insertAttendance(AttendanceDto attendanceDto);

	// 출근 기록을 업데이트
	int updateAttendance(AttendanceDto attendanceDto);

	// 특정 사원의 최신 출근 기록을 조회
	AttendanceDto selectLatestAttendanceByEmpNo(String emp_no);

	// 오늘의 출근 기록을 조회
	AttendanceDto selectTodayAttendanceByEmpNo(String emp_no);

	// 오늘의 출근 상태를 조회
	Map<String, String> selectTodayAttendanceStatusByEmpNo(String emp_no);

	// 특정 사원의 월별 출근 기록을 조회
	List<AttendanceDto> selectMonthlyAttendanceByEmpNo(@Param("emp_no") String emp_no, @Param("date") Date date);

	// 특정 날짜의 모든 사원들의 출근 기록을 조회
	List<AttendanceDto> selectAllAttendanceForDay(Date date);

	// 특정 날짜의 출근 기록을 조회
	List<AttendanceDto> selectAttendanceForDay(@Param("emp_no") String emp_no, @Param("date") Date date);
}
