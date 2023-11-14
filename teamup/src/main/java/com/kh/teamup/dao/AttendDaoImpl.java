package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttendDto;
import com.kh.teamup.vo.AttendWorkingSearchVO;
import com.kh.teamup.vo.AttendWorkingTimesVO;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

@Repository
public class AttendDaoImpl implements AttendDao {

	@Autowired SqlSession sqlSession;

	//출근
	@Override
	public void insert(AttendDto attendDto) {
		sqlSession.insert("attend.save", attendDto);
	}
	
	//퇴근
	@Override
	public void update(int empNo, AttendDto attendDto) {
		Map<String, Object> param = Map.of("empNo", empNo, "attendDto", attendDto);
		sqlSession.update("attend.update", param);
	}

	@Override
	public List<AttendWorkingTimesVO> selectListByEmpNo(AttendWorkingSearchVO VO) {
		return sqlSession.selectList("attend.selectListByEmpNo",VO);
	}
	
	//사원별 월별 총근무시간 계산
	@Override
	public int totalWorkingTimeByMonth(TotalWorkingTimeByMonthVO vo) {
		return sqlSession.selectOne("attend.selectByEmpTotalWorkingTime",vo);
	}
	

	
}
