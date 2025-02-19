package com.example.mytest05.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mytest05.dto.MemberDto;


@Controller
public class TestController {
	
	@GetMapping("/buy")
	public String buy(String id, String name, Model model) {
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		return "test/buy";
	}
	
	@GetMapping("/if")
	public String ifTest(Model model) {
		
		// 로그인 여부 확인
		model.addAttribute("isLogin", true);
		// 나이
		model.addAttribute("age", 15);
		// role
		model.addAttribute("role", "admin");
		
		return "test/if";
	}
	
	@GetMapping("/javascript")
	public String javascript(Model model) {
		
		model.addAttribute("hello", "world");
		
		MemberDto dto = MemberDto.builder().num(1).name("맹구").addr("강남").build();
		model.addAttribute("dto", dto);
		
		MemberDto dto1 = MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		MemberDto dto2 = MemberDto.builder().num(2).name("해골").addr("행신동").build();
		MemberDto dto3 = MemberDto.builder().num(3).name("원숭이").addr("상도동").build();
		
		List<MemberDto> list = List.of(dto1, dto2, dto3);
		model.addAttribute("list", list);
		
		return "test/javascript";
	}
	
}
