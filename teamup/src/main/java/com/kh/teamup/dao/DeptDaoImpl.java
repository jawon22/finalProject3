package com.kh.teamup.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.DeptDto;
import com.kh.teamup.vo.DeptVo;
@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addDept(DeptDto deptDto) {
		sqlSession.insert("dept.addDept",deptDto);
	}
	
	
	@Override
	public List<DeptVo> deptList(String comId) {
		return sqlSession.selectList("dept.listByCompany",comId);
	}
	
	@Override
	public void deptUpdate(int deptNo ,DeptDto deptDto) {
		Map<String, Object> params =Map.of("deptNo",deptNo,"deptDto",deptDto);
	
		
		sqlSession.update("dept.update", params);
	}
}
