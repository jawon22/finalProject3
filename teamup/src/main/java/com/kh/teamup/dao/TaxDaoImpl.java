package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.TaxDto;
import com.kh.teamup.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TaxDaoImpl implements TaxDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
		public void insert(TaxDto taxDto) {
			sqlSession.insert("tax.add",taxDto);
		}
	
	@Override
	public List<TaxDto> selectList() {
		return sqlSession.selectList("tax.list");
	}
	
	@Override
	public TaxDto selectOne(int taxNo) {
		return sqlSession.selectOne("tax.find", taxNo);
	}

	@Override//세금명 검색
	public List<TaxDto> selectList(String taxName) {
		return sqlSession.selectList("tax.searchByTaxName", taxName);
	}
	
	@Override
	public void edit(TaxDto taxDto, int taxNo) {
		Map<String, Object> param = Map.of("taxDto", taxDto, "taxNo", taxNo);
		int result = sqlSession.update("tax.change", param);
		if(result == 0) throw new NoTargetException();
	}

}









