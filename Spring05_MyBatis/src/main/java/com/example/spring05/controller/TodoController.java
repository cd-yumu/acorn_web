package com.example.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring05.dto.TodoDto;
import com.example.spring05.repository.TodoDao;


@Controller
public class TodoController {
	
	@Autowired
	private TodoDao dao;
	
	// To Do 페이지로 이동
	@GetMapping("/todo/list")
	public String getList(Model model) {
		
		List<TodoDto> list = dao.getList();
		model.addAttribute("list", list);
		
		return "todo/list";
	}
	
	// Insert 페이지로 이동
	@GetMapping("/todo/insert-form")
	public String goInsertForm() {
		return "todo/insert-form";
	}
	
	// Insert 작업 실행
	@PostMapping("/todo/insert")
	public String insertContent(String content) {
		
		dao.insert(content);
		
		return "redirect:/todo/list";
	}
	
	// Edit 페이지로 이동
	@GetMapping("/todo/edit-form")
	public String goEditForm(int id, Model model) {
		
		TodoDto dto = dao.getData(id);
		model.addAttribute("dto", dto);
		
		return "todo/edit-form";
	}
	
	// Edit 작업 실행
	@PostMapping("/todo/edit")
	public String editContent(TodoDto dto) {
		dao.update(dto);
		
		return "redirect:/todo/list";
	}
	
	// Delete 작업 바로 실행
	@GetMapping("/todo/delete")
	public String goDeleteForm(int id) {
		
		dao.delete(id);
		
		return "redirect:/todo/list";
	}
	
	
}
