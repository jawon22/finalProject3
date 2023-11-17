package com.kh.teamup.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.teamup.dao.TodoDao;
import com.kh.teamup.dto.TodoDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="TodoList 관리", description = "TodoList CRUD")
@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoRestController {
	
	@Autowired private TodoDao todoDao;
	
	//등록
	@PostMapping("/")
	public void insert(@RequestBody TodoDto todoDto) {
		todoDao.insert(todoDto);
	}
	
	//목록
	@GetMapping("/")
	public List<TodoDto>list(){
		return todoDao.selectList();
	}
	
	@GetMapping("/{empNo}")
	public TodoDto find (@PathVariable int empNo) {
		return todoDao.selectOne(empNo);
	}
	
	@DeleteMapping("/{empNo}")
	public void delete(@PathVariable int empNo) {
		todoDao.deleteTodo(empNo);
	}
	
//	@PutMapping("/{empNo}/{todoNo}")
//	public void update(@RequestBody TodoDto todoDto, @PathVariable int empNo,
//									@PathVariable int todoNo) {
//		todoDao.change(empNo, todoDto, todoNo);
//	}

}










