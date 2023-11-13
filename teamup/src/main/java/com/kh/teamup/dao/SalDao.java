package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.SalDto;

public interface SalDao {

	void insert(SalDto salDto);//저장
	List<SalDto> selectList(); //조회
	SalDto selectOne(int empNo);//상세
	void edit(int empNo, SalDto salDto);
	int selectLatesSalNo(int empNo);//최신연봉조회

}
