package com.kh.teamup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.teamup.dao.ApproveDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PastDateApprUpdateServiceImpl implements PastDateApprUpdateService{
	
	@Autowired
	private ApproveDao approveDao;

//	@Scheduled(cron="0 0 0 * * *")//(매일 자정마다)
	@Override
	public void updateApprByEndDate() {
		
	}

}
