package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.EmpDto;
import com.kh.teamup.vo.EmpComplexSearchVO;
import com.kh.teamup.vo.EmpSearchBydeptComVO;
import com.kh.teamup.vo.SearchVO;

public interface EmpDao {
	
	int sequence();//시퀀스
	
	void addEmp(EmpDto empDto);
	//login 아이디 생성
	void updateEmpId(int empNo,EmpDto empDto);
	//전체 조회 
	List<EmpDto> empList();
	void deleteEmp(int empNo);
	void empInfoUpdate(int empNo, EmpDto empDto);
	List<EmpComplexSearchVO> complexSearch(EmpComplexSearchVO VO);
	
	
	EmpDto selecOne(String empId);
	EmpDto selectIdByNo(int empNo);
	
	
	List<SearchVO> search(SearchVO searchVO);
	EmpDto selectOne(int empNo);

	List<EmpSearchBydeptComVO> selectListByDeptCom(EmpSearchBydeptComVO empSearchBydeptComVO);

	void updateDept(String empId, EmpDto empDto);

	void changeEmpId(String empId);

	void updateExit(String empId, EmpDto empDto);
	
	

}
