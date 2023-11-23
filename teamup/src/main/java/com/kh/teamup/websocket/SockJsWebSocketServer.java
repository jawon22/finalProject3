package com.kh.teamup.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.teamup.service.ChannelService;
import com.kh.teamup.vo.ClientVO;
import com.kh.teamup.vo.RoomVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SockJsWebSocketServer extends TextWebSocketHandler{
	
	@Autowired private ChannelService channelService;
	
	private RoomVO waitingRoom = new RoomVO();
	
    private Map<String, String> sessionUserMap = new ConcurrentHashMap<>();
    private ObjectMapper mapper = new ObjectMapper(); //JSON 변환기

	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		ClientVO client = new ClientVO(session);
		
		waitingRoom.enter(client);
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		ClientVO client  = new ClientVO(session);
		waitingRoom.exit(client);
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession sender, TextMessage message) throws Exception {
		
//		type = enterRoom , chatRoomNo = 144
		ClientVO client = new ClientVO(sender);
		Map params = mapper.readValue(message.getPayload(), Map.class); //JSON메세지 해석
		
		Map<String, Object> map = new HashMap<>();
		map.put("empNo", client.getEmpNo());
		map.put("deptNo", client.getDeptNo());
		map.put("empPositionNo", client.getEmpPositionNo());
		map.put("content", params.get("content"));

		
		log.debug("params={}",params);
		
		//사용자가 보낸 메세지에 type과 chatRoomNo가은게 있어야 어떠한 처리가 가능
//		String messageType = jsonNode.get("type").asText();
//		int roomNo = jsonNode.get("chatRoomNo").asInt();
		
		String type = (String) params.get("type");
		int roomNo = (int) params.get("chatRoomNo");
		log.debug("type={}",type);
		log.debug("roomNo",roomNo);
		
		String messageJson = mapper.writeValueAsString(map);
		TextMessage tm = new TextMessage(messageJson);
		
		for(ClientVO c : waitingRoom.getChatMembers()) {
			c.sendMessage(tm);
		}
		
		
		
		
		// messageType에 따라 다른 처리 수행
//		if ("enterRoom".equals(messageType)) {
//		    waitingRoom.enter(sender);
//		    log.debug("sender={}",sender);
//
//		    // 여기에서 세션에 원하는 값을 저장
//		    String empNo = jsonNode.get("content").asText();
//		    sessionUserMap.put(sender.getId(), empNo);
//		    log.debug("id={}", sessionUserMap);
//		    
//		} else if ("userId".equals(messageType)) {
//		    // 'userId'에 대한 처리 로직 추가
//		    String userId = jsonNode.get("content").asText();
//		    // 예: sessionUserMap.put(sender.getId(), userId);
//		    
//		}

	}
	
	//접속한 사용자에게 메세지 이력을 전송하는 메소드
	public void sendMessageList(ClientVO client) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
