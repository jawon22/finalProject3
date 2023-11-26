package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.ReplyDto;
import com.kh.teamup.vo.ReplyByBoardVO;

public interface ReplyDao {

	void insert(ReplyDto replyDto);
	List<ReplyByBoardVO> replyListByBoard(long replyOrigin);
	void deleteReply(long replyNo);
	void editReply(ReplyDto replyDto, long replyNo);
	ReplyDto selectReply(long replyNo);
	
	

}
