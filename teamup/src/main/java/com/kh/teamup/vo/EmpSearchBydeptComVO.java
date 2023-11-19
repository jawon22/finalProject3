package com.kh.teamup.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmpSearchBydeptComVO {
	private String empPositionName;
	private String empPositionNo;
	private String empName;
	private String empTel;
	private String empEmail;
	private String empId;
	private String comId;
	private Date empJoin;
	private Date empExit;
	private int deptNo;

}
