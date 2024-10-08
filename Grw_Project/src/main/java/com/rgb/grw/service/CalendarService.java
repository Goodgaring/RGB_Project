package com.rgb.grw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgb.grw.dao.ICalendarDao;
import com.rgb.grw.dto.CalendarDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarService implements ICalendarService {
	
	private final ICalendarDao dao;
	
	
	@Override
	public List<CalendarDto> selectCal(Map<String, String> map) {
		log.info("CalendaerService selectCal");
//		Map<String, Object> event = new HashMap<String, Object>();
//		List<Map<String, Object>> eventList = new ArrayList<Map<String,Object>>();
		return dao.selectCal(map);
	}
	
	@Override
	public boolean insertCal(Map<String, Object> map) {
		log.info("Calendar insertCal");
		return dao.insertCal(map);
	}
	
	@Override
	public CalendarDto eventDetail(int sd_no) {
		return dao.eventDetail(sd_no);
	}
	
	@Override
	public boolean delEvent(int eventNo) {
		// TODO Auto-generated method stub
		return dao.delEvent(eventNo);
	}
	
	@Override
	public boolean modifyEvent(Map<String, Object> map) {
		return dao.modifyEvent(map);
	}
}
