package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.ProfileDto;
import com.kh.teamup.vo.ProfileImageVO;
import com.kh.teamup.vo.ProfileInfoVO;
import com.kh.teamup.vo.ProfileUpdateVO;

public interface ProfileDao {
	
	int sequence(); //시퀀스

	void addProfile(ProfileDto profileDto);//프로필 등록할 때 이미지 함께 등록

	void connectProfile(int profileNo, int attachNo);//프로필과 이미지 연결

	AttachDto findImage(int profileNo);//프로필 이미지 화면에 출력

	boolean updateProfile(ProfileInfoVO profileInfoVO, int empNo);//프로필+프로필이미지 수정
	
	boolean updateEmp(ProfileInfoVO profileInfoVO, int empNo);//프로필에 받는 회원정보 수정

	List<ProfileInfoVO> selectList(int empNo);


	
}
