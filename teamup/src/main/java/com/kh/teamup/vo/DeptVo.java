package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DeptVo {
	//부서 별 사원수 ? 
	private int deptNo;
	private String deptName;
	private String comId;
	private String comName;
	
	
	private int empCount;
	private int deptCount;

}
