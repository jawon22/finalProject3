package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.EmpDao;
import com.kh.teamup.dto.EmpDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "사원 관리" ,description = "사원 CRUD") 

@RestController
@CrossOrigin
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	private EmpDao empDao;
	
	@Operation(description = "사원 추가")
	@PostMapping("/")
	public void addEmp(@RequestBody EmpDto empDto) {
		empDao.addEmp(empDto);
		
	}
	
	@Operation(description = "사번 생성")
	@PatchMapping("/{empNo}")
	public void updateEmpId(@RequestBody EmpDto empDto,@PathVariable int empNo) {
		
		empDao.updateEmpId(empNo, empDto);
		
	}
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
//	@Operation(description = "복합 검색")
//	@GetMapping("/comflexSearch/{}/{}/{}")
//	public List<empDto>

	
}
