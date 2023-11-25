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
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.ReplyDao;
import com.kh.teamup.dto.ReplyDto;
import com.kh.teamup.vo.ReplyByBoardVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "공지사항 댓글 관리" ,description = "공지사항 댓글 CRUD") 
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyRestController {
	
	
	@Autowired private ReplyDao replyDao;

	@Operation(description = "공지사항 댓글 등록")
	@PostMapping("/")
	public void insert(@RequestBody ReplyDto replyDto) {
		replyDao.insert(replyDto);
	}
	
	@Operation(description = "공지사항 댓글 목록")
	@GetMapping("/list/{replyOrigin}")
	public List<ReplyByBoardVO> list(@PathVariable long replyOrigin){
		return replyDao.replyListByBoard(replyOrigin);
	}
	
	@Operation(description = "공지사항 댓글 삭제")
	@DeleteMapping("/{replyNo}")
	public void delete(@PathVariable long replyNo) {
		replyDao.deleteReply(replyNo);
	}
	
	@Operation(description = "공지사항 댓글 수정")
	@PutMapping("/{replyNo}")
	public void update(@RequestBody ReplyDto replyDto, @PathVariable long replyNo) {
		replyDao.editReply(replyDto, replyNo);
	}
	
	
	
}












