package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.teamup.dto.SalDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SalDaoImpl implements SalDao{

	@Autowired
	private SqlSession sqlSession;

	@Override//저장
	public void insert(SalDto salDto) {
		sqlSession.insert("sal.add", salDto);
	}
	
	@Override//조회
	public List<SalDto> selectList(){
		return sqlSession.selectList("sal.list");
	}
	
	@Override
	public SalDto selectOne(int empNo) {
		return sqlSession.selectOne("sal.find");
	}

	
	
	
}
