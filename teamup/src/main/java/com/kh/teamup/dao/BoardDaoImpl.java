package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.BoardDto;
import com.kh.teamup.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDaoImpl implements BoardDao{
	
	
	@Autowired private SqlSession sqlSession;

	@Override
	public void insert(BoardDto boardDto) {
		sqlSession.insert("board.add", boardDto);
	}

	@Override
	public List<BoardDto> comBoardList(String comId) {
		return sqlSession.selectList("board.list", comId);
	}
	
	@Override
	public BoardDto selectOne(int boardNo) {
		return sqlSession.selectOne("board.find", boardNo);
	}
	
	@Override
	public void deleteBoard(int boardNo) {
		sqlSession.delete("board.remove",boardNo);
	}
	
	@Override
	public void change(BoardDto boardDto, long boardNo) {
		Map<String, Object> param = Map.of("boardDto", boardDto, "boardNo", boardNo);
		int result = sqlSession.update("board.edit", param);
		if(result == 0) throw new NoTargetException();
	}

}
