package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmpSearchBydeptComVO {
	private String empPositionName;
	private String empName;
	private String empTel;
	private String empEmail;
	private String empId;

}
