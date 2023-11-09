package com.kh.teamup.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.AttendDao;
import com.kh.teamup.dto.AttendDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="근태 관리", description = "근태 관리를 위한 컨트롤러")
@CrossOrigin
@RestController
@RequestMapping("/attend")
public class AttendRestController {
	
	@Autowired
	private AttendDao attendDao;
	
	//출근시간 찍히기
	@PostMapping("/") //등록
	public void insert(@RequestBody AttendDto attendDto) {
		attendDao.insert(attendDto);
	}
	
	//퇴근시간 찍히기
	// @PutMapping - 전체 수정
	@PatchMapping("/{empNo}") // 일부 수정
	public void update(@RequestBody AttendDto attendDto, @PathVariable int empNo) {
		attendDao.update(empNo, attendDto);
	}
	
	
	// @GetMapping - 조회
	
}
