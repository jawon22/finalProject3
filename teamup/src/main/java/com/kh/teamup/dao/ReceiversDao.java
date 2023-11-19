package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.ReceiversDto;

public interface ReceiversDao {
	
	int sequence();
	void insert(ReceiversDto receiversDto);
	
	List<ReceiversDto> selectByPathNo(int pathNo); // 결재선에 대한 승인자 리스트 모두 조회
	ReceiversDto selectByPathNoAndReceiver(int pathNo, int receiver);
	boolean apprConfirm(int pathNo, int receiver, String reasons ); // 결재 승인
	boolean apprCancel(int pathNo, int receiver, String reasons); // 결재 반려

}
