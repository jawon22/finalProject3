package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.SalListDto;

public interface SalListDao {

	void insert(SalListDto salListDto);
	boolean delete(int empNo);
	List<SalListDto> findByEmpSalList(int salListNo);//급여내역상세
	List<SalListDto> findByEmpNo(int empNo);//급여내역 목록


}
