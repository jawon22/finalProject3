package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.SalListDto;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SalListDaoImpl implements SalListDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override//저장
	public void insert(SalListDto salListDto) {
		sqlSession.insert("salList.save", salListDto);
	}
	
	@Override//삭제
	public boolean delete(int empNo) {
		return sqlSession.delete("salList.remove", empNo) > 0;
	}
	
	@Override//사원별 급여내역 상세
	public List<SalListDto> findByEmpSalList(int salListNo) {
		List<SalListDto> list = sqlSession.selectList("salList.findByEmpSalList", salListNo);
		return list;
	}
	
	@Override
	public List<TotalWorkingTimeByMonthVO> findByEmpMonthSalList(TotalWorkingTimeByMonthVO vo) {
		return sqlSession.selectOne("salList.findByEmpMonthSalList", vo);
	}
	
	
	@Override//사원별 급여내역 목록
	public List<SalListDto> findByEmpNo(int empNo) {
		List<SalListDto> list = sqlSession.selectList("salList.findByEmpNo", empNo);
		return list;
	}
	


}
