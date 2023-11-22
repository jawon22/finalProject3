package com.kh.teamup.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.teamup.service.ChannelService;
import com.kh.teamup.vo.RoomVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SockJsWebSocketServer extends TextWebSocketHandler{
	
	private RoomVO waitingRoom = new RoomVO();
	
	@Autowired private ChannelService channelService;
	
	
    private Map<String, String> sessionUserMap = new ConcurrentHashMap<>();

	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		waitingRoom.enter(session);
	
		String sessionId = session.getId();
		
		log.debug("채널:{}",waitingRoom);
		sessionUserMap.put("client", sessionId);
		log.debug("사용자:{}",sessionId);
		log.debug("사용자{}명",sessionUserMap.size());

		
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		waitingRoom.exit(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession sender, TextMessage message) throws Exception {
		String payload = message.getPayload();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(payload);
		
		log.debug("node={}",jsonNode);

		String messageType = jsonNode.get("type").asText();
		log.debug("type={}",messageType);

		// messageType에 따라 다른 처리 수행
		if ("enterRoom".equals(messageType)) {
		    waitingRoom.enter(sender);
		    log.debug("sender={}",sender);

		    // 여기에서 세션에 원하는 값을 저장
		    String empNo = jsonNode.get("content").asText();
		    sessionUserMap.put(sender.getId(), empNo);
		    log.debug("id={}", sessionUserMap);
		    
		} else if ("userId".equals(messageType)) {
		    // 'userId'에 대한 처리 로직 추가
		    String userId = jsonNode.get("content").asText();
		    // 예: sessionUserMap.put(sender.getId(), userId);
		    
		}

	}
	

}
