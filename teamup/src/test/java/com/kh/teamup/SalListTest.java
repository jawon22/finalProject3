package com.kh.teamup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.teamup.dao.SalDao;
import com.kh.teamup.dao.TaxDao;
import com.kh.teamup.dto.SalDto;
import com.kh.teamup.dto.TaxDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SalListTest {

	@Autowired
	private SalDao salDao;
	
	@Autowired
	private TaxDao taxDao;
	
	
	@Test
	void test() {
		int empNo = 11;
		
		SalDto salDto = salDao.selectOne(empNo);	
	    log.debug("salDto = {}", salDto);
	    
	   
	    log.debug( "salTime ={}",salDto.getSalTime());
	    int annual = (int) salDto.getSalAnnual();
	    log.debug("연봉 = {}", annual);
	    
	    int timePay =(int) salDto.getSalTime();
	    
	    int salMonth = timePay * 160;//통상시급 * 한달근무시간 
	    
//	    log.debug("salMonth = {}", salMonth);
	       
	    List<TaxDto> list = taxDao.selectList();    
	    Map<String, Float> map = new HashMap<>();
	    for(TaxDto dto : list) {
	    	map.put(dto.getTaxName(), dto.getTaxRate());
	    }
	    log.debug("map = {}", map);
	    
	    //세금 종류별 금액
	    //[1] 건보료 = 급여 * 건보료율
	    int t1 = (int)(salMonth * map.get("건강보험") / 100);
	    System.out.println("건보료 = " + t1+"원");
		
	    //[2] 고보료 = 급여 * 고보료율
	    int t2 = (int)(salMonth * map.get("고용보험료") / 100);
	    System.out.println("고보료 = " + t2+"원");
	    
	    //[3] 국민연금액 = 급여 * 국민연금세율
	    int t3 = (int)(salMonth * map.get("국민연금") / 100);
	    System.out.println("국민연금 = " + t3 + "원");
	    
	    //[4] 장기요양보험료 = 건보료 * 장기요양보험료율
	    int t4 = (int)(t1 * map.get("장기요양보험료") / 100);
	    System.out.println("장기요양보험료 = " + t4+"원");
	}
}
