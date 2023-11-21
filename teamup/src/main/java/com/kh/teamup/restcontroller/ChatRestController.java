package com.kh.teamup.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.ChatGroupDao;
import com.kh.teamup.dao.ChatRoomDao;
import com.kh.teamup.dao.MessageDao;
import com.kh.teamup.dto.ChatRoomDto;
import com.kh.teamup.dto.MessageDto;
import com.kh.teamup.vo.ChatVO;
import com.kh.teamup.vo.RoomVO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "채팅" ,description = "채팅 CRUD")
@RestController
@CrossOrigin
@RequestMapping("/chat")
@Slf4j
public class ChatRestController {
	
	@Autowired private ChatRoomDao chatRoomDao;
	@Autowired private ChatGroupDao chatGroupDao;
	
	@PostMapping("/add/")
	public void addChat(@RequestBody ChatVO chatVO) {
		int chatRoomNo = chatRoomDao.sequence();
		
		//채팅방 생성 
		chatRoomDao.addChat(chatRoomNo);
		
		log.debug("no={}",chatRoomNo);
		
		//채팅 대상 추가

	    // chatVO.getChatGroupDto().getChatMember()로부터 배열 생성 후 할당
		int[] chatMembers = chatVO.getChatGroupDto().getChatMember();
		log.debug("members={}", chatMembers);
		// chatGroupDao에 대화 상대 추가
		for (int i = 0 ; i<chatMembers.length; i++) {

			log.debug("member={}", chatMembers[i]);
		    log.debug("NO={}", chatRoomNo);

		    chatGroupDao.addMember(chatRoomNo, chatMembers[i]);
		}
	}
	
	@GetMapping("/roomList/{chatMember}")
	public List<RoomVO> roomList(@PathVariable int chatMember){
		
		return chatRoomDao.roomList(chatMember);
	}
	
	
	
	@Autowired private MessageDao messageDao;
	
	@PostMapping("/messege/")
	public void messageSend(@RequestBody MessageDto messageDto) {
		
		messageDao.send(messageDto);
		
	}
	}
