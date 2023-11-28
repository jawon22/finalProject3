package com.kh.teamup.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.teamup.service.ChannelService;
import com.kh.teamup.vo.ClientVO;
import com.kh.teamup.vo.RoomVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SockJsWebSocketServer extends TextWebSocketHandler {

	@Autowired
	private ChannelService channelService;

	private RoomVO waitingRoom = new RoomVO();

	Map<String, List<ClientVO>> chatRooms = new HashMap<>();

	private Map<String, Object> sessionUserMap = new ConcurrentHashMap<>();
	private ObjectMapper mapper = new ObjectMapper(); // JSON 변환기

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		ClientVO client = new ClientVO(session);
//		
//
//		waitingRoom.enter(client);
//		log.debug("client!!={}",client);
//		log.debug("waitingRoom!!={}",waitingRoom);
//
//		log.debug("{}명",waitingRoom.getChatMembers().size());

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		ClientVO client = new ClientVO(session);
//		waitingRoom.exit(client);
		
	}

//	@Override
//	protected void handleTextMessage(WebSocketSession sender, TextMessage message) throws Exception {
//		
////		type = enterRoom , chatRoomNo = 144
//		ClientVO client = new ClientVO(sender);
//		Map params = mapper.readValue(message.getPayload(), Map.class); //JSON메세지 해석
//		client.setEmpNo((int)params.get("empNo"));
//		client.setMessageTime((String)params.get("date"));
//		
//		Map<String, Object> map = new HashMap<>();
////		map.put("empNo", client.getEmpNo());
////		map.put("empName", client.getEmpName());
////		map.put("date", client.getMessageTime());
////		map.put("roomNo", params.get("roomNo"));
//		
////		map.put("deptNo", client.getDeptNo());
////		map.put("empPositionNo", client.getEmpPositionNo());
//		
//		map.put("type", params.get("type"));
//		map.put("content", params.get("content"));
//
//		
//		log.debug("params={}",params);
//		
//		//사용자가 보낸 메세지에 type과 chatRoomNo가은게 있어야 어떠한 처리가 가능
////		String messageType = jsonNode.get("type").asText();
////		int roomNo = jsonNode.get("chatRoomNo").asInt();
//		
//		String type = (String) params.get("type");
//		int chatRoomNo = (int) params.get("chatRoomNo");
//		log.debug("type={}",type);
//		log.debug("chatRoomNo={}",chatRoomNo);
//		
//		String messageJson = mapper.writeValueAsString(map);
//		TextMessage tm = new TextMessage(messageJson);
//		log.debug("tm={}",tm);
//		
//		for(ClientVO c : waitingRoom.getChatMembers()) {
//			c.sendMessage(tm);
//		}
//		
//		
//			
//
//	}

	@Override
	protected void handleTextMessage(WebSocketSession sender, TextMessage message) throws Exception {
		// ...
//		log.debug("sender={}",sender.getId());
		Map params = mapper.readValue(message.getPayload(), Map.class); // JSON메세지 해석
		
//		ClientVO client = new ClientVO(sender);
//		
//		log.debug("클라이언트={}",client.getEmpNo());


		// 클라이언트에서 전송한 데이터에서 type과 chatRoomNo 추출
		String type = (String) params.get("type");
		String chatRoomNo = (String) params.get("chatRoomNo");
		String content = (String) params.get("content");
		String id = (String) params.get("id");

		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("id", id);
		map.put("content", content);

//		log.debug("type={}", type);

		// 나머지 로직 수행

		if ("message".equals(type)) {
//			   log.debug("Received message content: {}", content);

			    String messageJson = mapper.writeValueAsString(map);
			    TextMessage tm = new TextMessage(messageJson);
//			    log.debug("tm={}", messageJson);

			    // 이게 웨이팅 룸이 아니라 개별 방 번호로 전송
			    List<ClientVO> roomMembers = chatRooms.get(chatRoomNo);
			    if (roomMembers != null) {
			        for (ClientVO c : roomMembers) {
			            c.sendMessage(tm);
//			            log.debug("members",c);
			        }
			    }
		}
		// "enterRoom" 타입인 경우
		else if ("enterRoom".equals(type)) {
			String user = (String) params.get("empNo");
//			log.debug("client={}",client);
//			log.debug("User entered chat room: {}", chatRoomNo);

			List<ClientVO> roomMembers = chatRooms.get(chatRoomNo);
			if (roomMembers == null) {
			    roomMembers = new ArrayList<>();
			    chatRooms.put(chatRoomNo, roomMembers);
			}

			// 클라이언트가 이미 방에 속해 있는지 확인
//			if (!roomMembers.contains(client)) {
//			    // 클라이언트를 채팅방에 추가
//			    roomMembers.add(client);
//			    log.debug("User entered chat room: {}", chatRoomNo);
//			} else {
//			    log.debug("User is already in the chat room: {}", roomMembers.size());
//			}
			}
		

		// 그 외의 타입인 경우
//		else {
//			log.warn("Unknown message type: {}", type);
//			
			// 예외 처리 또는 로깅
//		}

	}

	private String convertTextMessageToJson(TextMessage textMessage) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Convert TextMessage to JSON string
			return objectMapper.writeValueAsString(textMessage);
		} catch (JsonProcessingException e) {
			log.error("Error converting TextMessage to JSON", e);
			return null;
		}
	}

	// 접속한 사용자에게 메세지 이력을 전송하는 메소드
	public void sendMessageList(ClientVO client) {

	}

}
