package com.kh.teamup.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.teamup.dao.AttachDao;
import com.kh.teamup.dao.CompanyDao;
import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.CompanyDto;
import com.kh.teamup.vo.CompanyImageVO;

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
	@GetMapping("/image/{comId}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable String comId) throws IOException{
		
		System.out.println("comId = " + comId);
		AttachDto attachDto = companyDao.findImage(comId);
		System.out.println("attachDto = " + attachDto);
		
		String home = System.getProperty("user.home");
		File dir = new File(home, "upload");
		File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
		
		byte[] data = FileUtils.readFileToByteArray(target);//실제 파일 정보 불러오기
		ByteArrayResource resource = new ByteArrayResource(data);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
				.contentLength(attachDto.getAttachSize())
				.header(HttpHeaders.CONTENT_DISPOSITION, 
						ContentDisposition.attachment()
							.filename(attachDto.getAttachName(), StandardCharsets.UTF_8)
							.build().toString()
						)
				.body(resource);
		
	}
	
	

}
