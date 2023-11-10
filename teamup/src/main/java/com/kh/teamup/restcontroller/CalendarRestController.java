package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.CalDao;
import com.kh.teamup.dto.EmpCalDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "캘린더" , description = "일정 CRUD")
@RestController
@CrossOrigin
@RequestMapping("/cal_emp")
@Slf4j
public class CalendarRestController {
	@Autowired
	private CalDao calDao;
	
	@GetMapping("/list/{empNo}")
	public List<EmpCalDto> calList(@PathVariable int empNo){
		return calDao.empCalList(empNo);
		
	}
	
	@PostMapping("/")
	public void addcal(@RequestBody EmpCalDto empCalDto) {
		
		
		log.debug("Dto={}", empCalDto);
		calDao.insert(empCalDto);
	}
	

}
