package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.SalListDto;

public interface SalListDao {

	void insert(SalListDto salListDto);
	SalListDto selectOne(int empNo);
	boolean delete(int empNo);
	List<SalListDto> findByEmpSalList(int salListNo);

}
