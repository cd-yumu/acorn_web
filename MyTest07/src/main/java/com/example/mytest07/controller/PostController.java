package com.example.mytest07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mytest07.dto.PostDto;
import com.example.mytest07.service.PostService;

@Controller
public class PostController {
	
	@Autowired PostService postService;

	@GetMapping("/post/new")
	public String postNew() {
		return "post/new";
	}
	
	@GetMapping("/post/list")
	public String postList(Model model) {
		List<PostDto> list =  postService.getList();
		model.addAttribute("postList", list);
		return "post/list";
	}
	
	@GetMapping("/post/view")
	public String postView() {
		return "post/view";
	}
	
}
