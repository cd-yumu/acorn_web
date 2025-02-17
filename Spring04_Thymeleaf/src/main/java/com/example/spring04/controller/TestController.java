package com.example.spring04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring04.dto.MemberDto;


@Controller
public class TestController {
	@GetMapping("/notice")
	public String notice(Model model) {
		List<String> noticeList = List.of("Thymeleaf view engine 을 배워보아요!",
				"하나", "두울", "어쩌구...", "저쩌구...");
		model.addAttribute("noticeList",noticeList);
		return "post/notice";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		// lombok 의 기능을 이용해서 MemberDto 객체를 얻어내고
		MemberDto dto = MemberDto.builder()
				.num(1)
				.name("김구라")
				.addr("노량진")
				.build();
		
		// 해당 데이터를 Model 객체에 담고
		model.addAttribute("dto",dto);	// model 에 담은 것은 자동으로 HttpRequest 에 담긴다.
		// view page 로 이동해서 응답하기
		return "member/info";
	}
	
	@GetMapping("/member/list")
	public String memberList(Model model) {
		// DB 에서 select 한 결과라고 가정하자 
		MemberDto dto1 = MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		MemberDto dto2 = MemberDto.builder().num(2).name("해골").addr("행신동").build();
		MemberDto dto3 = MemberDto.builder().num(3).name("원숭이").addr("상도동").build();
		
		// 이전에는 new ArrayList 해서 .add 로 만들던 것을 List.of 로 간단히 생성
		// 단, read only 이다.
		List<MemberDto> list = List.of(dto1, dto2, dto3);
		
		// Model 객체에 "list" 라는 키값으로 담기
		model.addAttribute("list", list);
		
		return "member/list";
	}
	
	@GetMapping("/sequence")
	public String sequence() {
		return "test/sequence";
	}
	
	
}
