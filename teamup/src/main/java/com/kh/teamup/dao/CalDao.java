package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.EmpCalDto;

public interface CalDao {
	
	List<EmpCalDto> empCalList(int empNo);
	void insert(EmpCalDto empCalDto);
	EmpCalDto selectOne(int calNo);
	void updateCal(int calNo,EmpCalDto empCalDto);
	void deleteCal(int calNo);
	void deptadd(EmpCalDto empCalDto);
	List<EmpCalDto> deptCalList(int deptNo);
	EmpCalDto deptDetail(int calNo);
	void updateDeptCal(int calNo, EmpCalDto empCalDto);
	void deleteDeptCal(int calNo);

}
