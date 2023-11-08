package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.AddrDto;

public interface AddrDao {

	void addAddr(AddrDto addrDto);

	void deletAddr(int addEmpNo);

	List<AddrDto> myAddrList(int myEmpNo);

}
