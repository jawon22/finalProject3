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
	public int sequence() {
		return sqlSession.selectOne("attach.sequence");
	}

	//이미지 업로드
	@Override
	public void insert(AttachDto attachDto) {
		sqlSession.insert("attach.save", attachDto);
	}

	//이미지 다운로드
	@Override
	public AttachDto selectOne(int attachNo) {
		return sqlSession.selectOne("attach.find", attachNo);
	}

	//이미지 삭제
	@Override
	public boolean delete(int attachNo) {
		return sqlSession.delete("attach.remove", attachNo) > 0;
	}

}
