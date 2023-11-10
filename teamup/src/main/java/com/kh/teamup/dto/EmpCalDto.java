package com.kh.teamup.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmpCalDto {
	
	private int calNo;
	private int empNo;
	private Date calStartDate;
	private Date calEndDate;
	private String calTitle;
	private String calContent;
	private String calStatus;
	
	

}
