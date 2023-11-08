package com.kh.teamup.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ApprovePathDto {
	private int apprPathNO;
	private int apprNo;
	private int apprSender;
	private String pathStatus;
	private Date pathConfirmTime;
	private String pathReturnRs;
	private int pathReceiver;
}
