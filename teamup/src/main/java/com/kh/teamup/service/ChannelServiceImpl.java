package com.kh.teamup.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.kh.teamup.vo.RoomVO;

@Service
public class ChannelServiceImpl implements ChannelService{
	private List<RoomVO> roomList = new CopyOnWriteArrayList<>();

	@Override
	public RoomVO createRoom(int chatRoomNo) { // 방 생성
		RoomVO room = RoomVO.builder().chatRoomNo(chatRoomNo).build();
		roomList.add(room);
		return room;
	}
	
	@Override
	public void deleteRoom(int chatRoomNo) { // 방 제거
		RoomVO room = findRoom(chatRoomNo);
		if(room !=null) {
			roomList.remove(chatRoomNo);
		}
	}

	@Override
	public RoomVO findRoom(int chatRoomNo) { //방 검색
		for(RoomVO room : roomList) {
			if(room.getChatRoomNo() == chatRoomNo) return room;
		}
		return null;
	}
	
	@Override
	public void enterUser(WebSocketSession session, int chatRoomNo) {
		RoomVO room =  findRoom(chatRoomNo);
		if(room == null) { // 방번호가 없다면 방 만들기
			room = createRoom(chatRoomNo);
		}
		room.enter(session); // 있다면 입장 or 만든 후 입장
	}
	
	@Override
	public void exitUser(WebSocketSession session, int chatRoomNo) { // 방에서 나가기
		RoomVO room = findRoom(chatRoomNo);
		if(room == null) return;
		
		room.exit(session);
	}
	
	@Override
	public void sendMessage(WebSocketSession session, int chatRoomNo, TextMessage message) throws IOException { // 메세지 보내기
		RoomVO room = findRoom(chatRoomNo);
		if(room == null)return;
		
		room.send(session, message);
		
	}
	
	

}



















