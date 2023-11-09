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
import com.kh.teamup.dao.ApprovePathDao;
import com.kh.teamup.dao.ReceiversDao;
import com.kh.teamup.dto.ApproveDto;
import com.kh.teamup.dto.ApprovePathDto;
import com.kh.teamup.dto.ReceiversDto;
import com.kh.teamup.vo.ApproveInputVO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

//문서용 annotation
@Tag(name = "결재 리액트용 백엔드개발", description = "개발중임")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/approve")
public class ApproveRestController { //결재 테이블

	@Autowired
	private ApproveDao approveDao; //결재Dao
	
	@Autowired
	private ApprovePathDao approvePathDao; //결재선Dao
	
	@Autowired
	private ReceiversDao receiversDao; //승인자Dao
	
	//조회
	@GetMapping("/")
	public List<ApproveDto> list(){
		return approveDao.selectList();
	}
	
	//등록
	// 결재선과 같이 등록해야함
	@PostMapping("/")
	public void insert(@RequestBody ApproveInputVO approveInputVO) {
		
		
		int apprNo = approveDao.sequence(); //apprNo를 가져옴
		int apprPathNo = approvePathDao.sequence();
		
		
		approveInputVO.getApproveDto().setApprNo(apprNo); //결재에 시퀀스 설정
		ApproveDto approveDto = approveInputVO.getApproveDto(); //approveDto를 꺼낸후
		approveDao.insert(approveDto);  // 인서트
		
//		// 결재선에 등록자 설정, 결재번호 설정 후 approvePathDto를 꺼낸 뒤 등록
		approveInputVO.getApprovePathDto().setApprPathNo(apprPathNo);
		approveInputVO.getApprovePathDto().setApprNo(approveDto.getApprNo());
		approveInputVO.getApprovePathDto().setApprSender(approveDto.getApprSender());
		ApprovePathDto approvePathDto = approveInputVO.getApprovePathDto();
		approvePathDao.insert(approvePathDto);
		
		// 승인자에 결재선 번호 설정 후 ReceiversDto 꺼낸 뒤 등록
		// 결재자가 추가될때마다 (결재자 수 + 참조자 수)만큼 인서트 반복 -> 결재자는 프론트에서 직원아이디 값을 받아와 수를 세야할듯?
		approveInputVO.getReceiversDto().setPathNo(approvePathDto.getApprPathNo()); //승인자테이블에 결재선번호를 설정
		
//		List<Integer> receiver = 
		ReceiversDto receiversDto = approveInputVO.getReceiversDto();
		receiversDao.insert(receiversDto);
		
		log.debug("VO={}",approveInputVO);
	}
	
	
	//삭제
	// 로그인했을때의 사원 아이디를 리코일 저장소를 통해 비교 + 진행상태가 R일 경우만
	@DeleteMapping("/empNo/{empNo}") //아이디를 받아서 비교해야함 -> react에서 리코일로
	public ResponseEntity<String> delete(@PathVariable int empNo){ //여기도 아직 고민
		boolean result = approveDao.delete(empNo);
		if(result) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
}
