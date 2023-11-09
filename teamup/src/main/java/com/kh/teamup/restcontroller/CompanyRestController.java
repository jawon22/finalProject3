package com.kh.teamup.restcontroller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.teamup.VO.CompanyImageVO;
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
	
	
	//이미지 등록하면서 회사 정보 등록 같이 하니까 일단 주석 처리 했습니당~
//	@PostMapping("/")
//	public void addCom(@RequestBody CompanyDto companyDto) {
//		
//		companyDao.addCom(companyDto);
//		
//	}
	
	//회사 등록(+파일 업로드)
	@PostMapping(value = "/image/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void addCom(@ModelAttribute CompanyImageVO vo) throws IllegalStateException, IOException {
		log.debug("dto={}", vo);
		
		CompanyDto companyDto = vo.getCompanyDto();
		companyDao.addCom(companyDto);
		
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
		
		companyDao.connectCom(companyDto.getComId(), attachNo);
		log.debug("attach={}", attach);
		
	} 
	
	//파일 다운로드
	

}
