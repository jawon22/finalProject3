package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.SalListDto;
import com.kh.teamup.vo.SalListDetailYearMonthVO;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

public interface SalListDao {

	void insert(SalListDto salListDto);

	List<SalListDto> findByEmpNo(int empNo);//급여내역 목록
	boolean delete(int empNo);

	SalListDto selectOne(SalListDetailYearMonthVO vo);
	

}
