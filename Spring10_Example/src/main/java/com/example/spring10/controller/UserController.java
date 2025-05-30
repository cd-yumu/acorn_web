package com.example.spring10.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring10.dto.UserDto;
import com.example.spring10.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired private UserService service;
	
	@PostMapping("/user/update")
	public String update(UserDto dto) {
		// 서비스를 이용해서 개인 정보를 수정하고
		service.updatedUserInfo(dto);
		// 다시 개인 정보 보기로 리다이렉트 이동한다.
		return "redirect:/user/info";
	}
	
	
	/*
	 * @AuthenticationPrincipal 어노테이션을 이용하면 로그인된 사용자의 정보를 담고 있는
	 * UserDetails 객체를 얻어낼 수 있다.
	 */
	
	@GetMapping("/user/edit")
	public String edit(@AuthenticationPrincipal UserDetails ud, Model model) {
		// 로그인된 사용자의 userName  
		String userName = ud.getUsername();
		UserDto dto = service.getByUserName(userName);
		model.addAttribute("dto", dto);
		
		return "user/edit"; 
	}
	
	@PostMapping("/user/update-password")
	public String updatePassword(UserDto dto, HttpSession session) {
		// 서비스를 이용해서 비밀번호를 수정하고
		service.changePassword(dto);
		// 로그아웃 처리
		session.invalidate();
		return "user/update-password";
	}
	
	@GetMapping("/user/edit-password")
	public String editPassword() {
		
		return "user/edit-password";
	}
	
	@GetMapping("/user/info")
	public String ingo(Model model) {
		// 로그인 되어 있는 userName 얻어내기
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		// 로그인된 userName 을 이용해서 사용자 정보를 얻어와서
		UserDto dto = service.getByUserName(userName);
		// dto 라는 키값으로 담고
		model.addAttribute("dto", dto);
		// view page 에서 응답하기
		return "user/info";
	}
	
	// 사용 가능한 아이디인지 여부를 json 문자열로 응답하기
	@ResponseBody // 응답 데이터가 View Page 가 아니고 리턴값 그 자체이기 때문에 (Map 또는 Dto 경우)
	@GetMapping("/user/checkid")
	public Map<String, Boolean> checkId(String userName){
		
		// get 방식 파라미터로 전달되는 userName 을 이용해서 UserDto 를 얻어와 본다.
		UserDto dto = service.getByUserName(userName);
		// dto 가 null 이면 사용 가능
		boolean canUse = dto == null ? true : false;
		
		// Map 객체에 사용가능 여부를 담아서 리턴한다.
		Map<String, Boolean> map = Map.of("canUse", canUse);
		
		return map;
	}
	
	// 회원 가입 요청 처리
	@PostMapping("/user/signup")
	public String signup(UserDto dto) {
		// 서비스를 이용해서 회원 가입 처리를 한다.
		service.createUser(dto);
		return "user/signup";
	}
	
	// 회원가입 폼 요청 처리
	@GetMapping("/user/singup-form")
	public String signupForm() {
		
		return "user/signup-form";
	}
	
	//세션 허용갯수 초과시 
	@GetMapping("/user/expired")
	public String userExpired() {
		return "user/expired";
	}	
	
	//권한 부족시 or 403 인 경우 
	@RequestMapping("/user/denied")  //GET, POST 등 모두 가능
	public String userDenied() {
		return "user/denied";
	}
	
	//ROLL_STAFF , ROLL_ADMIN 만 요청 가능
	@GetMapping("/staff/user/list")
	public String userList() {
		
		return "user/list";
	}
	//ROLL_ADMIN 만 요청 가능
	@GetMapping("/admin/user/manage")
	public String userManage() {
		
		return "user/manage";
	}
	
	
	@GetMapping("/user/loginform")
	public String loginform() {
		// templates/user/loginform.html 페이지로 forward 이동해서 응답 
		return "user/loginform";
	}
	
	//로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로 
	@GetMapping("/user/required-loginform")
	public String required_loginform() {
		return "user/required-loginform";
	}
	// POST 방식 /user/login 요청후 로그인 성공인경우 forward 이동될 url 
	@PostMapping("/user/login-success")
	public String loginSuccess() {
		return "user/login-success";
	}
	
	//로그인 폼을 제출(post) 한 로그인 프로세즈 중에 forward 되는 경로이기 때문에 @PostMapping 임에 주의!
	@PostMapping("/user/login-fail")
	public String loginFail() {
		//로그인 실패임을 알릴 페이지
		return "user/login-fail";
	}	
}
















