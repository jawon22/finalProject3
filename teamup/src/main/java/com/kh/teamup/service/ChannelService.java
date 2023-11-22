package com.kh.teamup.service;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.kh.teamup.vo.RoomVO;

public interface ChannelService {
	RoomVO createRoom(int chatRoomNo); //방 생성
	void deleteRoom (int chatRoomNo); //방 제거
	RoomVO findRoom(int chatRoomNo); // 방 검색
	
	void enterUser(WebSocketSession session, int chatRoomNo); 
	void exitUser(WebSocketSession session, int chatRoomNo);
	void sendMessage(WebSocketSession session, int chatRoomNo, TextMessage message) throws IOException;
	
	
}
