package com.rgb.grw.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgb.grw.dao.IAttendanceDao;
import com.rgb.grw.dto.AttendanceDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	private IAttendanceDao dao;

	@Override
	public int insertAttendance(AttendanceDto attendanceDto) {
		log.info("AttendanceServiceImpl 출근 기록 추가: {}", attendanceDto);
		return dao.insertAttendance(attendanceDto);
	}

	@Override
	public int updateAttendance(AttendanceDto attendanceDto) {
		log.info("AttendanceServiceImpl 출근 기록 업데이트: {}", attendanceDto);
		return dao.updateAttendance(attendanceDto);
	}

	@Override
	public AttendanceDto selectLatestAttendanceByEmpNo(String empNo) {
		log.info("AttendanceServiceImpl 최신 출근 기록 조회: {}", empNo);
		return dao.selectLatestAttendanceByEmpNo(empNo);
	}

	@Override
	public List<AttendanceDto> selectAttendanceForDay(String empNo, Date date) {
		log.info("AttendanceServiceImpl 특정 날짜 출근 기록 조회: {} {}", empNo, date);
		return dao.selectAttendanceForDay(empNo, date);
	}

	@Override
	public AttendanceDto selectTodayAttendanceByEmpNo(String empNo) {
		log.info("AttendanceServiceImpl 오늘의 출근 기록 조회: {}", empNo);
		return dao.selectTodayAttendanceByEmpNo(empNo);
	}

	@Override
	public Map<String, String> selectTodayAttendanceStatusByEmpNo(String empNo) {
		log.info("AttendanceServiceImpl 오늘의 출근 상태 조회: {}", empNo);
		return dao.selectTodayAttendanceStatusByEmpNo(empNo);
	}

	@Override
	public List<AttendanceDto> selectMonthlyAttendanceByEmpNo(String empNo, Date date) {
		log.info("AttendanceServiceImpl 월별 출근 기록 조회: {} {}", empNo, date);
		return dao.selectMonthlyAttendanceByEmpNo(empNo, date);

	}

	@Override
	public List<AttendanceDto> selectAllAttendanceForDay(Date date) {
		log.info("AttendanceServiceImpl 특정 날짜 모든 사원 출근 기록 조회: {}", date);
		return dao.selectAllAttendanceForDay(date);
	}
}
