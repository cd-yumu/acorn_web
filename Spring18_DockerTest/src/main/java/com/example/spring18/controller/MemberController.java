package com.example.spring18.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring18.dto.MemberDto;
import com.example.spring18.repository.MemberDao;

import jakarta.websocket.Session;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MemberController {
	
	@Autowired
	private MemberDao dao; // spring bean container 로부터 injection(주입)
	
	@PostMapping("/member/insert")	
	public String insert(MemberDto dto) {
		dao.insert(dto);	//매개 변수에 MemberDto type 을 선언하면 form 전송 되는 파라미터가 자동 추출되어서 MemberDto 객체에 담긴채로 참조값이 전달된다.
							// 단, name 속성 값이 필드명이 같아야 한다. (AI는 아니니깐!)
		return "member/insert";
	}
	
	@GetMapping("/member/edit")
	public String updateForm(int num, Model model) {
		// Get 방식 파라미터로 전달되는 회원의 번호를 이용해서 회원 정보를 얻어온다.
		MemberDto dto = dao.getData(num);
		// 응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("dto", dto);
		return "member/edit";
	}
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/new")
	public String newForm() {
		
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		// 주입 받은 MemberDao 객체를 이용해서 회원 목록을 얻어온다.
		List<MemberDto> list = dao.getList();
		// Model 객체에 담는다.
		model.addAttribute("list", list);
		// Thymeleaf view 페이지에서 회원 목록을 응답한다.
		return "member/list";
		
		// /templates/
	}
	

	@GetMapping("/member/delete")
	public String delete(int num) {
		dao.delete(num);
		return "redirect:/member/list";
		// String cPath="request.getContextPath();
		// response.sendRedirect(cPath + ".member/list");
		// redirect: 로 시작하게 되면 templates 폴더의 위치가 아니게 된다.
		// 그리고 그 뒤에 경로 요청을 다시 하게 된다.
	}
	

	
	

	
}
