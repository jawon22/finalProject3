package com.kh.teamup.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class chatRoomDaoImpl implements ChatRoomDao{
	@Autowired private SqlSession sqlSession;
	//채팅방 생성
	@Override
	public int sequence() {
		return sqlSession.selectOne("chat.sequence");
	}
	@Override
	public void addChat(int chatRoomNo) {
		sqlSession.insert("chat.addChat", chatRoomNo);
	}
	//채팅멤버
	
	
	//메세지
	

}
