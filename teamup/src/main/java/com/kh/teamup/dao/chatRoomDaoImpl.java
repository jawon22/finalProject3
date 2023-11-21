package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.vo.RoomVO;

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
	@Override
	public List<RoomVO> roomList(int chatMember) {
		return sqlSession.selectList("chat.list",chatMember);
	}
	
	
	//메세지
	

}
