package com.kh.teamup.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AttendWorkingSearchVO {
	private int empNo;
//	private String attendBeginDate, attendEndDate; //사용자가 검색할 날짜
	private String yearMonth; //사용자가 검색할 년,월
}
