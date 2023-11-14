package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.teamup.dao.AttendDao;
import com.kh.teamup.dto.AttendDto;
import com.kh.teamup.vo.AttendWorkingSearchVO;
import com.kh.teamup.vo.AttendWorkingSysdateVO;
import com.kh.teamup.vo.AttendWorkingTimesVO;

import io.swagger.v3.oas.annotations.Operation;
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
	@Operation(description = "출근 버튼")
	@PostMapping("/") //등록
	public void insert(@RequestBody AttendDto attendDto) {
		attendDao.insert(attendDto);
	}
	
	//퇴근시간 찍히기
	// @PutMapping - 전체 수정
	@Operation(description = "퇴근 버튼")
	@PatchMapping("/{empNo}") // 일부 수정
	public void update(@RequestBody AttendDto attendDto, @PathVariable int empNo) {
		attendDao.update(empNo, attendDto);
	}
	
	@Operation(description = "현재 달의 1일부터 현재 달의 오늘일까지")
	@PostMapping("/findSysdate")
	public List<AttendWorkingTimesVO> findSysdate(@RequestBody AttendWorkingSysdateVO VO) throws JsonProcessingException{
		List<AttendWorkingTimesVO> list = attendDao.findSysdate(VO);
		return list;
	}
	
	
	@Operation(description = "사용자가 월을 직접 입력하여 근태내역 보여주기")
	@PostMapping("/findSearch")
	public List<AttendWorkingTimesVO> findMonth(@RequestBody AttendWorkingSearchVO VO){
		List<AttendWorkingTimesVO> list = attendDao.findSearch(VO);
		return list;
	}
	

}
