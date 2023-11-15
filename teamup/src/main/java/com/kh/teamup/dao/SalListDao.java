package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.SalListDto;
import com.kh.teamup.vo.SalListDetailYearMonthVO;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

public interface SalListDao {

	void insert(SalListDto salListDto);
	boolean delete(int empNo);
	SalListDto selectOne(int salListNo);//급여내역 상세
	SalListDetailYearMonthVO selectOne(TotalWorkingTimeByMonthVO vo);//연월에 따른 급여내역상세
	List<SalListDto> findByEmpNo(int empNo);//급여내역 목록



}
