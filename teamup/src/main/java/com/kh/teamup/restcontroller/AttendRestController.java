package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kh.teamup.dao.AttendDao;
import com.kh.teamup.dto.AttendDto;
import com.kh.teamup.vo.AttendWorkingSearchVO;
import com.kh.teamup.vo.AttendWorkingSysdateVO;
import com.kh.teamup.vo.AttendWorkingTimesVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="근태 관리", description = "근태 관리를 위한 컨트롤러")
@CrossOrigin
@RestController
@RequestMapping("/attend")
public class AttendRestController {
	
	@Autowired
	private AttendDao attendDao;
	
	//출근시간 찍히기
	@Operation(description = "출근 버튼")
	@PostMapping("/{empNo}") //등록
	public AttendDto insert(@PathVariable int empNo) {
		//시퀀스를 만들고, 등록을 한 다음 등록이 되면서 시간이 들어감
		//내가 모르지만 db가 안다. 하지만 나는 번호를 알고 있다. 그래서 번호를 조회해서 번호를 반환
		//db 갈 때 - 시퀀스 만들 때, 등록할 때, 조회할 때
		
		//근태 번호 조회
		int attendNo = attendDao.sequence(); 
		AttendDto attendDto = AttendDto.builder().attendNo(attendNo).empNo(empNo).build();
		//출근 기록 등록 
		attendDao.insert(attendDto);
		//출퇴근 기록 정보 반환
		return attendDao.findAttendNo(attendNo);
	}
	
	//퇴근시간 찍히기
	// @PutMapping - 전체 수정
	@Operation(description = "퇴근 버튼")
	@PatchMapping("/{empNo}") // 일부 수정
	public AttendDto update(@PathVariable int empNo) {
		//empNo를 이용하여 오늘자 출근내역을 불러오는 명령
		AttendDto attendDto = attendDao.findTodayAttendByEmpNo(empNo);
		//퇴근 기록 수정
		attendDao.update(attendDto);
		//출퇴근 기록 정보 반환
		return attendDao.findAttendNo(attendDto.getAttendNo());
	}
	
	@Operation(description = "현재 달의 1일부터 현재 달의 오늘일까지")
	@PostMapping("/findSysdate/{empNo}")
	public List<AttendWorkingTimesVO> findSysdate(@PathVariable int empNo) throws JsonProcessingException {
	    AttendWorkingSysdateVO VO = new AttendWorkingSysdateVO(empNo);
	    List<AttendWorkingTimesVO> list = attendDao.findSysdate(VO);
	    return list;
	}
	
	
	@Operation(description = "사용자가 월을 직접 입력하여 근태내역 보여주기")
	@PostMapping("/findSearch/{empNo}")
	public List<AttendWorkingTimesVO> findSearch(@PathVariable int empNo, @RequestBody AttendWorkingSearchVO VO){ //@RequestBody AttendWorkingSearchVO VO
		List<AttendWorkingTimesVO> list = attendDao.findSearch(VO);
		return list;
//		AttendWorkingSearchVO VO = new AttendWorkingSearchVO(empNo, yearMonth);
//	    List<AttendWorkingTimesVO> list = attendDao.findSearch(VO);
//	    return list;
	}
	
}
