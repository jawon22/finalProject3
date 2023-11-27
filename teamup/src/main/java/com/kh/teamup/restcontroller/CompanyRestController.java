package com.kh.teamup.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.teamup.dao.AttachDao;
import com.kh.teamup.dao.CompanyDao;
import com.kh.teamup.dto.AttachDto;
import com.kh.teamup.dto.CompanyDto;
import com.kh.teamup.service.EmpService;
import com.kh.teamup.vo.CompanyImageVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "회사", description = "회사CRUD")

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/com")
public class CompanyRestController {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private AttachDao attachDao;
	@Autowired
	private BCryptPasswordEncoder encoder;


	// 이미지 등록하면서 회사 정보 등록 같이 하니까 일단 주석 처리 했습니당~
	@PostMapping("/")
	public void addCom(@RequestBody CompanyDto companyDto) {
	

	String convertPw = encoder.encode(companyDto.getComPw());

	companyDto.setComPw(convertPw);
		
	companyDao.addCom(companyDto);
		
	}

//	// 회사 등록(+파일 업로드)
//	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public void addCom(@ModelAttribute CompanyImageVO vo) throws IllegalStateException, IOException {
//		log.debug("dto={}", vo);
//
//		CompanyDto companyDto = vo.getCompanyDto();
//
//		String convertPw = encoder.encode(companyDto.getComPw());
//
//		companyDto.setComPw(convertPw);
//
//		log.debug("convert={}", convertPw);
//
//		companyDao.addCom(companyDto);
//
//		MultipartFile attach = vo.getAttach();
//
//		int attachNo = attachDao.sequence();
//
//		String home = System.getProperty("user.home");
//		File dir = new File(home, "upload");
//		dir.mkdirs();
//		File target = new File(dir, String.valueOf(attachNo));
//		attach.transferTo(target);
//
//		AttachDto attachDto = new AttachDto();
//		attachDto.setAttachNo(attachNo);
//		attachDto.setAttachName(attach.getOriginalFilename());
//		attachDto.setAttachSize(attach.getSize());
//		attachDto.setAttachType(attach.getContentType());
//		attachDao.insert(attachDto);
//
//		companyDao.connectCom(companyDto.getComId(), attachNo);
//		log.debug("attach={}", attach);
//
//	}

//	//파일 다운로드
//	@GetMapping("/image/{comId}")
//	public ResponseEntity<ByteArrayResource> download(@PathVariable String comId) throws IOException{
//		
//		System.out.println("comId = " + comId);
//		AttachDto attachDto = companyDao.findImage(comId);
//		System.out.println("attachDto = " + attachDto);
//		
//		String home = System.getProperty("user.home");
//		File dir = new File(home, "upload");
//		File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
//		
////		System.out.println("파일크기 = " + target.length());
//		
//		byte[] data = FileUtils.readFileToByteArray(target);//실제 파일 정보 불러오기
////		System.out.println("파일크기2 = " + data.length);
////		System.out.println("파일크기3 = " + attachDto.getAttachSize());
//		ByteArrayResource resource = new ByteArrayResource(data);
//		
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
//				.contentLength(attachDto.getAttachSize())
//				.header(HttpHeaders.CONTENT_DISPOSITION, 
//						ContentDisposition.attachment()
//							.filename(attachDto.getAttachName(), StandardCharsets.UTF_8)
//							.build().toString()
//						)
//				.contentType(MediaType.APPLICATION_OCTET_STREAM)
//				.body(resource);
//		
//	}

	// 회사 이미지 수정
	@PutMapping(value = "/{comId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> editCom(@ModelAttribute CompanyImageVO vo) throws IllegalStateException, IOException {

		MultipartFile attach = vo.getAttach();

		CompanyDto companyDto = vo.getCompanyDto();
		companyDao.update(vo);

		if (!attach.isEmpty()) {// 파일이 있으면
			// 파일 삭제 - 기존 파일이 있을 경우에만 처리
			AttachDto attachDto = companyDao.findImage(companyDto.getComId());
			String home = System.getProperty("user.home");
			File dir = new File(home, "upload");

			if (attachDto != null) {
				attachDao.delete(attachDto.getAttachNo());

				File target = new File(dir, String.valueOf(attachDto.getAttachNo()));
				target.delete();
			}

			// 파일 추가 및 연결
			// 파일번호 생성
			int attachNo = attachDao.sequence();

			// 신규파일 저장
			File insertTarget = new File(dir, String.valueOf(attachNo));
			attach.transferTo(insertTarget);

			// 신규파일정보 저장
			AttachDto insertDto = new AttachDto();
			insertDto.setAttachNo(attachNo);
			insertDto.setAttachName(attach.getOriginalFilename());
			insertDto.setAttachSize(attach.getSize());
			insertDto.setAttachType(attach.getContentType());
			attachDao.insert(insertDto);

			// 회사+파일 연결
			companyDao.connectCom(companyDto.getComId(), attachNo);
		}

		return ResponseEntity.ok().body("회사 및 이미지 수정 성공");

	}

	@Operation(description = "로그인")
	@PostMapping("/login")
	public boolean login(@RequestBody CompanyDto inputDto) {

		// 해독
		CompanyDto findDto = companyDao.selectOne(inputDto.getComId());
		
		

//		String originPw = findDto.getComPw();
//		String inputPw = inputDto.getComPw();
//		log.debug(inputPw);
//		log.debug(originPw);
//		
		//input이 앞에 있어야 함
		boolean isMatch = encoder.matches( inputDto.getComPw(),findDto.getComPw());


		return isMatch;


	}
	@Operation(description = "로그인용 전체 리스트")
	@GetMapping("/list")
	public List<CompanyDto> list(){
		return companyDao.selectList();
	}

}
