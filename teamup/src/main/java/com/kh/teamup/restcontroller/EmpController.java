package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@PatchMapping("/{empNo}")
	public void updateEmpId(@RequestBody EmpDto empDto,@PathVariable int empNo) {
		
		empDao.updateEmpId(empNo, empDto);
		
	}
	
	@GetMapping("/")
	public List<EmpDto> empList(){
		return empDao.empList();
	}
	
	@DeleteMapping("/{empNo}")
	public void deleteEmp(@PathVariable int empNo) {
		empDao.deleteEmp(empNo);
	}
	
	@PatchMapping("/empInfoUpdate/{empNo}")
	public void empInfoUpdate(@RequestBody EmpDto empDto,@PathVariable int empNo) {
		empDao.empInfoUpdate(empNo,empDto);
	}
	
}
