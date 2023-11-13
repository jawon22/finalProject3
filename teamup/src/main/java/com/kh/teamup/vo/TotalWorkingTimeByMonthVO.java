package com.kh.teamup.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class TotalWorkingTimeByMonthVO {
	private int empNo;
	private String yearMonth;
	
}
