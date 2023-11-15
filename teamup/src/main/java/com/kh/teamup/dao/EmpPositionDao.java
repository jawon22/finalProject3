package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.EmpPositionDto;

public interface EmpPositionDao {

	List<EmpPositionDto> selectList();

	void addPosition(EmpPositionDto empPositionDto);

	List<EmpPositionDto> listByCom(String comId);

}
