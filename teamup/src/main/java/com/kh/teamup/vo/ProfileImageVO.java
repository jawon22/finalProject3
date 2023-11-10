package com.kh.teamup.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.teamup.dto.ProfileDto;

import lombok.Data;

@Data
public class ProfileImageVO {
	private MultipartFile attach;
//	private int profileNo;
	private int empNo;
	private String profileTitle;
	private String profileContent;
	
	@JsonIgnore
	public ProfileDto getProfileDto() {
		return ProfileDto.builder()
//					.profileNo(profileNo)
					.empNo(empNo)
					.profileTitle(profileTitle)
					.profileContent(profileContent)
				.build();
				
	}


	
}
