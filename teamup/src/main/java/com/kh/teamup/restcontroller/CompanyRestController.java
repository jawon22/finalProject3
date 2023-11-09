package com.kh.teamup.restcontroller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.mybatis.MyBatisContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.teamup.dao.AttachDao;
import com.kh.teamup.dao.CompanyDao;
import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.CompanyDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "회사" , description = "회사CRUD")

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/com")
public class CompanyRestController {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private AttachDao attachDao;
	
	@PostMapping("/")
	public void addCom(@RequestBody CompanyDto companyDto) {
		
		companyDao.addCom(companyDto);
		
	}
	
	//회사이미지 등록
	@PostMapping(value = "/image/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void upload(@RequestPart MultipartFile attach, @RequestBody CompanyDto companyDto) throws IllegalStateException, IOException {
		log.debug("attach={}", attach);
		
		
		AttachDto attachDto = new AttachDto();
		
		String home = System.getProperty("user.home");
		File dir = new File(home, "upload");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
		attach.transferTo(target);
		
//		attachDto.setAttachNo(attachNo);
		attachDto.setAttachName(attach.getOriginalFilename());
		attachDto.setAttachSize(attach.getSize());
		attachDto.setAttachType(attach.getContentType());
		attachDao.insert(attachDto);
		
		//연결(파일이 있을때만)
		companyDao.connect(companyDto.getComId(), attachDto.getAttachNo());
		
	} 

}
