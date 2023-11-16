package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

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
		return sqlSession.selectList("empcal.empCalList", empNo);
	}

	@Override
	public void insert(EmpCalDto empCalDto) {
		sqlSession.insert("empcal.add", empCalDto);
	}

	@Override
	public EmpCalDto selectOne(int calNo) {
		// 사월별

		return sqlSession.selectOne("empcal.empCalDetail", calNo);
	}

	@Override
	public void updateCal(int calNo, EmpCalDto empCalDto) {
		Map<String, Object> params = Map.of("calNo", calNo, "empCalDto", empCalDto);
		sqlSession.update("empcal.update", params);
	}

	@Override
	public void deleteCal(int calNo) {
		sqlSession.delete("empcal.deleteCal", calNo);
	}
	
	////////------------------------------------------------------

	@Override
	public void deptadd(EmpCalDto empCalDto) {
		sqlSession.insert("deptcal.add",empCalDto);
	}

	@Override
	public List<EmpCalDto> deptCalList(int deptNo) {
		return sqlSession.selectList("deptcal.deptCalList",deptNo);

	}
	
	
	
	@Override
	public EmpCalDto deptDetail(int calNo) {
		// 사월별

		return sqlSession.selectOne("deptcal.deptCalDetail", calNo);
	}

	@Override
	public void updateDeptCal(int calNo, EmpCalDto empCalDto) {
		Map<String, Object> params = Map.of("calNo", calNo, "empCalDto", empCalDto);
		sqlSession.update("deptcal.updateDept", params);
	}

	@Override
	public void deleteDeptCal(int calNo) {
		sqlSession.delete("deptcal.deleteDeptCal", calNo);
	}
	
	
}
