package com.kh.teamup.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data @Builder @AllArgsConstructor @NoArgsConstructor 
public class ReplyDto {
		private long replyNo;
		private int replyWriter;
		private String replyContent;
		private Timestamp replyTime;
		private long replyOrigin;
}
