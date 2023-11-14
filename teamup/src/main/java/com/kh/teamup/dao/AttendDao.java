package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.AttendDto;
import com.kh.teamup.vo.AttendWorkingSearchVO;
import com.kh.teamup.vo.AttendWorkingSysdateVO;
import com.kh.teamup.vo.AttendWorkingTimesVO;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

public interface AttendDao {
	//사번으로 근태목록 조회
	//출근(등록)
	void insert(AttendDto attendDto);
	//퇴근(수정)
	void update(int attendNo, AttendDto attendDto);
	//현재 날짜를 받아서 현재 달의 1일부터 현재 달의 오늘일까지 보여주는 구문
	List<AttendWorkingTimesVO>findSysdate(AttendWorkingSysdateVO VO);	
	//한 달 간격으로, 모두 나올 필요가 없음 애초에 가장 최근부터 한 달만 보이게, 날짜 선택이 가능하게
	List<AttendWorkingTimesVO>findSearch(AttendWorkingSearchVO VO);	
	
	int totalWorkingTimeByMonth(TotalWorkingTimeByMonthVO vo);
}
