package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.EmpPositionDao;
import com.kh.teamup.dto.EmpPositionDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "직급" ,description = "직급 CRUD")
@CrossOrigin
@RequestMapping("/empPosition")
@RestController
public class EmpPositionRestController {
	@Autowired
	private EmpPositionDao empPositionDao;
	@GetMapping("/position/")
	public List<EmpPositionDto> list(){
		
		return empPositionDao.selectList();
	}
	
	
	
	@PostMapping()
	public void addPosition(@RequestBody EmpPositionDto empPositionDto) {
		
		empPositionDao.addPosition(empPositionDto);
		
		
	}

}
