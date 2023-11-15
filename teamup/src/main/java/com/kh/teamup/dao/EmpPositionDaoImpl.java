package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.EmpPositionDto;

@Repository
public class EmpPositionDaoImpl implements EmpPositionDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<EmpPositionDto> selectList() {
		return sqlSession.selectList("position.list");
	}

	@Override
	public void addPosition(EmpPositionDto empPositionDto) {
		sqlSession.insert("position.addPosition",empPositionDto);
	}
	
	@Override
	public List<EmpPositionDto> listByCom(String comId) {
		return sqlSession.selectList("position.listByCom",comId);
	}
	
}
