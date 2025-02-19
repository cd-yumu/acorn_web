package com.example.spring06.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring06.dto.TodoDto;

@Repository
public class TodoDaoImpl implements TodoDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<TodoDto> selectAll() {
		List<TodoDto> list = session.selectList("todo.selectAll");
		return list;
	}

	@Override
	public TodoDto select(int id) {
		TodoDto dto = session.selectOne("todo.select", id);
		return dto;
	}

	@Override
	public int insert(String content) {
		int resCount = session.insert("todo.insert", content);
		return resCount;
	}

	@Override
	public int update(TodoDto dto) {
		int resCount = session.update("todo.update", dto);
		return resCount;
	}

	@Override
	public int delete(int id) {
		int resCount = session.delete("todo.delete", id);
		return resCount;
	}
	
}
