package com.kh.teamup.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ReceiversDto;
import com.kh.teamup.error.NoTargetException;

@Repository
public class ReceiversDaoImpl implements ReceiversDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("receivers.sequence");
	}
	
	@Override
	public void insert(ReceiversDto receiversDto) {
		sqlSession.insert("receivers.receiversSave",receiversDto);
	}
	
	@Override
	public List<ReceiversDto> selectByPathNo(int pathNo) {
		return sqlSession.selectList("receivers.findByPathNo", pathNo);
	}
	
	@Override
	public ReceiversDto selectByPathNoAndReceiver(int pathNo, int receiver) {
		Map<String, Object> params = new HashMap<>();
		params.put("pathNo", pathNo);
		params.put("receiversReceiver", receiver);
		
		ReceiversDto receiversDto = sqlSession.selectOne("receivers.findByPathNoAndReceiver", params);
		if(receiversDto ==null )throw new NoTargetException();
		return receiversDto;
	}
	
	@Override
	public boolean apprConfirm(int pathNo, int receiver, ReceiversDto receiversDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("pathNo", pathNo);
		params.put("receiversReceiver", receiver);
		params.put("dto", receiversDto);
		
		return sqlSession.update("receivers.approveConfirm",params)>0;
	}
	
	@Override
	public boolean apprCancel(int pathNo, int receiver, ReceiversDto receiversDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("pathNo", pathNo);
		params.put("receiversReceiver", receiver);
		params.put("dto", receiversDto);
		
		return sqlSession.update("receivers.approveCancel",params)>0;
	}
}
