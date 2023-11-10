package com.kh.teamup.restcontroller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.teamup.dao.AttachDao;
import com.kh.teamup.dao.ProfileDao;
import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.ProfileDto;
import com.kh.teamup.vo.ProfileImageVO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "회원프로필" , description = "회원프로필CRUD")

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/profile")
public class ProfileRestController {
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private AttachDao attachDao;
	
	//프로필 등록(+파일 업로드)
	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void addProfile(@ModelAttribute ProfileImageVO vo) throws IllegalStateException, IOException {
//		log.debug("dto={}", vo);
		
		int profileNo = profileDao.sequence();//profileNo를 가져옴
//		log.debug("profileNo={}", profileNo);
		
		
		
		ProfileDto profileDto = vo.getProfileDto();
		profileDto.setProfileNo(profileNo);
		profileDao.addProfile(profileDto);
		
		
		MultipartFile attach = vo.getAttach();
		
		int attachNo = attachDao.sequence();
		
		String home = System.getProperty("user.home");
		File dir = new File(home, "upload");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(attachNo));
		attach.transferTo(target);
		
		AttachDto attachDto = new AttachDto();
		attachDto.setAttachNo(attachNo);
		attachDto.setAttachName(attach.getOriginalFilename());
		attachDto.setAttachSize(attach.getSize());
		attachDto.setAttachType(attach.getContentType());
		attachDao.insert(attachDto);
		log.debug("attach={}", attachDto);
		log.debug("f={}", profileDto);
		
		profileDao.connectProfile(profileDto.getProfileNo(), attachNo);
	}

}
