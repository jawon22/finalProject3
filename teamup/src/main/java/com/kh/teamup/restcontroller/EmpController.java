package com.kh.teamup.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.EmpDao;
import com.kh.teamup.dto.EmpDto;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "사원 관리" ,description = "사원 CRUD") 

@RestController
@CrossOrigin
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	private EmpDao empDao;
	
	
	@PostMapping("/")
	public void addEmp(@RequestBody EmpDto empDto) {
		empDao.addEmp(empDto);
		
	}
}