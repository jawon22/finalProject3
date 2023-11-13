package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.SalListDto;

public interface SalListDao {

	void insert(SalListDto salListDto);
	boolean delete(int empNo);
	List<SalListDto> findByEmpSalList(int salListNo);
	List<SalListDto> findByEmpNo(int empNo);

}
