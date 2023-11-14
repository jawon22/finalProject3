package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ReferrersDto;

@Repository
public class ReferrersDaoImpl implements ReferrersDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("referrers.sequence");
	}
	
	@Override
	public void insert(ReferrersDto referrersDto) {
		sqlSession.insert("referrers.referrersSave",referrersDto);
	}
	
	@Override
	public List<ReferrersDto> selectByPathNo(int pathNo) {
		return sqlSession.selectList("referrers.findByPathNo",pathNo);
	}
	
}
