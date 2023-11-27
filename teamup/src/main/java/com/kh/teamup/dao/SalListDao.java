package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.SalListDto;
import com.kh.teamup.vo.SalListDetailYearMonthVO;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

public interface SalListDao {

	void insert(SalListDto salListDto);
	boolean delete(int empNo);
	List<SalListDto> findByEmpNo(int empNo);//급여내역 목록
	SalListDto selectOne(SalListDetailYearMonthVO vo);//급여내역(사번,연월기준)
	SalListDto selectOne(int empNo);//최신 급여내역 조회

}
