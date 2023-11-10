package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.EmpCalDto;

public interface CalDao {
	
	List<EmpCalDto> empCalList(int empNo);
	void insert(EmpCalDto empCalDto);

}
