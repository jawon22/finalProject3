package com.kh.teamup.dao;

import java.util.List;

import com.kh.teamup.dto.TodoDto;

public interface TodoDao {

	void insert(TodoDto todoDto);
	List<TodoDto> selectList();
	TodoDto selectOne(int empNo);
	void deleteTodo(int empNo);
	
}
