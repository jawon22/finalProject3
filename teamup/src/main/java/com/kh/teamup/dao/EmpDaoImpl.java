package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.EmpDto;
@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void addEmp(EmpDto empDto) {
		sqlSession.insert("emp.addEmp",empDto);
	}
	
	
	
	
	
	//메일 전송할때 실횅할코드 아이디 생성 이거랑 비밀번호 랜던? 아니면 초기 비밀번호 생성으로 해서 사용
	@Override
	public void updateEmpId(int empNo, EmpDto empDto) {
		Map<String, Object> params = Map.of("empNo",empNo ,"empDto",empDto);
		//int result =sqlSession.update("emp.updateEmpId",params);
		sqlSession.update("emp.updateEmpId",params);
		//if(result==0) throw new NotargetException();
	}
	
	@Override
	public List<EmpDto> empList() {
		
		return sqlSession.selectList("emp.empList");
	}
	
	@Override
	public void deleteEmp(int empNo) {
		sqlSession.delete("emp.deleteEmp",empNo);
	}
	
	@Override
	public void empInfoUpdate(int empNo, EmpDto empDto) {
		Map<String, Object> params = Map.of("empNo",empNo ,"empDto",empDto);
		//int result =sqlSession.update("emp.updateEmpId",params);
		sqlSession.update("emp.empInfoUpdate",params);		
	}
	
}
