package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.AddrDto;
import com.kh.teamup.vo.SearchVO;

@Repository
public class AddrDaoImpl implements AddrDao{
	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override
	public void addAddr(AddrDto addrDto) {
		sqlSession.insert("addr.addAddr",addrDto);
	}
	
	@Override
	public void deletAddr(int addEmpNo) {
		sqlSession.delete("addr.deleteAddr",addEmpNo);
		
		
	}
	@Override
	public List<SearchVO> myAddrList(int myEmpNo) {
		return sqlSession.selectList("addr.myAddrList",myEmpNo);
	}
}
