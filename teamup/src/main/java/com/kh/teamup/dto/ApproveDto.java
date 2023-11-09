package com.kh.teamup.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ApproveDto {
	private int apprNo;
	private int apprSender;
	private int deptNo;
	private String apprTitle, apprContent;
	private Date apprDate;
	private String apprDivision;
}
