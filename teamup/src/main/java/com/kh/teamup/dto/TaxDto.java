package com.kh.teamup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "세금 정보 객체")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TaxDto {
	private int taxNo;
	private String taxName;
	private float taxRate;
}
