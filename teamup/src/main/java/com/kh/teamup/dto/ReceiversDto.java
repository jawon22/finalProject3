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
	private String receiversStatus;
	private Date ReceiversConfirmTime;
	private String receiversReturnRs;
}
