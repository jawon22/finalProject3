package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ProfileInfoVO {
	private int attachNo;
	private int profileNo;
	private int empNo;
	private String deptName;
	private String empPositionName;
	private String empName;
	private String empTel;
	private String empEmail;
	private String empJoin;
	private String profileTitle;
	private String profileContent;
}
