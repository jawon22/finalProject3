package com.kh.teamup.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.MessageDto;

@Repository
public class MessageDaoImpl implements MessageDao{
	@Autowired private SqlSession sqlSession;
	@Override
	public void send(MessageDto messageDto) {
		
		sqlSession.insert("message.send",messageDto);
	}

}
