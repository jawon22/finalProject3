package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.teamup.dto.SalDto;
import com.kh.teamup.error.NoTargetException;

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
	
	@Override//상세
	public SalDto selectOne(int empNo) {
		return sqlSession.selectOne("sal.find", empNo);
	}
	
	@Override//수정
	public void edit(int empNo, SalDto salDto) {
		Map<String, Object> param = Map.of("empNo", empNo, "salDto", salDto);
		int result = sqlSession.update("sal.change", param);
		if(result == 0) throw new NoTargetException();
	}

	
	
	
}
