package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ApproveDto;

@Repository
public class ApproveDaoImpl implements ApproveDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ApproveDto> selectList() {
		return sqlSession.selectList("approve.list");
	}
	
	
}
