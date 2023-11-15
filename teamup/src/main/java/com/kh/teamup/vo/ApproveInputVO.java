package com.kh.teamup.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kh.teamup.dto.ApproveDto;
import com.kh.teamup.dto.ApprovePathDto;
import com.kh.teamup.dto.ReceiversDto;
import com.kh.teamup.dto.ReferrersDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ApproveInputVO {
	private ApproveDto approveDto;
	private ApprovePathDto approvePathDto;
	private List<ReceiversDto> receiversDto;
	private List<ReferrersDto> referrersDto;
}
