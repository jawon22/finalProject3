package com.kh.teamup.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.CompanyDto;

@Repository
public class CompanyDaoImpl implements CompanyDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addCom(CompanyDto companyDto) {
		sqlSession.insert("com.addCom",companyDto);
	}
	
	@Override
	public void connectCom(String comId, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("comId", comId);
		params.put("attachNo", attachNo);
		sqlSession.insert("com.comImage", params);
	}
	
}
