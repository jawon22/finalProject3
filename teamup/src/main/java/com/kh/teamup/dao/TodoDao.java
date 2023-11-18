package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.TodoDto;

public interface TodoDao {

	void insert(TodoDto todoDto);
	TodoDto selectOne(int todoNo);
	void deleteTodo(int empNo);
	void change(TodoDto todoDto, int todoNo);
	List<TodoDto> empTodoList(int empNo);
	
}
