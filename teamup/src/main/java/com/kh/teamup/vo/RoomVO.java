package com.kh.teamup.vo;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RoomVO {
	
	
	private int chatRoomNo;
	
	@Builder.Default
	private Set<WebSocketSession> chatMembers = new CopyOnWriteArraySet<>();
	
	
	public void enter(WebSocketSession session) {
		chatMembers.add(session);
	}
	
	public void exit(WebSocketSession session) {
		chatMembers.remove(session);
	}
	public void send(WebSocketSession session,TextMessage message) throws IOException {
		for(WebSocketSession user : chatMembers) {
			user.sendMessage(message);
		}
	}
	
	
	

}
