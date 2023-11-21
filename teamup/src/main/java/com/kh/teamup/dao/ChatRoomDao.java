package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.vo.RoomVO;

public interface ChatRoomDao {

	int sequence();


	void addChat(int chatRoomNo);


	List<RoomVO> roomList(int chatMember);
	
}
