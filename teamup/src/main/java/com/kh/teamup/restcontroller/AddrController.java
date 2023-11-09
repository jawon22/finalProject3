package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.AddrDao;
import com.kh.teamup.dto.AddrDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "주소록", description = "주소록 CRUD")
@RestController
@RequestMapping("/addr")
@CrossOrigin
@Slf4j
public class AddrController {
	@Autowired
	private AddrDao addrDao;
	
	@PostMapping("/")
	public void addrAddr(@RequestBody AddrDto addrDto) {
		
		addrDao.addAddr(addrDto);
	}
	
	@DeleteMapping("/addr/{addEmpNo}")
	public void deleteAddr(@PathVariable int addEmpNo) {
		addrDao.deletAddr(addEmpNo);
	}
	
	@GetMapping("/myAddrList/{myEmpNo}")
	public List<AddrDto> myAddrList(@PathVariable int myEmpNo){
		
		return addrDao.myAddrList(myEmpNo);
		
		
		
	}

}
