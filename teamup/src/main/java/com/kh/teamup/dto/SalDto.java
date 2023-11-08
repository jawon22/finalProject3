package com.kh.teamup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "급여 정보 객체")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SalDto {
		private int empNo;
		private float salAnnual;
		private float salTime;
}
