package com.kh.teamup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmpPositionDto {
	
	private int empPositionNo;
	private String empPositionName;
	private int empPositionOrder;

}
