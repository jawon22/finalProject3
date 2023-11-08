package com.kh.teamup.dao;

import com.kh.teamup.dto.AttachDto;

public interface AttachDao {
	
	void insert(AttachDto attachDto);
	
	AttachDto selectOne(int attachNo);
	
	boolean delete(int attachNo);
	
}
