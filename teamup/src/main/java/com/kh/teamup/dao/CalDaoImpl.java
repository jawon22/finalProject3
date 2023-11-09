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
	public List<EmpCalDto> empList(int empNo) {
		
		
		return sqlSession.selectList("cal.empList",empNo);
	}
}
