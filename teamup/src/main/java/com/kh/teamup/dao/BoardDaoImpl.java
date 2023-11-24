package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.BoardDto;
import com.kh.teamup.error.NoTargetException;
import com.kh.teamup.vo.BoardNameVO;
import com.kh.teamup.vo.BoardVO;

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
	public List<BoardNameVO> comBoardList(String comId) {
		return sqlSession.selectList("board.list", comId);
	}
	
	@Override
	public BoardNameVO selectOne(int boardNo) {
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
	
	@Override
	public boolean updateRcount(long boardNo) {
		return sqlSession.update("board.updateRcount", boardNo)>0;
	}
	
	@Override
	public int getTotalCount(BoardVO boardVO) {
	    return sqlSession.selectOne("board.getTotalCount", boardVO);
	}

	@Override
	public List<BoardVO> listPaged(BoardVO boardVO) {
	    return sqlSession.selectList("board.listPaged", boardVO);
	}


}
