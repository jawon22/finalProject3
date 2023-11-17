package com.kh.teamup.restcontroller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.AttachDao;
import com.kh.teamup.dao.EmpDao;
import com.kh.teamup.dao.ProfileDao;
import com.kh.teamup.dao.SalDao;
import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.EmpDto;
import com.kh.teamup.dto.ProfileDto;
import com.kh.teamup.dto.RequestDto;
import com.kh.teamup.dto.SalDto;
import com.kh.teamup.service.EmpService;
import com.kh.teamup.vo.EmpComplexSearchVO;
import com.kh.teamup.vo.EmpSearchBydeptComVO;
import com.kh.teamup.vo.SearchVO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "사원 관리" ,description = "사원 CRUD") 
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/emp")
public class EmpRestController {
	@Autowired
	private EmpDao empDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private AttachDao attachDao;
	
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private EmpService empService;
	@Autowired
	private SalDao salDao;
	
	
	
//	@Transactional
//	@Operation(description = "사원 추가")
//	@PostMapping("/addEmp/")
//	public void addEmp(@RequestBody EmpDto empDto, @RequestBody SalDto salDto) throws MessagingException, IOException {
//	
//		int empNo = empDao.sequence();
//		empDto.setEmpNo(empNo);
//		
//		empDao.addEmp(empDto);
//		
//		salDto.setEmpNo(empNo);
//		salDao.insert(salDto);
//		
//		//사원 등록할 때 기본 프로필도 같이 등록
//		ProfileDto profileDto = new ProfileDto();
//		int profileNo = profileDao.sequence();
//		profileDto.setProfileNo(profileNo);
//		profileDto.setEmpNo(empNo);
//		profileDto.setProfileTitle("나를 소개하는 제목을 입력하세요.");
//		profileDto.setProfileContent("나를 소개하는 내용을 입력하세요."); 
//		
//		profileDao.addProfile(profileDto);
//		
//		
//		//사원 등록할 때 기본 이미지 같이 등록
//		int defaultImageNo = 1; // 기본 이미지의 번호를 설정하세요.
//	    int attachNo = attachDao.sequence();
//	    
//	    AttachDto attachDto = new AttachDto();
//	    attachDto.setAttachNo(attachNo);
//	    attachDto.setAttachName("profileImage.png"); // 이미지 파일명은 적절하게 설정하세요.
//	    attachDto.setAttachSize(0); // 이미지 크기는 0 또는 적절한 크기로 설정하세요.
//	    attachDto.setAttachType("image/jpeg"); // 이미지 타입은 적절한 타입으로 설정하세요.
//	    attachDao.insert(attachDto);
//	    
//	    // 프로필과 이미지 연결
//	    profileDao.connectProfile(profileNo, attachNo);
//	    
//	    EmpDto findDto = empDao.selectIdByNo(empNo);
//	    
//	    
//	    
//	    log.debug("findDto={}",findDto);
//	    
//	    empService.updateEmpId(empNo, findDto);
//		
//		
//	}
	@Transactional
	@Operation(description = "사원 추가")
	@PostMapping("/addEmp/")
	public void addEmp(@RequestBody RequestDto requestDto) throws MessagingException, IOException {
		
	    EmpDto empDto = requestDto.getEmpDto();
	    SalDto salDto = requestDto.getSalDto();
	    
		int empNo = empDao.sequence();
		empDto.setEmpNo(empNo);
		
		empDao.addEmp(empDto);
		
		salDto.setEmpNo(empNo);
		salDao.insert(salDto);
		
		//사원 등록할 때 기본 프로필도 같이 등록
		ProfileDto profileDto = new ProfileDto();
		int profileNo = profileDao.sequence();
		profileDto.setProfileNo(profileNo);
		profileDto.setEmpNo(empNo);
		profileDto.setProfileTitle("나를 소개하는 제목을 입력하세요.");
		profileDto.setProfileContent("나를 소개하는 내용을 입력하세요."); 
		
		profileDao.addProfile(profileDto);
		
		
		//사원 등록할 때 기본 이미지 같이 등록
		int defaultImageNo = 1; // 기본 이미지의 번호를 설정하세요.
	    int attachNo = attachDao.sequence();
	    
	    AttachDto attachDto = new AttachDto();
	    attachDto.setAttachNo(attachNo);
	    attachDto.setAttachName("profileImage.png"); // 이미지 파일명은 적절하게 설정하세요.
	    attachDto.setAttachSize(0); // 이미지 크기는 0 또는 적절한 크기로 설정하세요.
	    attachDto.setAttachType("image/jpeg"); // 이미지 타입은 적절한 타입으로 설정하세요.
	    attachDao.insert(attachDto);
	    
	    // 프로필과 이미지 연결
	    profileDao.connectProfile(profileNo, attachNo);
	    
	    EmpDto findDto = empDao.selectIdByNo(empNo);
	    
	    
	    
	    log.debug("findDto={}",findDto);
	    
	    empService.updateEmpId(empNo, findDto);
		
		
	}
	
//	@Operation(description = "사번 생성")
//	@PutMapping("/updateEmpId/{empNo}")
//	public void updateEmpId(@RequestBody EmpDto empDto,@PathVariable int empNo) throws MessagingException, IOException {
//		//사번 생성이루 select 로 사번을 찾고 있으면 메세지 전송한다.
//		//보내기 전에 pw를 랜덤으로 설정
//		//로그인시에 secure
//		empDao.updateEmpId(empNo, empDto);
//		
//		EmpDto findDto = empDao.selectIdByNo(empNo);
//		
//		//findDto의 비밀번호를 랜덤한 값으로 바꾸는 코드
//		
//		String tempPw=UUID.randomUUID().toString().replace("-", "");//-를 제거
//		tempPw = tempPw.substring(0,10);//tempPw를 앞에서부터 10자리 잘라줌
//		
//		log.debug("임시비번={}",tempPw);
//		
//		//여시서 임시비밀번호를 insert
//		
//		String convert =encoder.encode(tempPw);
//		log.debug("convert={}",convert);
//		empDto.setEmpPw(convert);//이거를 보내주고
//		empDao.empInfoUpdate(empNo, empDto);
//		
//		log.debug("dto={}",empDto);
//		
//		
//		log.debug("findDto={}", findDto);
//		
//		if(findDto.getEmpEmail() == null) return;
//		
//		MimeMessage messege = sender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(messege,false,"UTF-8");
//		
//		helper.setTo(findDto.getEmpEmail());
//		helper.setSubject("임시비밀번호 생성");
//		
//		log.debug("email={}",findDto.getEmpEmail());
//		
//		ClassPathResource resource = new ClassPathResource("templates/email2.html");
//		
//		File targetFile = resource.getFile();
//		
//		Scanner scanner = new Scanner(targetFile);
//		StringBuffer buffer = new StringBuffer();
//		
//		while(scanner.hasNextLine()) {
//			buffer.append(scanner.nextLine());
//		}
//		scanner.close();
//		
//		String text = buffer.toString();
//		log.debug(text);
//		Document doc = Jsoup.parse(text);
//		
//		Element who = doc.getElementById("who");
//		who.text(findDto.getEmpName());
//		
//		Element id = doc.getElementById("id");
//		id.text(findDto.getEmpName());
//		
//		Element pw = doc.getElementById("pw");
//		pw.text(tempPw);
//		
//		Element link = doc.getElementById("link");
//		link.attr("href", "#");//링크는 병경하면 된다
//		
//		
//		helper.setText(doc.toString(),true);
//		sender.send(messege);
//		
//		
//	}
	@Operation(description = "전체 리스트")
	@GetMapping("/")
	public List<EmpDto> empList(){
		return empDao.empList();
	}
	@Operation(description = "사원 제거")
	@DeleteMapping("/{empNo}")
	public void deleteEmp(@PathVariable int empNo) {
		empDao.deleteEmp(empNo);
	}
	@Operation(description = "개별 수정")
	@PatchMapping("/empInfoUpdate/{empNo}")
	public void empInfoUpdate(@RequestBody EmpDto empDto,@PathVariable int empNo) {
		empDao.empInfoUpdate(empNo,empDto);
	}
	
	//사번만 있을때 사번과 부서가 있을떄 사번과 부서가 있을때? 회사는 조인을 해야하나??
	//사원 no 만 알면 다 조회가 가능한데 부서도 no가 있고 개별 번호인데 회사의 조인이 필요한가
	//이름으로 검색하면 문제가 될 수 있음 ->동명이인 일경우 
	// 그럼 사원 검색에서는 회사까지 조인 그냥 리스트를뽑는 경우 인데 나중에 검색을 추가 한다고 하면 회사까지 해두는게 좋지않은가
	@Operation(description = "복합 검색")
	@PostMapping("/complexSearch/")
	public List<EmpComplexSearchVO> complexSearch(@RequestBody EmpComplexSearchVO VO){
		return empDao.complexSearch(VO);
		
	}
	
	////////////////////////////////////////////////////////////////////
	private final String secretKey = ""; // 서명 키. 반드시 안전한 방법으로 보호되어야 함
    private final long validityInMilliseconds = 3600000; // 토큰의 유효 기간 (1시간)
	//true면 session에 저장
	@Operation(description = "로그인")
	@PostMapping("login/")
	public String login(@RequestBody EmpDto inputDto) {
		//아이디로 조회 
		EmpDto findDto = empDao.selecOne(inputDto.getEmpId());
		
		
		boolean isMach =encoder.matches(inputDto.getEmpPw(),findDto.getEmpPw()) ;
		
		log.debug("??={}",isMach);
		
		if(isMach==true) {
			Date now = new Date();
	        Date validity = new Date(now.getTime() + validityInMilliseconds);

	        return Jwts.builder()
	                .setSubject(findDto.getEmpId())
	                .setIssuedAt(now)
	                .setExpiration(validity)
	                .compact();

		}
		else {
			return null;
		}

	}
	
	
	@PostMapping("/search/")
	public List<SearchVO> complexSearch(@RequestBody SearchVO searchVO){
		
		
		return empDao.search(searchVO);
	}
	@GetMapping("/mypage/{empNo}")
	public EmpDto myPage(@PathVariable int empNo) {
		return empDao.selectIdByNo(empNo);
		
	}
	
	@PostMapping("/empListByDeptCom")
	public List<EmpSearchBydeptComVO> listByDeptCom(@RequestBody EmpSearchBydeptComVO empSearchBydeptComVO) {
		return empDao.selectListByDeptCom(empSearchBydeptComVO);
	}
	
	
	
	@PutMapping("/updateDept/{empId}")
	public void updateDept(@PathVariable String empId,@RequestBody EmpDto empDto) {
		//부서 이동시키고
		empDao.updateDept(empId, empDto);
		log.debug("dto={}",empDto);
		log.debug("id={}",empId);

		//이동시킨 부서로 사원번호 이전
		
		empDao.changeEmpId(empId);
		
	}
	
	@PutMapping("/updateExit/{empId}")
	public void updateExit(@PathVariable String empId,@RequestBody EmpDto empDto) {
		
		empDao.updateExit(empId, empDto);
	}
	
}
