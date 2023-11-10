package com.kh.teamup.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ReceiversDto {
	private int receiversNo;
	private int pathNo;
	private int receiversReceiver;
	private Integer receiversReferrer; // null이 허용되므로 Integer로
	private String receiversStatus;
	private Date ReceiversConfirmTime;
	private String receiversReturnRs;
}
