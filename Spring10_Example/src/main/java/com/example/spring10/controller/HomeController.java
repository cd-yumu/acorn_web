package com.example.spring10.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		// 공지사항
		List<String> noticeLsit = List.of("Spring Boot Project Start",
				"열심히 해보아요", "어쩌구리", "저쩌구리");
		// 응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("noticeLsit", noticeLsit);
		// view page 에서 응답하기
		return "home";
	}
	
	
}
