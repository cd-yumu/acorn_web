package com.example.mytest07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mytest07.dto.UserDto;
import com.example.mytest07.service.UserService;

@Controller
public class UserController {
	
	@Autowired UserService service;
	
	@GetMapping("/user/login-form")
	public String loginForm() {
		return "user/login-form";
	}
	
	@GetMapping("/user/signup-form")		
	public String signupForm() {
		return "user/signup-form";
	}
	
	@PostMapping("/user/signup-proc")
	public String signupProc(UserDto dto) {
		//service.newUser(dto);
		return "user/signup-proc";
	}
}
