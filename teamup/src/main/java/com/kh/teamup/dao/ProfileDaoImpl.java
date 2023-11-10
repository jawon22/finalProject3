package com.kh.teamup.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ProfileDto;

@Repository
public class ProfileDaoImpl implements ProfileDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addProfile(ProfileDto profileDto) {
		sqlSession.insert("profile.addProfile", profileDto);
	}
	
	@Override
	public void connectProfile(int profileNo, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("profileNo", profileNo);
		params.put("attachNo", attachNo);
		sqlSession.insert("com.profileImage", params);
		
	}

}
