package com.kh.teamup.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.CompanyDto;

@Repository
public class CompanyDaoImpl implements CompanyDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addCom(CompanyDto companyDto) {
		sqlSession.insert("com.addCom",companyDto);
	}
	
	//회사와 회사이미지 연결
	@Override
	public void connectCom(String comId, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("comId", comId);
		params.put("attachNo", attachNo);
		sqlSession.insert("com.comImage", params);
	}
	
	//회사ID로 이미지를 찾는 구문
	@Override
	public AttachDto findImage(String comId) {
		return sqlSession.selectOne("com.findImage", comId);
	}
	
}
