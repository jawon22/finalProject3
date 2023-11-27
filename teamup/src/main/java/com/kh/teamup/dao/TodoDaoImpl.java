package com.kh.teamup.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.TodoDto;
import com.kh.teamup.error.NoTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TodoDaoImpl implements TodoDao{

	@Autowired private SqlSession sqlSession;

	@Override
	public void insert(TodoDto todoDto) {
		sqlSession.insert("todo.save", todoDto);
	}
	
	@Override
	public List<TodoDto> empTodoList(int empNo) {
		return sqlSession.selectList("todo.list", empNo);
	}
	
	@Override
	public TodoDto selectOne(int todoNo) {
		return sqlSession.selectOne("todo.find", todoNo);
	}
	
	@Override
	public void deleteTodo(int todoNo) {
		sqlSession.delete("todo.remove", todoNo);
	}
	
	@Override
	public void change(TodoDto todoDto, int todoNo) {
		Map<String, Object> param = Map.of("todoDto", todoDto, "todoNo", todoNo);
		int result = sqlSession.update("todo.change", param);
		if(result == 0) throw new NoTargetException();
	}
}















