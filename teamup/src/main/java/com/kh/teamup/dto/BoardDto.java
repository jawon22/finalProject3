package com.kh.teamup.dto;




import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(description = "공지사항 정보 객체")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data @Builder @AllArgsConstructor @NoArgsConstructor 
public class BoardDto {
	private long boardNo;
	private int empNo,deptNo;
	private String comId, boardTitle, boardContent;
	private Timestamp boardWriteDate, boardUpdateDate;
	private int boardReadCount, boardReplyCount;
}
