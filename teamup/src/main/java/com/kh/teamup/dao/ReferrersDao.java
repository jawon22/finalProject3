package com.kh.teamup.dao;

import com.kh.teamup.dto.ReferrersDto;

public interface ReferrersDao {
	
	int sequence();
	void insert(ReferrersDto referrersDto);

}
