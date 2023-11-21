package com.kh.teamup.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kh.teamup.service.ChannelService;
import com.kh.teamup.vo.RoomVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SockJsWebSocketServer extends TextWebSocketHandler{
	
	private RoomVO waitingRoom = new RoomVO();
	
	@Autowired private ChannelService channelService;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		waitingRoom.enter(session);
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		waitingRoom.exit(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
	}
	

}
