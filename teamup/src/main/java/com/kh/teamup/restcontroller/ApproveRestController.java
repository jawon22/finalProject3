package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.ApproveDao;
import com.kh.teamup.dto.ApproveDto;

@CrossOrigin
@RestController
@RequestMapping("/approve")
public class ApproveRestController {

	@Autowired
	private ApproveDao approveDao;
	
	
	//조회
	@GetMapping("/")
	public List<ApproveDto> list(){
		return approveDao.selectList();
	}
	
}
