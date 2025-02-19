package com.example.spring06.service;

import java.util.List;

import com.example.spring06.dto.TodoDto;

public interface TodoService {
	// 리스트 모두 가져오기
	public List<TodoDto> selectAll(); 
	// 하나의 정보 가져오기
	public TodoDto select(int id);
	// 추가하기
	public void insert(String content);
	// 수정하기
	public void update(TodoDto dto);
	// 삭제하기
	public void delete(int id);
}
