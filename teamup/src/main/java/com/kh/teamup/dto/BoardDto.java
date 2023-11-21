package com.kh.teamup.dto;



import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor 
public class BoardDto {
	private long boardNo;
	private int empNo;
	private String comId, boardTitle, boardContent;
	private Timestamp boardWriteDate, boardUpdateDate;
	private String boardCategory;
	private int boardReadCount;
}
