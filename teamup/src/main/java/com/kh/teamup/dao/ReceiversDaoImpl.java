package com.kh.teamup.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ReceiversDto;

@Repository
public class ReceiversDaoImpl implements ReceiversDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("receivers.sequence");
	}
	
	@Override
	public void insert(ReceiversDto receiversDto) {
		sqlSession.insert("receivers.receiversSave",receiversDto);
	}
	
	
	
}
