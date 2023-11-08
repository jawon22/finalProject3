package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.ApproveDto;

public interface ApproveDao {

	List<ApproveDto> selectList(); //조회
	void insert(ApproveDto approveDto); //등록
	
	boolean delete(int empNo); //삭제
	

}
