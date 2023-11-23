package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.MessageDto;

public interface MessageDao {

	void send(MessageDto messageDto);
	
	List<MessageDto> list(int chatRoomNo);

}
