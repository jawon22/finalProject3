package com.kh.teamup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class EmpDto {
	private String comId;
	private int deptNo;
	private int empNo;
	private String empName;
	private String empId;
	private String empPw;
	private String empTel;
	private String empJoin;
	private String empExit;
	private int empPositionNo;
	private String empEmail;
}
