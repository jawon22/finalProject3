package com.kh.teamup.vo;

import com.kh.teamup.dto.ApproveDto;
import com.kh.teamup.dto.ApprovePathDto;
import com.kh.teamup.dto.ReceiversDto;
import com.kh.teamup.dto.ReferrersDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ApproveInputVO {
	private ApproveDto approveDto;
	private ApprovePathDto approvePathDto;
	private ReceiversDto receiversDto;
	private ReferrersDto referrersDto;
}
