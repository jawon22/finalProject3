package com.kh.teamup.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ChatGroupDto;

@Repository
public class ChatGroupDaoImpl implements ChatGroupDao{
	@Autowired private SqlSession sqlSession;
	@Override
	public void addMember(ChatGroupDto chatGroupDto) {
		sqlSession.insert("chat.addMember", chatGroupDto);
	}

}
