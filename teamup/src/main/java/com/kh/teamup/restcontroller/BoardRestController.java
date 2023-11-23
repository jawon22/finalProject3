package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.BoardDao;
import com.kh.teamup.dto.BoardDto;
import com.kh.teamup.vo.BoardVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "공지사항 관리" ,description = "공지사항 CRUD") 
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/board")
public class BoardRestController {
	
	@Autowired private BoardDao boardDao;

	@Operation(description = "공지사항 등록")
	@PostMapping("/add")
	public void insert(@RequestBody BoardDto boardDto) {
		log.debug("boardDto = {}", boardDto);
		boardDao.insert(boardDto);
	}
	
	@Operation(description = "공지사항 회사별 목록")
	@GetMapping("/list/{comId}")
	public List<BoardDto>list(@PathVariable String comId){
		return boardDao.comBoardList(comId);
	}
	
	@Operation(description = "공지사항 상세+수정용")
	@GetMapping("/find/{boardNo}")
		public BoardDto find(@PathVariable int boardNo){
		return boardDao.selectOne(boardNo);
	}
	
	@Operation(description = "공지사항 상세 읽기전용+조회수증가")
	@GetMapping("/read/{boardNo}")
	public BoardDto read(@PathVariable int boardNo, @RequestParam String empNo) {
	    BoardDto board = boardDao.selectOne(boardNo);

	    // empNo를 int로 변환하여 비교
	    int empNoInt = Integer.parseInt(empNo);
	    if (board.getEmpNo() != empNoInt) {
	        boardDao.updateRcount(boardNo); // 조회수 증가
	    }

	    return boardDao.selectOne(boardNo);
	}
	
	@GetMapping("/listPaged/{comId}")
	public List<BoardVO> listPaged(@PathVariable String comId,
	                               @RequestParam("page") int page,
	                               @RequestParam("size") int size) {
	    BoardVO boardVO = BoardVO.builder()
	                            .comId(comId)
	                            .page(page)
	                            .size(size)
	                            .build();

	    int totalCount = boardDao.getTotalCount(boardVO);
	    boardVO.setTotalCount(totalCount);

	    List<BoardVO> pagedList = boardDao.listPaged(boardVO);
	    return pagedList;
	}


	
	@Operation(description = "공지사항 삭제")
	@DeleteMapping("/{boardNo}")
	public void delete(@PathVariable int boardNo) {
		boardDao.deleteBoard(boardNo);
	}
	
	@Operation(description = "공지사항 수정")	
	@PutMapping("/{boardNo}")
	public void update(@RequestBody BoardDto boardDto, @PathVariable long boardNo) {
		boardDao.change(boardDto, boardNo);
	}
	
	
	

}





