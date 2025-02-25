package com.example.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired CommentService service;
	
	@PostMapping("/post/save-comment")
	public String saveComment(CommentDto dto) {
		
		service.addComment(dto);
		
		return "post/view";
	}
}
