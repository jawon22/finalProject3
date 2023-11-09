package com.kh.teamup.dao;

import com.kh.teamup.dto.CompanyDto;

public interface CompanyDao {
	void addCom(CompanyDto companyDto);

	void connectCom(String comId, int attachNo);//회사와 회사이미지 연결
}
