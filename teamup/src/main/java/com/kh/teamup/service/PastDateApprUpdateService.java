package com.kh.teamup.service;

public interface PastDateApprUpdateService {
	//승인 마감일이 지난 결재들의 승인자 을 모두 반려로 업데이트(매일마다)
	void updateApprByEndDate();
}
