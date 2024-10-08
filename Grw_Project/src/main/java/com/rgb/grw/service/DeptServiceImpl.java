package com.rgb.grw.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgb.grw.dao.IDeptDao;
import com.rgb.grw.dto.DeptDto;
@Service
public class DeptServiceImpl implements IDeptService {

	
	@Autowired
	private IDeptDao dao;
	
	@Override
	public List<DeptDto> deptList() {
		return dao.deptList();
	}
	
	@Override
	public int insertDept(Map<String, Object> map) {
		return dao.insertDept(map);
	}
	
	@Override
	public List<DeptDto> deptEdit() {
		return dao.deptEdit();
	}
	
	@Override
	public int deptDel(Map<String, Object> map) {
		return dao.deptDel(map);
	}
	
	
	@Override
	public DeptDto deptDetail(String Depno) {
		return dao.deptDetail(Depno);
	}
}

