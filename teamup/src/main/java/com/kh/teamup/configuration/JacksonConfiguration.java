package com.kh.teamup.configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

//Spring Boot에서 사용하는 기본 Jackson 변환도구를 변경하는 설정
@Configuration
public class JacksonConfiguration {

	//신규 Bean을 중요(Primary)하게 등록하여 기존 코드를 대체
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setTimeZone(TimeZone.getDefault());//시간 지역을 시스템 기본값으로 설정
		//mapper.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));//시간 지역을 인위적으로 설정
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//날짜 변환 기본 포맷을 설정
		return mapper;
	}
	
}
