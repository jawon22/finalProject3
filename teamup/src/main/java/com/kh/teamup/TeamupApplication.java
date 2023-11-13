package com.kh.teamup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling//급여내역 자동 insert를 위한 스케줄러 사용을 위한 설정
@SpringBootApplication
public class TeamupApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamupApplication.class, args);
	}

}
