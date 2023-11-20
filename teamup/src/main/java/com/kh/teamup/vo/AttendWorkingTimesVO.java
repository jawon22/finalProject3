package com.kh.teamup.vo;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class AttendWorkingTimesVO {
	private int attendNo; //근태번호 
	private int empNo; //사번 번호 검색할 때 필요한 외래키
	private Date dt;
	private Timestamp attendStart, attendEnd;
	private int workingTimes; //근무시간
	private String attendStatus; //근무상태
}
