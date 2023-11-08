package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.EmpDto;

public interface EmpDao {
	
	void addEmp(EmpDto empDto);
	//login 아이디 생성
	void updateEmpId(int empNo,EmpDto empDto);
	//전체 조회 
	List<EmpDto> empList();
	void deleteEmp(int empNo);
	void empInfoUpdate(int empNo, EmpDto empDto);

}
