package com.kh.teamup.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = {"com.kh.teamup.restcontroller"})
public class ExceptionControllerAdvice {
	
	//[1] No TargetException이 발생하면 사용자에게 404반환
	@ExceptionHandler(NoTargetException.class)
	public ResponseEntity<?> error404(Exception e){
//		log.warn("404 발생", e);
		return ResponseEntity.notFound().build();
	}
	

//	[2] 그 외 예외가 발생하면 사용자에게 500을 반환
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> error500(Exception e){//오류를 기록
		log.error("오류 발생", e);
		return ResponseEntity.internalServerError().body("server error");
	}

}















