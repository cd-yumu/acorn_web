package com.example.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring06.dto.TodoDto;
import com.example.spring06.service.TodoService;


@Controller
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping("/todo/list")
	public String goListPage() {
		List<TodoDto> list = service.selectAll();
		return "todo/list";
	}
	
	@GetMapping("/todo/insert-form")
	public String goInsertPage() {
		return "todo/insert-form";
	}
	
	@PostMapping("/todo/insert")
	public String insert(String content) {
		
		service.insert(content);
		return "redirect:/todo/list";
	}
	
	@GetMapping("/todo/edit-form")
	public String goEditPage(int id, Model model) {
		
		TodoDto dto = service.select(id);
		model.addAttribute("dto", dto);
		
		return "todo/edit-form";
	}
	
	@PostMapping("/todo/update")
	public String insert(TodoDto dto) {
		
		service.update(dto);
		
		return "redirect:/todo/list";
	}
	
	
	
	
}
