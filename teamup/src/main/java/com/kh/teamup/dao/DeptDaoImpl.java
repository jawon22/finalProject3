package com.kh.teamup.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.DeptDto;
@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addDept(DeptDto deptDto) {
		sqlSession.insert("dept.addDept",deptDto);
	}
}
