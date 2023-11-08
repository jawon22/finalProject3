package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttendDto;

@Repository
public class AttendDaoImpl implements AttendDao {

	@Autowired SqlSession sqlSession;

	@Override
	public List<AttendDto> selectListByEmpId(int empId) {
		return sqlSession.selectList("attend.findByEmpId");
	}

	@Override
	public void insert(AttendDto attendDto) {
		sqlSession.insert("attend.save", attendDto);
	}

	@Override
	public void update(int attendNo, AttendDto attendDto) {
		Map<String, Object> param = Map.of("attendNo", attendNo, "attendDto", attendDto);
		sqlSession.update("attend.update", param);
	}
	
}
