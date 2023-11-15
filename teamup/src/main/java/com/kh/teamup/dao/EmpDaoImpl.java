package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.EmpDto;
import com.kh.teamup.vo.EmpComplexSearchVO;
import com.kh.teamup.vo.EmpSearchBydeptComVO;
import com.kh.teamup.vo.SearchVO;
@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("emp.sequence");
	}
	
	
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
	
	
	@Override
	public List<EmpComplexSearchVO> complexSearch(EmpComplexSearchVO VO) {
		
		return sqlSession.selectList("emp.complexSearch",VO);
	}
	
	@Override
	public EmpDto selecOne(String empId) {
		return sqlSession.selectOne("emp.selectOne",empId);
	}
	
	@Override
	public EmpDto selectIdByNo(int empNo) {
		return sqlSession.selectOne("emp.selectIdByNo",empNo);
	}
	
	@Override
	public List<SearchVO> search(SearchVO searchVO) {
		return sqlSession.selectList("search.search2", searchVO);
	}
	
	@Override
	public EmpDto selectOne(int empNo) {
		return sqlSession.selectOne("emp.findEmp",empNo);
	}
	
	@Override
	public List<EmpSearchBydeptComVO> selectListByDeptCom(EmpSearchBydeptComVO empSearchBydeptComVO) {
		return sqlSession.selectList("emp.selectBydeptAndCom",empSearchBydeptComVO);
	}
	
	@Override
	public void updateDept(String empId, EmpDto empDto) {
		Map<String, Object> params = Map.of("empId",empId ,"empDto",empDto);

		sqlSession.update("emp.changeDetp",params);
	}
	
	@Override
	public void changeEmpId(String empId) {
		sqlSession.update("emp.changeEmpId",empId);
	}
	
}
