package com.kh.teamup.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.CompanyDao;
import com.kh.teamup.dto.CompanyDto;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "회사" , description = "회사CRUD")

@CrossOrigin
@RestController
@RequestMapping("/com")
public class CompanyRestController {
	@Autowired
	private CompanyDao companyDao;
	
	@PostMapping("/")
	public void addCom(@RequestBody CompanyDto companyDto) {
		
		companyDao.addCom(companyDto);
		
	}

}
