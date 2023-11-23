package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.BoardDto;
import com.kh.teamup.vo.BoardVO;

public interface BoardDao {

	void insert(BoardDto boardDto);
	List<BoardDto> comBoardList(String comId);
	BoardDto selectOne(int boardNo);
	void deleteBoard(int boardNo);
	void change(BoardDto boardDto, long boardNo);
	boolean updateRcount(long boardNo);
	List<BoardVO> listPaged(BoardVO boardVO);
	int getTotalCount(BoardVO boardVO);
}
