package com.example.mytest07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	
	
	// 게시판의 리스트 페이지로 이동
	@GetMapping("/post/list")
	public String list() {
		return "post/lisg";
	}
}
