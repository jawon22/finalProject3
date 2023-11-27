package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		calDao.insert(empCalDto);
	}
	
	@PutMapping("/updateCal/{calNo}")
	public void updateCal(@PathVariable int calNo,@RequestBody EmpCalDto empCalDto) {
		calDao.updateCal(calNo, empCalDto);
	}
	
	@GetMapping("/detail/{calNo}")
	public EmpCalDto detail(@PathVariable int calNo) {
		return calDao.selectOne(calNo);
	}
	
	@DeleteMapping("/delete/{calNo}")
	public void deleteCal(@PathVariable int calNo) {
		
		calDao.deleteCal(calNo);
		
	}
	
	
	///부서 별?
	
	@PostMapping("/dpetadd/")
	public void deptAdd(@RequestBody EmpCalDto empCalDto) {
		
		calDao.deptadd(empCalDto);
		
		
	}
	@GetMapping("deptList/{deptNo}")
	public List<EmpCalDto> deptCalList(@PathVariable int deptNo){
		return calDao.deptCalList(deptNo);
	}
	
	@PutMapping("/updateDeptCal/{calNo}")
	public void updateDeptCal(@PathVariable int calNo,@RequestBody EmpCalDto empCalDto) {
		calDao.updateDeptCal(calNo, empCalDto);
	}
	
	@GetMapping("/deptDetail/{calNo}")
	public EmpCalDto deptDetail(@PathVariable int calNo) {
		return calDao.deptDetail(calNo);
	}
	
	@DeleteMapping("/deleteDeptCal/{calNo}")
	public void deleteDeptCal(@PathVariable int calNo) {
		
		calDao.deleteDeptCal(calNo);
		
	}
	

}
