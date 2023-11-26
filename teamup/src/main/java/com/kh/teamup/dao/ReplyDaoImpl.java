package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.ReplyDto;
import com.kh.teamup.error.NoTargetException;
import com.kh.teamup.vo.ReplyByBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ReplyDaoImpl implements ReplyDao{
	
	@Autowired private SqlSession sqlSession;
	
	@Override
	public void insert(ReplyDto replyDto) {
		sqlSession.insert("reply.save", replyDto);
	}
	
	@Override
	public List<ReplyByBoardVO> replyListByBoard(long replyOrigin) {
		return sqlSession.selectList("reply.list", replyOrigin);
	}
	
	 @Override
	public void deleteReply(long replyNo) {
		 sqlSession.delete("reply.remove", replyNo);
	}
	 
	 @Override
	public void editReply(ReplyDto replyDto, long replyNo) {
		Map<String, Object>param = Map.of("replyDto", replyDto, "replyNo", replyNo);
		int result = sqlSession.update("reply.edit", param);
		if(result == 0) throw new NoTargetException();
	}

	 @Override
	 public ReplyDto selectReply(long replyNo) {
	     return sqlSession.selectOne("reply.selectReply", replyNo);
	 }

	 
}













