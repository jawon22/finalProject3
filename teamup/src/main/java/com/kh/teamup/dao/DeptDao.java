package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.DeptDto;
import com.kh.teamup.vo.DeptVo;

public interface DeptDao {
	void addDept(DeptDto deptDto);
	
	List<DeptVo> deptList(String comId);
	
	void deptUpdate(int deptNo,DeptDto deptDto);

}
