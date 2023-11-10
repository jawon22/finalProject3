package com.kh.teamup.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.teamup.dto.CompanyDto;

import lombok.Data;

//회사 이미지 등록을 위한 VO
@Data
public class CompanyImageVO {
	private MultipartFile attach;
	private String comId;
	private String comPw;
	private String comName;
	private String comPost;
	private String comAddr;
	private String comTel;
	private String comBs;
	private String comRegion;
	private String comEmail;
	
	@JsonIgnore
	public CompanyDto getCompanyDto() {
		return CompanyDto.builder()
					.comId(comId)
					.comPw(comPw)
					.comName(comName)
					.comPost(comPost)
					.comAddr(comAddr)
					.comTel(comTel)
					.comBs(comBs)
					.comRegion(comRegion)
					.comEmail(comEmail)
				.build();
	}
}
