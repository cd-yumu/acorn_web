package com.example.mytest03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		
		
		
		return "WEB-INF/views/home.jsp";
	}
}
