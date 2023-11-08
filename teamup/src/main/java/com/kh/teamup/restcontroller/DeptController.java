package com.kh.teamup.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.DeptDao;
import com.kh.teamup.dto.DeptDto;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "부서관리" , description = "부서CRUD")
@RestController
@CrossOrigin
@RequestMapping("dept")
public class DeptController {
	@Autowired
	private DeptDao deptDao;
	
	@PostMapping("/")
	public void addDept(@RequestBody DeptDto deptDtp) {
		deptDao.addDept(deptDtp);
	}

}
