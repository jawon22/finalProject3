package com.kh.teamup.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "급여내역 정보 객체")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SalListDto {
	private int salListNo;
	private int empNo;
	private int salListTotal;
	private int salListHealth;
	private int salListLtcare;
	private int salListNational;
	private int salListEmp;
	private int salListWork;
	private int salListLocal;
	private String salListDate;
}
