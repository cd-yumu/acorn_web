package com.example.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SendController {
	/*
	 * 컨트롤러 메소드 안에서 HttpServletRequest, HttpServletResponse, HttpSession 등의 객체가 필요하면
	 * 매개변수에 선언한다.
	 * 
	 * 선언만하면 spring 프레임워크가 알아서 해당 객체의 참조값을 매개변수에 전달해준다.
	 */
	@ResponseBody
	@PostMapping("/send")
	public String send(HttpServletRequest request) {
		// 요청 파라미터 추출!
		String msg = request.getParameter("msg");
		// 콘솔창에 출력하기
		System.out.println(msg);
		return "/send okay!";
	}
	
	// 전송되는 파라미터명과 동일하게 매개 변수를 선언하면 자동으로 추출되어서 매개 변수에된다.
	// <input type="text" name="msg"/>
	@ResponseBody
	@PostMapping("/send2")
	public String send2(String msg) {
		System.out.println(msg);
		return "/send2 okay!";
	}
	
	
	//@ResponseBody 이 어노테이션을 붙이면 바로 응답한다.
	// 없다면 view page 에 정보를 담아 응답한다.
}
