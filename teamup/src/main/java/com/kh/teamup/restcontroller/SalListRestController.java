package com.kh.teamup.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.SalDao;
import com.kh.teamup.dao.SalListDao;
import com.kh.teamup.dao.TaxDao;
import com.kh.teamup.dto.SalDto;
import com.kh.teamup.dto.SalListDto;
import com.kh.teamup.dto.TaxDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name="급여내역", description = "급여내역 CRUD")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/salList")
public class SalListRestController {
	
	@Autowired
	private SalListDao salListDao;

	@Autowired
	private SalDao salDao;
	
	@Autowired//추가 추가
	private TaxDao taxDao;
	
	@Operation(description = "급여내역저장")
	@PostMapping("/")
	public void calculateSalList(@RequestBody SalListDto salListDto, int empNo) {
		
		SalDto salDto = salDao.selectOne(empNo);
		
		int annualPay = (int) salDto.getSalAnnual();
		int timePay = (int)salDto.getSalTime();//해당 사원의 통상시급 
		int salMonth = timePay * 160;//통상 시급 * 한달근무시간 (근무시간은 갖고와야함)
		
		List<TaxDto> list = taxDao.selectList();
		Map<String, Float> map = new HashMap<>();
		for(TaxDto dto : list) {//taxDto에서 세금명과 세율만 갖고옴
			map.put(dto.getTaxName(), dto.getTaxRate());
		}
		
		//세금 종류별 금액 계산
		//[1]건강보험	
		int health = (int)(salMonth * map.get("건강보험")/100);
		//[2]고용보험
		int emp = (int)(salMonth * map.get("고용보험")/100);
		//[3]국민연금
		int national = (int)(salMonth * map.get("국민연금")/100);
		//[4]장기요양보험료 = 건보료 * 장기요양보험료율
		int ltcare = (int)(salMonth * map.get("장기요양보험")/100);
		//[5]지방소득세 = 근로소득세 * 지방소득세율
		int local = (int)(salMonth * map.get("지방소득세")/100);
		//[6]근로소득세 = 연봉에 따라 적용이 다름
		int work;
	    if (annualPay < 4000000) {
	        work = (int) (salMonth * map.get("소득세1") / 100);
	    }
	    else if (annualPay < 6000000) {
	        work = (int) (salMonth * map.get("소득세2") / 100);
	    }
	    else {
	        work = (int) (salMonth * map.get("소득세3") / 100);
	    }
	    log.debug("소득세={}",work);
		
		salListDto.setEmpNo(empNo);
		salListDto.setSalListTotal(salMonth);
		salListDto.setSalListHealth(health);
		salListDto.setSalListEmp(emp);
		salListDto.setSalListNational(national);
		salListDto.setSalListLtcare(ltcare);
		salListDto.setSalListLocal(local);
		salListDto.setSalListWork(work);
		
		salListDao.insert( salListDto);
	}
	
	
	
	
}
















