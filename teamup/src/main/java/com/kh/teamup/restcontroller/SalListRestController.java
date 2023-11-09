package com.kh.teamup.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.SalDao;
import com.kh.teamup.dao.SalListDao;
import com.kh.teamup.dao.TaxDao;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="급여내역", description = "급여내역 CRUD")
@CrossOrigin
@RestController
@RequestMapping("/salList")
public class SalListRestController {
	
	@Autowired
	private SalListDao salListDao;

	@Autowired
	private SalDao salDao;
	
	@Autowired
	private TaxDao taxDao;
	
	
	
	
}
















