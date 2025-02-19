package com.example.spring06.controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring06.dto.MemberDto;
import com.example.spring06.repository.MemberDao;
import com.example.spring06.service.MemberServcie;

import jakarta.websocket.Session;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MemberController {
	
	// 서비스 객체는 Controller 에서 활용하는 유틸리티라고 생각하면 된다.
	@Autowired
	private MemberServcie service;
	
	@PostMapping("/member/insert")	
	public String insert(MemberDto dto) {
		// 서비스를 이용해서 저장하기
		service.save(dto);
		return "member/insert";
	}
	
	@GetMapping("/member/edit")
	public String updateForm(int num, Model model) {
		// Get 방식 파라미터로 전달되는 회원의 번호를 이용해서 회원 정보를 얻어온다.
		MemberDto dto = service.findById(num);
		// 응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("dto", dto);
		return "member/edit";
	}
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		service.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/new")
	public String newForm() {
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		// service 를 이용해서 회원 목록을 얻어온다.
		List<MemberDto> list = service.findAll();
		// Model 객체에 담는다.
		model.addAttribute("list", list);
		// Thymeleaf view 페이지에서 회원 목록을 응답한다.
		return "member/list";
	}
	

	@GetMapping("/member/delete")
	public String delete(int num) {
		// 서비스를 이용해서 회원 정보 삭제
		service.deleteById(num);
		return "redirect:/member/list";
	}
	

	
	

	
}
