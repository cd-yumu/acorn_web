package com.example.spring10.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 예외 컨트롤러는 @ControllerAdvice 어노테이션을 붙여 bean 으로 만든다.
@ControllerAdvice
public class ExceptionController{
	
	// 거부된 요청일 때 실행되는 메소드
	@ExceptionHandler(DeniedException.class)
	public String denied(DeniedException de, Model model) {
		// 예외 객체를 template 페이지에 전달하기
		model.addAttribute("exception", de);
		
		return "error/denied";
	}
	
	/*
	 *  "/user/updated-password" 요청을 처리하는 중에 기본 비밀번호가 일치하지 않으면
	 *  userService 객체에서 PasswordException 이 발생하고 
	 *  해당 예외가 발생하면 이 메소드가 호출되고 
	 *  결과 적으로 비밀번호 수정 폼으로 다시 리다이렉트 된다.
	 */
	@ExceptionHandler(PasswordException.class)
	public String password(PasswordException pe, RedirectAttributes ra) {
		// model 이나 http request 영역에 담긴 메시지는 forward 만 가능하지 redirect 로 전달할 수 없다.
		// 그래서 session 에 담아서 전달하고 바로 지워주기도 귀찮다.
		// 따라서 spring 에서 RedirectAttrivutes 객체를 제공하여 
		// 마치 모델에 담아서 전달해주는 것처럼 사용할 수 잇다.
		
		// 리다이렉트 이동된 페이지에서도 한 번 사용할 수 있다.
		// Thymeleaf 에서 ${exception} 으로 참조 가능
		ra.addFlashAttribute("exception", pe); // 추후 페이지에서 ${exception.message} 로 얻어낼 수 있다.
		
		
		// /user/edit-password 으로 요청을 다시 하라고 리다이렉트 응답하기
		return "redirect:/user/edit-password";
	}
	
	
}
