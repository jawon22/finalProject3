package com.kh.teamup.service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.teamup.dao.EmpDao;
import com.kh.teamup.dto.EmpDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EmpService {
	@Autowired
	private EmpDao empDao;
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	

	public void updateEmpId(int empNo, EmpDto empDto) throws MessagingException, IOException {
		//사번 생성이루 select 로 사번을 찾고 있으면 메세지 전송한다.
		//보내기 전에 pw를 랜덤으로 설정
		//로그인시에 secure
		empDao.updateEmpId(empNo, empDto);
		
		EmpDto findDto = empDao.selectIdByNo(empNo);
		
		//findDto의 비밀번호를 랜덤한 값으로 바꾸는 코드
		
		String tempPw=UUID.randomUUID().toString().replace("-", "");//-를 제거
		tempPw = tempPw.substring(0,10);//tempPw를 앞에서부터 10자리 잘라줌
		
		//여시서 임시비밀번호를 insert
		
		String convert =encoder.encode(tempPw);
		empDto.setEmpPw(convert);//이거를 보내주고
		empDao.empInfoUpdate(empNo, empDto);
		
		if(findDto.getEmpEmail() == null) return;
		
		MimeMessage messege = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messege,false,"UTF-8");
		
		helper.setTo(findDto.getEmpEmail());
		helper.setSubject("임시비밀번호 생성");
		
		ClassPathResource resource = new ClassPathResource("templates/email2.html");
		
		File targetFile = resource.getFile();
		
		Scanner scanner = new Scanner(targetFile);
		StringBuffer buffer = new StringBuffer();
		
		while(scanner.hasNextLine()) {
			buffer.append(scanner.nextLine());
		}
		scanner.close();
		
		String text = buffer.toString();

		Document doc = Jsoup.parse(text);
		
		Element who = doc.getElementById("who");
		who.text(findDto.getEmpName());
		
		Element id = doc.getElementById("id");
		id.text(findDto.getEmpName());
		
		Element pw = doc.getElementById("pw");
		pw.text(tempPw);
		
		Element link = doc.getElementById("link");
		link.attr("href", "#");//링크는 병경하면 된다
		
		
		helper.setText(doc.toString(),true);
		sender.send(messege);
		
		
	}
	
}
