package com.example.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.service.PostService;


@Controller
public class PostController {
	
	@Autowired private PostService service;
	
	/*
	 * pageNum 이 파라미터로 넘어오지 않으면 pageNum 의 default 값은 1로 설정
	 * 검색 키워드도 파라미터로 넘어오면 PostDto 에는 condition 과 keyword 가 null 이 아니다.
	 * 검색 키워드가 넘어오지 않으면 PostDto 의 condition 과 keyword 는 null 이다.
	 */

	// @RequestParam 은 파라미터를 직접 추출할 때 사용되는 어노테이션으로 평소 생략 가능하다.
	// defaultValue 는 문자열 타입이지만 자동 int 형으로 형 변환이 이루어진다.
	@GetMapping("/post/list")
	public String list(@RequestParam(defaultValue = "1") int pageNum, PostDto search, Model model) {
		PostListDto dto = service.getPosts(pageNum, search);
		model.addAttribute("dto", dto);
		return "post/list";
	}
}
