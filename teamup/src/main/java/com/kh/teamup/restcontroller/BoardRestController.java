package com.kh.teamup.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.kh.teamup.vo.BoardNameVO;
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
		boardDao.insert(boardDto);
	}
	
	@Operation(description = "공지사항 회사별 목록")
	@GetMapping("/list/{comId}")
	public List<BoardNameVO> list(@PathVariable String comId){
		return boardDao.comBoardList(comId);
	}
	
	@Operation(description = "공지사항 상세+수정용")
	@GetMapping("/find/{boardNo}")
		public BoardNameVO find(@PathVariable int boardNo){
		return boardDao.selectOne(boardNo);
	}
	
//	@Operation(description = "공지사항 상세 읽기전용+조회수증가")
//	@GetMapping("/read/{boardNo}")
//	public BoardNameVO read(@PathVariable int boardNo, @RequestParam String empNo) {
//	    BoardNameVO board = boardDao.selectOne(boardNo);
//
//	    // empNo를 int로 변환하여 비교
//	    int empNoInt = Integer.parseInt(empNo);
//	    if (board.getEmpNo() != empNoInt) {
//	        boardDao.updateRcount(boardNo); // 조회수 증가
//	    }
//
//	    return boardDao.selectOne(boardNo);
//	}
	@Operation(description = "공지사항 상세 읽기전용+조회수증가")
	@PostMapping("/read/{boardNo}")
	public BoardNameVO read(
	    @PathVariable int boardNo,
	    @RequestParam("empNo") String empNo,
	    @RequestBody List<Integer> userReadHistory
	) {
	    BoardNameVO board = boardDao.selectOne(boardNo);

	    int empNoInt = Integer.parseInt(empNo);

	    if (board.getEmpNo() != empNoInt && !userReadHistory.contains(boardNo)) {
	        boardDao.updateRcount(boardNo);
	        userReadHistory.add(boardNo); // 읽은 게시글 기록에 추가
	    }

	    return boardDao.selectOne(boardNo);
	}
	
	@Operation(description = "공지사항 목록+페이지네이션(회사기준)")
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
	
//	@Operation(description = "공지사항 목록+페이지네이션+복합검색(회사기준)")
//	@PostMapping("/listPaged/")
//	public List<BoardVO> listPaged(@RequestBody BoardVO boardVO) {
//	    int totalCount;
//
//	    // 만약 검색어가 입력되었다면 검색 결과의 총 갯수 조회
//	    if (boardVO.getKeyword() != null && boardVO.getSelect() != null) {
//	        totalCount = boardDao.getSearchCount(boardVO);
//	    } else {
//	        // 검색어가 없으면 전체 공지사항의 총 갯수 조회
//	        totalCount = boardDao.getTotalCount(boardVO);
//	    }
//
//	    boardVO.setTotalCount(totalCount);
//
//	    // 나머지 페이징 및 목록 조회 로직은 여기에 추가
//
//	    return boardDao.listPaged(boardVO);
//	}

	@Operation(description = "공지사항 총 갯수")
	@GetMapping("/totalCount/{comId}")
	public ResponseEntity<Long> getTotalCount(@PathVariable String comId) {
	    try {
	        BoardVO boardVO = BoardVO.builder().comId(comId).build();
	        long totalCount = boardDao.getTotalCount(boardVO);
	        return new ResponseEntity<>(totalCount, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
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
	
	@Operation(description = "공지사항 댓글 갯수 업데이트")
	@PutMapping("/updateReplyCount/{boardNo}")
	public void updateReplyCount(@PathVariable long boardNo) {
		boardDao.updateBoardReplycount(boardNo);
	}

	
	

}





