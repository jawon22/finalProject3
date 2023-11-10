package com.kh.teamup.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.SalListDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SalListDaoImpl implements SalListDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(SalListDto salListDto) {
		sqlSession.insert("salList.save", salListDto);
	}

}
