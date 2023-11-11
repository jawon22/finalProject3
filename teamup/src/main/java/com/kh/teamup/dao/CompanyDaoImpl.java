package com.kh.teamup.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.CompanyDto;
import com.kh.teamup.vo.CompanyImageVO;

@Repository
public class CompanyDaoImpl implements CompanyDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addCom(CompanyDto companyDto) {
		sqlSession.insert("com.addCom",companyDto);
	}
	
	//회사와 이미지 연결
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
	
	//회사정보+이미지 수정 구문
	@Override
	public boolean update(CompanyImageVO companyImageVO) {
		return sqlSession.update("com.editCom", companyImageVO) > 0;
	}
	
	@Override
	public CompanyDto selectOne(String comId) {
		return sqlSession.selectOne("com.find",comId);
	}
	@Override
	public List<CompanyDto> selectList() {
		return sqlSession.selectList("com.list");
	}
		
	
}