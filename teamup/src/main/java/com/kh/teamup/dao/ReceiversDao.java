package com.kh.teamup.dao;

import com.kh.teamup.dto.ReceiversDto;

public interface ReceiversDao {
	
	int sequence();
	void insert(ReceiversDto receiversDto);

}
