
package com.kh.teamup.dao;

import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.CompanyDto;
import com.kh.teamup.vo.CompanyImageVO;

public interface CompanyDao {
	void addCom(CompanyDto companyDto);//회사 등록할 때 이미지 함께 등록

	void connectCom(String comId, int attachNo);//회사와 회사이미지 연결

	AttachDto findImage(String comId);//회사 이미지 화면에 출력

	boolean update(CompanyImageVO companyImageVO);//회사+회사이미지 수정

	CompanyDto selectOne(String comId);

}