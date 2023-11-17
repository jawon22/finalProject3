package com.kh.teamup.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.ProfileDto;

import lombok.Data;

@Data
public class ProfileUpdateVO {
	private MultipartFile attach;
	private String empTel;
	private String empEmail;
	private String profileTitle;
	private String profileContent;
	
	@JsonIgnore
	public ProfileInfoVO getProfileInfoVO() {
		return ProfileInfoVO.builder()
					.empTel(empTel)
					.empEmail(empEmail)
					.profileTitle(profileTitle)
					.profileContent(profileContent)
				.build();
				
	}


	
}
