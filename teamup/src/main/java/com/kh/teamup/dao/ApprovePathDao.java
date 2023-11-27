package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.ApprovePathDto;

public interface ApprovePathDao {

	int sequence();
	void insert(ApprovePathDto approvePathDto);
	List<ApprovePathDto> selectByApprNo(int apprNo); //결재번호에 해당하는 결재선 모두 조회

}
