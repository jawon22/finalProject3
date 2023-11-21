package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class EmpComplexSearchVO {
	private String comId;
	private String comName;
	private int deptNo; 
	private String deptName;
	private String empPositionName;
	private String empName; 
	private String empId;
	private String empTel;
	private String empEmail;
	private int empNo;
	
}
