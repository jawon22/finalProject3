package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.ApproveDao;
import com.kh.teamup.dto.ApproveDto;

import io.swagger.v3.oas.annotations.tags.Tag;

//문서용 annotation
@Tag(name = "결재 리액트용 백엔드개발", description = "개발중임")
@CrossOrigin
@RestController
@RequestMapping("/approve")
public class ApproveRestController { //결재 테이블

	@Autowired
	private ApproveDao approveDao;
	
	
	//조회
	@GetMapping("/")
	public List<ApproveDto> list(){
		return approveDao.selectList();
	}
	
	//등록
	// 결재선과 같이 등록해야함
	@PostMapping("/")
	public void insert(@RequestBody ApproveDto approveDto) {
		approveDao.insert(approveDto);
	}
	
	
	//삭제
	// 로그인했을때의 사원 아이디를 리코일 저장소를 통해 비교 + 진행상태가 R일 경우만
	@DeleteMapping("/{empNo}") //아이디를 받아서 비교해야함 -> react에서 리코일로
	public ResponseEntity<String> delete(@PathVariable int empNo){ //여기도 아직 고민
		boolean result = approveDao.delete(empNo);
		if(result) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
}
