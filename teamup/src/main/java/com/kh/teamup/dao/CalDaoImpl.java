package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.EmpCalDto;

@Repository
public class CalDaoImpl implements CalDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<EmpCalDto> empCalList(int empNo) {
		return sqlSession.selectList("empcal.empCalList",empNo);
	}
	@Override
	public void insert(EmpCalDto empCalDto) {
		sqlSession.insert("empcal.add", empCalDto);
	}
}
