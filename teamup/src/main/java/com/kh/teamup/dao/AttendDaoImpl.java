package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttendDto;

@Repository
public class AttendDaoImpl implements AttendDao {

	@Autowired SqlSession sqlSession;

	//출근
	@Override
	public void insert(AttendDto attendDto) {
		sqlSession.insert("attend.save", attendDto);
	}
	
	//퇴근
	@Override
	public void update(int empNo, AttendDto attendDto) {
		Map<String, Object> param = Map.of("empNo", empNo, "attendDto", attendDto);
		sqlSession.update("attend.update", param);
	}
	
}
