package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.AttendDto;
import com.kh.teamup.vo.AttendWorkingSearchVO;
import com.kh.teamup.vo.AttendWorkingSysdateVO;
import com.kh.teamup.vo.AttendWorkingTimesVO;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

public interface AttendDao {
	//근태번호 시퀀스
	public int sequence();
	//출근(등록)
	void insert(AttendDto attendDto);
	///empNo를 이용하여 오늘자 출근내역을 불러오는 명령
	public AttendDto findTodayAttendByEmpNo(int empNo);
	//퇴근(수정)
//	void update(int attendNo, AttendDto attendDto);
	void update(AttendDto attendDto);
	//근태번호로 근태 상세 조회
	AttendDto findAttendNo(int attendNo); 
	//목록(이번년도 이번월 1일부터~현재까지)
	List<AttendWorkingTimesVO>findSysdate(AttendWorkingSysdateVO VO);	
	//목록(사용자가 년도와 월 선택)
	List<AttendWorkingTimesVO>findSearch(AttendWorkingSearchVO VO);	
	
	int totalWorkingTimeByMonth(TotalWorkingTimeByMonthVO vo);
}
