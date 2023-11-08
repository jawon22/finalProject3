package com.kh.teamup.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor 
public class AttendDto {
	private int empId; //사원번호 외래키
	private int attendNo; //근태번호 프키
	private Date attendStart; //출근시간
	private Date attendEnd; //퇴근시간
}
