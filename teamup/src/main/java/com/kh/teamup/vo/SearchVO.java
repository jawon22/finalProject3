package com.kh.teamup.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SearchVO {
	private String comId;
	private String comName;
	private int deptNo;
	private int empNo;
	private String deptName;
	private String empName;
	private String empId;
	private String empPositionName;
	private int empPositionNo;
	private String empEmail;
	private String empTel;
	private int salAnnual;
	private Date empJoin;
	
	
	private String select;
	private String keyword;
	private int salMax;//sal<salmax
	private int salMin;//sal>salmin
	
	private Date joinStart;
	private Date joinEnd;
	
	

}
