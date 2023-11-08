package com.kh.teamup.restcontroller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.teamup.dao.AttachDao;
import com.kh.teamup.dto.AttachDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/attach")
public class AttachRestController {
	
	@Autowired
	private AttachDao attachDao;

//	@GetMapping - 조회
//	@PostMapping - 등록
//	@PutMapping - 전체 수정
//	@PatchMapping - 일부 수정
//	@DeleteMapping - 삭제
	
	//이미지 등록
	@PostMapping(value = "/image/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void upload(@RequestPart MultipartFile attach) {
//		log.debug("attach={}", attach);
				
	}
}
