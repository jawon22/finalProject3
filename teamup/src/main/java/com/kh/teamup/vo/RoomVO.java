package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RoomVO {
	
	
	private int chatRoomNo;
	
	private int chatMember;
	
	
	@Builder.Default
	private Set<WebSocketSession> chatMembers = new CopyOnWriteArraySet<>();
	
	
	public void enter(WebSocketSession session) {
		chatMembers.add(session);
	}
	
	public void exit(WebSocketSession session) {
		chatMembers.remove(session);
	}
	public void send(WebSocketSession session,TextMessage message) throws IOException {
		for(WebSocketSession chatMember : chatMembers) {
			chatMember.sendMessage(message);
		}
	}
	
	

}
