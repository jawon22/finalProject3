package com.kh.teamup.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ChatGroupDto;

@Repository
public class ChatGroupDaoImpl implements ChatGroupDao{
	@Autowired private SqlSession sqlSession;
	@Override
	public void addMember(int chatRoomNo, int chatMember) {
		Map<String, Object> params = Map.of("chatRoomNo", chatRoomNo, "chatMember", chatMember);
		System.out.println(params);
		sqlSession.insert("chat.addMember", params);
	}

}
