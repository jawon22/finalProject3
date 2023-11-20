package com.kh.teamup.vo;

import com.kh.teamup.dto.ChatGroupDto;
import com.kh.teamup.dto.ChatRoomDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatVO {
	private ChatRoomDto chatRoomDto;
	private ChatGroupDto chatGroupDto;

}
