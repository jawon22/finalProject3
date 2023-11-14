package com.kh.teamup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.teamup.dao.AttendDao;
import com.kh.teamup.dao.EmpDao;
import com.kh.teamup.dao.SalDao;
import com.kh.teamup.dao.SalListDao;
import com.kh.teamup.dao.TaxDao;
import com.kh.teamup.dto.EmpDto;
import com.kh.teamup.dto.SalDto;
import com.kh.teamup.dto.SalListDto;
import com.kh.teamup.dto.TaxDto;
import com.kh.teamup.vo.TotalWorkingTimeByMonthVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SalListTest {

	@Autowired
	private EmpDao empDao;
	
	@Autowired
	private SalDao salDao;
	
	@Autowired
	private SalListDao salListDao;
	
	@Autowired
	private TaxDao taxDao;
	
	@Autowired
	private AttendDao attendDao;
	
	
	@Test
	void test() {
		List<EmpDto> empList = empDao.empList();
		for(EmpDto empDto : empList) {
			TotalWorkingTimeByMonthVO vo = new TotalWorkingTimeByMonthVO();
	        vo.setEmpNo(empDto.getEmpNo());

	        SalDto salDto = salDao.selectOne(vo.getEmpNo());

	        int annualPay = (int) salDto.getSalAnnual();
	        int timePay = (int) salDto.getSalTime();

	        int totalWorkingHours = attendDao.totalWorkingTimeByMonth(vo);

	        int salMonth = timePay * totalWorkingHours;

	        List<TaxDto> list = taxDao.selectList();
	        Map<String, Float> map = new HashMap<>();
	        for (TaxDto dto : list) {
	            map.put(dto.getTaxName(), dto.getTaxRate());
	        }

	        int health = (int) (salMonth * map.get("건강보험") / 100);
	        int emp = (int) (salMonth * map.get("고용보험") / 100);
	        int national = (int) (salMonth * map.get("국민연금") / 100);
	        int ltcare = (int) (health * map.get("장기요양보험") / 100);

	        int work;
	        if (annualPay < 4000000) {
	            work = (int) (salMonth * map.get("소득세1") / 100);
	        } else if (annualPay < 6000000) {
	            work = (int) (salMonth * map.get("소득세2") / 100);
	        } else {
	            work = (int) (salMonth * map.get("소득세3") / 100);
	        }

	        int local = (int) (work * map.get("지방소득세") / 100);

	        SalListDto salListDto = new SalListDto();

	        salListDto.setEmpNo(vo.getEmpNo());
	        salListDto.setSalListTotal(salMonth);
	        salListDto.setSalListHealth(health);
	        salListDto.setSalListEmp(emp);
	        salListDto.setSalListNational(national);
	        salListDto.setSalListLtcare(ltcare);
	        salListDto.setSalListLocal(local);
	        salListDto.setSalListWork(work);

	        salListDao.insert(salListDto);
		}
	}
}
