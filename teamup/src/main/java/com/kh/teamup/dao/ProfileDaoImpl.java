package com.kh.teamup.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.ProfileDto;
import com.kh.teamup.vo.ProfileImageVO;
import com.kh.teamup.vo.ProfileInfoVO;
import com.kh.teamup.vo.ProfileUpdateVO;

@Repository
public class ProfileDaoImpl implements ProfileDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("emp_profile.sequence");
	}
	
	//empNo로 profileNo 찾기
	@Override
	public int findProfileNo(int empNo) {
		return sqlSession.selectOne("emp_profile.findProfileNo", empNo);
	}
	
	//프로필 정보 등록(+이미지)
	@Override
	public void addProfile(ProfileDto profileDto) {
		sqlSession.insert("emp_profile.addProfile", profileDto);
	}
	
	//프로필과 이미지 연결
	@Override
	public void connectProfile(int empNo, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("empNo", empNo);
		params.put("attachNo", attachNo);
		sqlSession.insert("emp_profile.profileImage", params);
	}
	
	//empNo로 이미지를 찾는 구문
	@Override
	public AttachDto findImage(int empNo) {
		return sqlSession.selectOne("emp_profile.findImage", empNo);
	}
	
	//프로필 정보 + 이미지 수정 구문
	@Override
	public boolean updateProfile(ProfileInfoVO profileInfoVO, int empNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("vo", profileInfoVO);
		params.put("empNo", empNo);
		return sqlSession.update("emp_profile.editProfile", params) > 0;
	}
	
	@Override
	public boolean updateEmp(ProfileInfoVO profileInfoVO, int empNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("vo", profileInfoVO);
		params.put("empNo", empNo);
		return sqlSession.update("emp_profile.editEmp", params) > 0;
	}
	
	//전체회원 프로필 조회
	@Override
	public List<ProfileInfoVO> selectList() {
		return sqlSession.selectList("emp_profile.joinProfileAll");
	}
	
	//회원번호에 따른 회원 프로필 조회
	@Override
	public ProfileInfoVO selectOne(int empNo) {
		return sqlSession.selectOne("emp_profile.joinProfileOne", empNo);
		
	}

}
