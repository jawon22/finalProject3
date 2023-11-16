package com.kh.teamup.dto;

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
	private String apprDateStart, apprDateEnd;
	private String apprDivision;
}
