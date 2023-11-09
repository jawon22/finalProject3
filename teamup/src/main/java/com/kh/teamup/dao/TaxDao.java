package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.TaxDto;

public interface TaxDao {

	void insert(TaxDto taxDto);
	List<TaxDto> selectList();
	TaxDto selectOne(int taxNo);
	List<TaxDto> selectList(String taxName);
	void edit(TaxDto taxDto, int taxNo);

}
