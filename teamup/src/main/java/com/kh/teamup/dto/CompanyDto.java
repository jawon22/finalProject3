package com.kh.teamup.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompanyDto {
	private String comId;
	private String comPw;
	private String comName;
	private String comPost;
	private String comAddr;
	private String comAddr2;
	private String comTel;
	private String comBs;
	private String comRegion;
	private String comPayStatus;
	private String comEmail;
	private Date comJoinDate;
}