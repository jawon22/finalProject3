package com.kh.teamup.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.ChatGroupDao;
import com.kh.teamup.dao.ChatRoomDao;
import com.kh.teamup.dto.ChatGroupDto;
import com.kh.teamup.vo.ChatVO;

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
		ChatGroupDto chatGroupDto = new ChatGroupDto();
		
	    // chatVO.getChatGroupDto().getChatMember()로부터 배열 생성 후 할당
	    String[] chatMembers = chatVO.getChatGroupDto().getChatMember();
	    chatGroupDto.setChatMember(chatMembers);
	    log.debug("Dto={}",chatGroupDto);
	    log.debug("member={}",chatMembers[1]);

	    // chatGroupDao에 대화 상대 추가
	    chatGroupDao.addMember(chatGroupDto);
	    
	}
	

}
