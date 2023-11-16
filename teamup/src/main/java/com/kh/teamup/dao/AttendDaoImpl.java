package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttendDto;
import com.kh.teamup.vo.AttendWorkingSearchVO;
import com.kh.teamup.vo.AttendWorkingSysdateVO;
import com.kh.teamup.vo.AttendWorkingTimesVO;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

@Repository
public class AttendDaoImpl implements AttendDao {

	@Autowired SqlSession sqlSession;
	
	//근태번호 시퀀스
	@Override
	public int sequence() {
		return sqlSession.selectOne("attend.sequence");
	}

	//출근
	@Override
	public void insert(AttendDto attendDto) {
//		sqlSession.insert("attend.save", empNo);
		sqlSession.insert("attend.save", attendDto);
	}
	
	///empNo를 이용하여 오늘자 출근내역을 불러오는 명령
	@Override
	public AttendDto findTodayAttendByEmpNo(int empNo) {
		return sqlSession.selectOne("findTodayAttendByEmpNo", empNo);
	}
	
	//퇴근
//	@Override
//	public void update(int empNo, AttendDto attendDto) {
//		Map<String, Object> param = Map.of("empNo", empNo, "attendDto", attendDto);
//		sqlSession.update("attend.update", param);
//	}
	
	//퇴근
	@Override
	public void update(AttendDto attendDto) {
		sqlSession.update("attend.update", attendDto);
	}
	
	//출퇴근기록 반환
	@Override
	public AttendDto findAttendNo(int attendNo) {
	    return sqlSession.selectOne("attend.findAttendNo", attendNo);
	}
	
	//목록(이번년도 이번월 1일부터~현재까지)
	@Override
	public List<AttendWorkingTimesVO> findSysdate(AttendWorkingSysdateVO VO) {
		return sqlSession.selectList("attend.findSysdate",VO);
	}

	//목록(사용자가 년도와 월 선택)
	@Override
	public List<AttendWorkingTimesVO> findSearch(AttendWorkingSearchVO VO) {
		return sqlSession.selectList("attend.findSearch",VO);
	}
	
	
	//사원별 월별 총근무시간 계산
	@Override
	public int totalWorkingTimeByMonth(TotalWorkingTimeByMonthVO vo) {
		return sqlSession.selectOne("attend.selectByEmpTotalWorkingTime",vo);
	}

}
