package com.example.mytest03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FortuneController {
	
	@GetMapping("/fortune")
	public String fortune() {
		
		return "WEB-INF/views/fortune.jsp";
	}
}
