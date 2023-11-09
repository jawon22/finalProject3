package com.kh.teamup.dao;

import com.kh.teamup.dto.AttachDto;

public interface AttachDao {
	
	//어테치 시퀀스
	int sequence();
	
	//이미지 업로드
	void insert(AttachDto attachDto);
	
	//이미지 다운로드
	AttachDto selectOne(int attachNo);
	
	//이미지 삭제
	boolean delete(int attachNo);
	
}
