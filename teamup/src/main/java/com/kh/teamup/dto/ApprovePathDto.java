package com.kh.teamup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ApprovePathDto {
	private int apprPathNo;
	private int apprNo;
	private int apprSender;
}
