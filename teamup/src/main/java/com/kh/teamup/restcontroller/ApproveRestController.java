package com.kh.teamup.restcontroller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
		int receiverNo = receiversDao.sequence();
		int referrerNo = referrersDao.sequence();
		
		
		approveInputVO.getApproveDto().setApprNo(apprNo); //결재에 시퀀스 설정
		// fe에서 작성자의 번호가 넘어오면 번호로 작성자의 정보 조회후 인서트
		ApproveDto approveDto = approveInputVO.getApproveDto(); //approveDto를 꺼낸후
		approveDao.insert(approveDto);  // 인서트
		
//		// 결재선에 등록자 설정, 결재번호 설정 후 approvePathDto를 꺼낸 뒤 등록
		approveInputVO.getApprovePathDto().setApprPathNo(apprPathNo);
		approveInputVO.getApprovePathDto().setApprNo(approveDto.getApprNo());
		approveInputVO.getApprovePathDto().setApprSender(approveDto.getApprSender());
		ApprovePathDto approvePathDto = approveInputVO.getApprovePathDto();
		approvePathDao.insert(approvePathDto);
		
		
		// 승인자가 몇명인지 파악한후 for문으로 insert
		ReceiversDto receiversDto = approveInputVO.getReceiversDto();
		// fe에서 승인자를 받아야함
		//승인자 여러명 list에 저장 , 승인자의 직급을 알아야 하는데.. Map으로 저장?
		List<Integer> receiverList = new ArrayList<>();
		receiverList.add(16);
		receiverList.add(14);
		receiverList.add(11);
		
		Set<Integer> receiverSet = new TreeSet<>(receiverList); //승인자는 정렬이 필요하므로 TreeSet
		for(int i=0; i<receiverSet.size(); i++) {
			approveInputVO.getReceiversDto().setReceiversNo(receiverNo); //시퀀스 설정
			approveInputVO.getReceiversDto().setPathNo(approvePathDto.getApprPathNo()); //승인자테이블에 결재선 번호를 설정
			receiversDto.setReceiversReceiver(i);
			receiversDao.insert(receiversDto);
		}
		
		//만약 참조자가 있다면 몇 명인지 파악한후 for문으로 insert
		ReferrersDto referrersDto = approveInputVO.getReferrersDto();	
		// fe에서 참조자를 받아야함
		List<Integer> referrerList = new ArrayList<>();
		referrerList.add(27);
		referrerList.add(28);
		
		Set<Integer> referrerSet = new HashSet<>(referrerList); //참조자는 정렬이 필요없으므로 HashSet
		if(referrerSet.size() !=0) { //사이즈가 0이 아니라면 추가
			for(int i=0; i<referrerSet.size(); i++) {
				approveInputVO.getReferrersDto().setReferrersNo(referrerNo); //시퀀스 설정
				approveInputVO.getReferrersDto().setPathNo(approvePathDto.getApprPathNo()); //참조자테이블에 결재선 번호를 설정
				referrersDto.setReferrersReferrer(i);
				referrersDao.insert(referrersDto);
			}
		}
		
		log.debug("VO={}",approveInputVO);
	}
	
	
	//삭제(결재)
	// 로그인했을때의 사원 아이디를 리코일 저장소를 통해 비교 + 진행상태가 R일 경우만
	@DeleteMapping("/apprNo/{apprNo}") //아이디를 받아서 비교해야함 -> react에서 리코일로
	public ResponseEntity<String> delete(@PathVariable int apprNo){ //여기도 아직 고민
		ApproveDto targetDto = approveDao.selectOne(apprNo);
		
		//클릭한 결재의 해당하는 결재자와 리액트에서 로그인했을때의 저장된 사용자가 같은지 비교
		
		// 결재의 진행상태가 "R"인지 확인해야함
		List<ApprovePathDto> targetDto2 = approvePathDao.selectByApprNo(targetDto.getApprNo());
		List<ReceiversDto> targetDto3 = receiversDao.selectByPathNo(targetDto2.get(0).getApprPathNo());

		boolean isIng= false;
		for(int i=0; i<targetDto3.size(); i++) {
			isIng = targetDto3.get(i).getReceiversStatus().equals("R");
		}
		
		boolean result = approveDao.delete(apprNo);
		if(result &&isIng) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
}
