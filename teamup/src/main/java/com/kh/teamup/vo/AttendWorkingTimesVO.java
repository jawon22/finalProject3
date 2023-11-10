package com.kh.teamup.vo;

import java.sql.Date;

import com.kh.teamup.dto.AttendDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data @Getter
public class AttendWorkingTimesVO {
	private int attendNo; //근태번호 
	private int empNo; //사번 번호 검색할 때 필요한 외래키
	private Date Dt, attendBeginDate, attendEndDate;
	private Date attendStart, attendEnd;
	private int workingTimes; //근무시간
}
