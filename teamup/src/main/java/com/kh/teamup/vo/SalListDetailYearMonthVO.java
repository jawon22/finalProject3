package com.kh.teamup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SalListDetailYearMonthVO {
	private int salListNo, empNo;
	private int salListTotal, salListHealth, salListLtcare, salListNational, salListEmp, salListWork, salListLocal;
	private int attendNo, attendEmpNo;
	private String attendMonth;
}
