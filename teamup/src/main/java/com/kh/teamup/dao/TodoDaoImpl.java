package com.kh.teamup.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.teamup.dto.TodoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TodoDaoImpl implements TodoDao{

	@Autowired private SqlSession sqlSession;

	@Override
	public void insert(TodoDto todoDto) {
//		log.debug("todoDto = {}", todoDto);
		sqlSession.insert("todo.save", todoDto);
	}
	
	@Override
	public List<TodoDto> selectList() {
		return sqlSession.selectList("todo.list");
	}
	
	@Override
	public TodoDto selectOne(int empNo) {
		return sqlSession.selectOne("todo.find", empNo);
	}
	
	@Override
	public void deleteTodo(int empNo) {
		sqlSession.delete("todo.remove", empNo);
	}
}















