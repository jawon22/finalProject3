package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ApproveDto;

@Repository
public class ApproveDaoImpl implements ApproveDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() { //시퀀스
		return sqlSession.selectOne("approve.sequence");
	}
	
	@Override
	public ApproveDto selectOne(int apprNo) { //하나 조회
		return sqlSession.selectOne("approve.find",apprNo);
	}
	
	@Override
	public List<ApproveDto> selectList() { //모두 조회
		return sqlSession.selectList("approve.approveList");
	}
	
	@Override
	public void insert(ApproveDto approveDto) {  //결재 등록
		sqlSession.insert("approve.approveSave",approveDto);
	}
	
	@Override
	public boolean delete(int empNo) { //결재 삭제(취소)
		return sqlSession.delete("approve.approveCancel",empNo)>0;
	}
	
}
