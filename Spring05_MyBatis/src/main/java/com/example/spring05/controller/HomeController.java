package com.example.spring05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring05.repository.MemberDao;

@Controller
public class HomeController {

	
	@GetMapping("/")
	public String home() {
		
		return "home";
		
		// Thymeleaf 사용할 때:  /templates/home.html
		// JSP 사용할 때: 		  /WEB-INF/views/home.jsp
	}
}
