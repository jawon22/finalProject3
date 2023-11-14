package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.ReferrersDto;

public interface ReferrersDao {
	
	int sequence();
	void insert(ReferrersDto referrersDto);

	List<ReferrersDto> selectByPathNo(int pathNo); //결재선에 대한 모든 참조자의 리스트조회
}
