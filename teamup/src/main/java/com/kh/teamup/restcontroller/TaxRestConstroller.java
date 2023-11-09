package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.TaxDao;
import com.kh.teamup.dto.TaxDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="세금 관리", description = "세금 CRU")
@CrossOrigin
@RestController
@RequestMapping("/tax")
public class TaxRestConstroller {

	@Autowired
	private TaxDao taxDao;
	
	//등록
	@PostMapping("/")
	public void insert(@RequestBody TaxDto taxDto) {
		taxDao.insert(taxDto);
	}
	
	//목록
	@GetMapping("/")
	public List<TaxDto>list(){
		return taxDao.selectList();
	}
	
	//상세
	@GetMapping("/{taxNo}")
	public TaxDto find(@PathVariable int taxNo){
		return taxDao.selectOne(taxNo);
	}
	
	//세금명 검색
	@GetMapping("/taxName/{taxName}")
	public List<TaxDto> searchByTaxName(@PathVariable String taxName){
		return taxDao.selectList(taxName);
	}
	
	@PutMapping("/{taxNo}")
	public void update(@RequestBody TaxDto taxDto, @PathVariable int taxNo) {
		taxDao.edit(taxDto, taxNo);
	}
	
	
}











