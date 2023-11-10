package com.kh.teamup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ReferrersDto {

	private int referrersNo;
	private int pathNo;
	private Integer referrersReferrer;//null도 허용이 되야함
}
