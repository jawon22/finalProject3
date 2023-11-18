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

import com.kh.teamup.dao.TodoDao;
import com.kh.teamup.dto.TodoDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="TodoList 관리", description = "TodoList CRUD")
@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoRestController {
	
	@Autowired private TodoDao todoDao;
	
	//등록
	@Operation(description = "todo등록")
	@PostMapping("/save/")
	public void insert(@RequestBody TodoDto todoDto) {
		log.debug("todoDto={}",todoDto);
		todoDao.insert(todoDto);
	}
	
	//목록
	@Operation(description = "todo 사원별 목록")
	@GetMapping("/list/{empNo}")
	public List<TodoDto>list(@PathVariable int empNo){
		return todoDao.empTodoList(empNo);
	}
	
	@Operation(description = "todo 사원별 상세 조회")
	@GetMapping("/{todoNo}")
	public TodoDto find (@PathVariable int todoNo) {
		return todoDao.selectOne(todoNo);
	}

	@Operation(description = "todo 삭제")
	@DeleteMapping("/{empNo}")
	public void delete(@PathVariable int empNo) {
		todoDao.deleteTodo(empNo);
	}
	
	@Operation(description = "todo 수정")
	@PutMapping("/{todoNo}")
	public void update(@RequestBody TodoDto todoDto, @PathVariable int todoNo) {
		todoDao.change(todoDto, todoNo);
	}

}










