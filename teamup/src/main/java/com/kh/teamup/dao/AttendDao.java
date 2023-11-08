package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.AttendDto;

public interface AttendDao {
	//사번으로 근태목록 조회
	List<AttendDto>selectListByEmpId(int empId);	
	//날짜 구간으로 검색
	//?
	//출근(등록)
	void insert(AttendDto attendDto);
	//퇴근(수정)
	void update(int attendNo, AttendDto attendDto);
}
