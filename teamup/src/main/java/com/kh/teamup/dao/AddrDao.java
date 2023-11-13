package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.AddrDto;
import com.kh.teamup.vo.SearchVO;

public interface AddrDao {

	void addAddr(AddrDto addrDto);

	void deletAddr(int addEmpNo);

	List<SearchVO> myAddrList(int myEmpNo);

}
