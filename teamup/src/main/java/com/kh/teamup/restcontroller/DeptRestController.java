package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.DeptDao;
import com.kh.teamup.dto.DeptDto;
import com.kh.teamup.vo.DeptVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "부서관리" , description = "부서CRUD")
@RestController
@CrossOrigin
@RequestMapping("dept")
public class DeptRestController {
	@Autowired
	private DeptDao deptDao;
	
	

	
	@PostMapping("/")
	public void addDept(@RequestBody DeptDto deptDtp) {
		deptDao.addDept(deptDtp);
	}
	@Operation(description = "회사별 부서 부서원의 수 ")
	@GetMapping("/listByCompany/{comId}")
	public List<DeptVo> listByCompany(@PathVariable String comId){
		return deptDao.deptList(comId);
		
	}
	
	@PutMapping("/update/{deptNo}")
	public void update( @PathVariable int deptNo ,@RequestBody DeptDto deptDto) {
		deptDao.deptUpdate(deptNo,deptDto);
		
	}
	
	


}
