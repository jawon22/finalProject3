package com.kh.teamup.dao;

import com.kh.teamup.dto.ApprovePathDto;

public interface ApprovePathDao {

	int sequence();
	void insert(ApprovePathDto approvePathDto);

}
