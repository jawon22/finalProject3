package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.ReceiversDto;

public interface ReceiversDao {
	
	int sequence();
	void insert(ReceiversDto receiversDto);
	
	List<ReceiversDto> selectByPathNo(int pathNo); // 결재선에 대한 승인자 리스트 모두 조회

}
