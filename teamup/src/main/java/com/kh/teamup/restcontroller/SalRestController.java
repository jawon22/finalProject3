package com.kh.teamup.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.SalDao;
import com.kh.teamup.dto.SalDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="연봉 관리", description = "연봉 CRU")
@CrossOrigin
@RestController
@RequestMapping("/sal")
public class SalRestController {
	
	@Autowired
	private SalDao salDao;
	
	//등록
	@Operation(description = "연봉 등록")
	@PostMapping("/")
	public void insert(@RequestBody SalDto salDto) {
		salDao.insert(salDto);
	}
	
	//조회
	@Operation(description = "전사원 연봉 목록")
	@GetMapping("/")
	public List<SalDto> list(){
		return salDao.selectList();
	}
	
	//상세조회
	@Operation(description = "사원의 연봉")
	@GetMapping("/{empNo}")
	public SalDto find(@PathVariable int empNo) {
		return salDao.selectOne(empNo);
	}
	
	//수정
	@Operation(description = "사원의 연봉 수정")
	@PutMapping("/{empNo}")
	public void update(@RequestBody SalDto salDto, @PathVariable int empNo) {
		salDao.edit(empNo, salDto);
	}
	
	//사원의 최신 연봉 조회
	@Operation(description = "사원의 최신 연봉 조회")
	@GetMapping("/maxSalNo/{empNo}")
	public SalDto selectLatesSalNo(@PathVariable int empNo) {
		return salDao.selectOne(empNo);
	}

}












