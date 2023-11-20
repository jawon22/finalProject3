package com.kh.teamup.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.ChatGroupDao;
import com.kh.teamup.dao.ChatRoomDao;
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

	    // chatVO.getChatGroupDto().getChatMember()로부터 배열 생성 후 할당
		
		
		
		
		
	    
	    
		int[] chatMembers = chatVO.getChatGroupDto().getChatMember();
		log.debug("members={}", chatMembers);

		// paramMap 생성 및 내용 설정
		
		
		// chatGroupDao에 대화 상대 추가
		for (int i = 0 ; i<chatMembers.length; i++) {

			log.debug("member={}", chatMembers[i]);
		    log.debug("NO={}", chatRoomNo);

		    chatGroupDao.addMember(chatRoomNo, chatMembers[i]);
		    
		}
	}
	}

//결제자 번호 3 2 6 
//로컬에다가 내거 직급 -> 로그인 직급을저장 
//3   if(3>1) 버튼 못누르게 
//else if 승인상태를 조회해서 이사람이 승인을 했으면 버튼을 활성화 1 
