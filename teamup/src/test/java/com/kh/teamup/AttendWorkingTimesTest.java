package com.kh.teamup;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.teamup.vo.AttendWorkingSearchVO;
import com.kh.teamup.vo.AttendWorkingTimesVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AttendWorkingTimesTest {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() throws JsonProcessingException {
//		Map<String, Object> params = new HashMap<>();
//		params.put("empNo", 11);
//		params.put("attendBeginDate", "2023-10-01");
//		params.put("attendEndDate", "2023-10-31");
//		List<AttendWorkingTimesVO> list = sqlSession.selectList("attend.selectListByEmpNo", params);
		
		AttendWorkingSearchVO awtvo = AttendWorkingSearchVO.builder()
					.empNo(11)
				.build();
		List<AttendWorkingTimesVO> list = sqlSession.selectList("attend.findSysdate", awtvo);
		log.debug("list size = {}", list.size());
		ObjectMapper mapper = new ObjectMapper();
		for(AttendWorkingTimesVO vo : list) {
//			log.debug("vo = {}", vo);
			log.debug("json = {}", mapper.writeValueAsString(vo));
		}
	}
	
}
