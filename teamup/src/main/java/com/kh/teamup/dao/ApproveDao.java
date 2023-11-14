package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.ApproveDto;

public interface ApproveDao {
	
	int sequence(); //시퀀스
	ApproveDto selectOne(int apprNo); // 하나 조회
	List<ApproveDto> selectList(); //조회
	void insert(ApproveDto approveDto); //등록
	
	boolean delete(int apprNo); //삭제
	

}
