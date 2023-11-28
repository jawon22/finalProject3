package com.kh.teamup.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
@Service
public class RegularSalInsertServiceImpl implements RegularSalInsertService{

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
	
	//스케줄러
//	@Scheduled(cron = "0 0 10 * * ?")//매달 10일날 실행
//	@Scheduled(fixedRate = 500000)//5초(1000ms/1초)에 한번씩 실행	
	@Override
		public void insertSalForAllEmp() {
		
			List<EmpDto> empList = empDao.empList();
			for(EmpDto empDto : empList) {
				// 현재 연월 계산
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
				String currentYearMonth = currentDate.format(dateFormatter);

				// 이전 달 계산
				LocalDate previousMonth = currentDate.minusMonths(1);
				String previousYearMonth = previousMonth.format(dateFormatter);
				
				
				TotalWorkingTimeByMonthVO vo = new TotalWorkingTimeByMonthVO();
		        vo.setEmpNo(empDto.getEmpNo());
		        vo.setYearMonth(previousYearMonth);
		        
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
				salListDto.setSalListDate(vo.getYearMonth());

		        salListDao.insert(salListDto);
			}
		}

}
