package com.example.spring06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring06.dto.TodoDto;
import com.example.spring06.repository.TodoDao;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	private TodoDao dao;

	@Override
	public List<TodoDto> selectAll() {
		List<TodoDto> list = dao.selectAll();
		return list;
	}

	@Override
	public TodoDto select(int id) {
		TodoDto dto = dao.select(id);
		return dto;
	}

	@Override
	public void insert(String content) {
		dao.insert(content);
	}

	@Override
	public void update(TodoDto dto) {
		dao.update(dto);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}



}
