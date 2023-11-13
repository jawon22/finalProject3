package com.kh.teamup.vo;

import java.sql.Date;

import com.kh.teamup.dto.SalListDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SalListDetailVO {
	private Date yearMonth;
	private SalListDto salListDto;
	private int actualSal;//실수령액
}
