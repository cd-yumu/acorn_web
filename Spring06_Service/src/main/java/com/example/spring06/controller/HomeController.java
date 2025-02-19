package com.example.spring06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring06.repository.MemberDao;

@Controller
public class HomeController {

	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
}
