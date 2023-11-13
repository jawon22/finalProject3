package com.kh.teamup.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProfileInfoVO {
	private int profileNo;
	private int empNo;
	private String deptName;
	private String empPositionName;
	private String empName;
	private String empTel;
	private String empEmail;
	private String empJoin;
}
