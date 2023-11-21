package com.kh.teamup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MessageDto {
	private int messageNo;
	private int chatRoomNo;
	private String chatContent;
	private String messageTime;
	private String messageStatus;
	private int sender;
	
}
