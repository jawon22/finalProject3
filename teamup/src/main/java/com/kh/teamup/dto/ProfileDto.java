package com.kh.teamup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class ProfileDto {
	private int profileNo;
	private int empNo;
	private String profileTitle;
	private String profileContent;
}