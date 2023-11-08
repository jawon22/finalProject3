package com.kh.teamup.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttachDto;

@Repository
public class AttachDaoImpl implements AttachDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(AttachDto attachDto) {
		sqlSession.insert("attach.save", attachDto);
	}

	@Override
	public AttachDto selectOne(int attachNo) {
		return sqlSession.selectOne("attach.find", attachNo);
	}

	@Override
	public boolean delete(int attachNo) {
		return sqlSession.delete("attach.remove", attachNo) > 0;
	}

}
