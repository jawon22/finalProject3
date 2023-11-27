package com.kh.teamup.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.ApproveDao;
import com.kh.teamup.dao.ApprovePathDao;
import com.kh.teamup.dao.ReceiversDao;
import com.kh.teamup.dao.ReferrersDao;
import com.kh.teamup.dto.ApproveDto;
import com.kh.teamup.dto.ApprovePathDto;
import com.kh.teamup.dto.ReceiversDto;
import com.kh.teamup.dto.ReferrersDto;
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
	private ApproveDao approveDao; //결재 Dao
	
	@Autowired
	private ApprovePathDao approvePathDao; //결재선 Dao
	
	@Autowired
	private ReceiversDao receiversDao; //승인자 Dao
	
	@Autowired
	private ReferrersDao referrersDao; //참조자 Dao
	
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
		
		approveInputVO.getApproveDto().setApprNo(apprNo);//결재에 시퀀스 설정
		ApproveDto approveDto = approveInputVO.getApproveDto();
				
		approveDao.insert(approveDto);  // 인서트
		
//		// 결재선에 등록자 설정, 결재번호 설정 후 approvePathDto를 꺼낸 뒤 등록
		ApprovePathDto approvePathDto = approveInputVO.getApprovePathDto();
		if(approvePathDto == null) {
			approvePathDto = new ApprovePathDto();
			approveInputVO.setApprovePathDto(approvePathDto);
		}
		
		approvePathDto.setApprPathNo(apprPathNo);
		approvePathDto.setApprNo(approveDto.getApprNo());
		approvePathDto.setApprSender(approveDto.getApprSender());
		approvePathDao.insert(approvePathDto);
		
		// 승인자가 몇명인지 파악한후 for문으로 insert
		List<ReceiversDto> receiversDto = approveInputVO.getReceiversDtoList();
		List<Integer> receivers = new ArrayList<>();
		for(int i=0; i<receiversDto.size();i++) {
			receivers.add(i, receiversDto.get(i).getReceiversReceiver());
		}
		
		for(int i=0; i<receiversDto.size(); i++) {
			int receiverNo = receiversDao.sequence(); //시퀀스를 여기서 뽑아야함 (승인자가 많으면 바꿔야하므로)
			
			receiversDto.get(i).setReceiversNo(receiverNo); //시퀀스 설정
			receiversDto.get(i).setPathNo(approvePathDto.getApprPathNo()); //승인자테이블에 결재선 번호를 설정
			receiversDto.get(i).setReceiversReceiver(receivers.get(i));
			receiversDao.insert(receiversDto.get(i));
		}
		
		//만약 참조자가 있다면 몇 명인지 파악한후 for문으로 insert
		List<ReferrersDto> referrersDto = approveInputVO.getReferrersDtoList();	
		List<Integer> referers = new ArrayList<>();
		
		for(int i =0; i<referrersDto.size();i++) {
			referers.add(i, referrersDto.get(i).getReferrersReferrer());
		}
		
		if(referrersDto.size() !=0) { //사이즈가 0이 아니라면 추가
			for(int i=0; i<referrersDto.size(); i++) {
				int referrerNo = referrersDao.sequence(); // 여기도 마찬가지
				
				referrersDto.get(i).setReferrersNo(referrerNo); //시퀀스 설정
				referrersDto.get(i).setPathNo(approvePathDto.getApprPathNo()); //참조자테이블에 결재선 번호를 설정
				referrersDto.get(i).setReferrersReferrer(referers.get(i));
				referrersDao.insert(referrersDto.get(i));
			}
		}
	}
	
	
	//삭제(결재)
	@DeleteMapping("/{apprNo}") //결재 번호만 받아서 삭제하면 됨
	public ResponseEntity<String> delete(@PathVariable int apprNo){ 
		
		boolean result = approveDao.delete(apprNo);
		if(result) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//승인(결재)
	@PutMapping("/{pathNo}/{receiver}")
	public ResponseEntity<Void> confirm(@PathVariable int pathNo, @PathVariable int receiver,
			@RequestBody ReceiversDto receiversDto){
		if(receiversDto.getReceiversReturnRs() ==null) {
			receiversDto.setReceiversReturnRs("승인");
		}
		
		boolean result = receiversDao.apprConfirm(pathNo, receiver, receiversDto);
		return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	
	//반려(결재)
	@PutMapping("/pathNo/{pathNo}/receiver/{receiver}")
	public ResponseEntity<Void> cancel(@PathVariable int pathNo, @PathVariable int receiver,
			@RequestBody ReceiversDto receiversDto){
		if(receiversDto.getReceiversReturnRs() == null) {
			receiversDto.setReceiversReturnRs("반려");
		}
		log.debug("dto = {}",receiversDto);
		
		boolean result = receiversDao.apprCancel(pathNo, receiver, receiversDto);
		return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	
	
	
	
}












