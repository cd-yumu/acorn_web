package com.example.spring15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring15.util.JwtUtil;
import com.example.spring15.dto.UserDto;
import com.example.spring15.service.UserService;

@RestController
public class UserController {
	
	@Autowired JwtUtil jwtUtil;
	// SecurityConfig 클래스에서 Bean 이 된 AuthenticationManager 객체를 주입받기
	@Autowired AuthenticationManager authManager;
	
	@Autowired UserService userService;
	
	@PatchMapping("/user/password")
	public String updatePassword(@RequestBody UserDto dto) {
		userService.changePassword(dto);
		return "success!";
	}
	
	// json 문자열이 전송되는게 아니기 때문에 @RequestBody 는 필요 없다.
	@PatchMapping("/user")
	public String updateUser(UserDto dto) {
		userService.updatedUserInfo(dto);
		//.ok 는 정상 응답이라는 의미
		return "success!";
	}
	
	@GetMapping("/user")
	public UserDto getInfo() {
		// 이미 1회성 로그인이 되어 있기 때문에 userName 을 얻어낼 수 있다.
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.getByUserName(userName);
	}
	
	// 클라이언트가 토큰이 정상 동작하는지 확인할 요청 경로
	@GetMapping("/ping")
	public String ping() {
		return "pong";
		// 토큰이 만료 및 정지된 것이면 정상 응답되지 않는다.
	}

	// 토큰을 발급 받는 로그인 관련 메소드
	@PostMapping("/auth")
	public ResponseEntity<String> auth(@RequestBody UserDto dto) throws Exception{
		
		Authentication authentication = null;
		
		try {
			UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
			// 인증 메니저 객체를 이용해서 인증을 진행한다.
			authentication = authManager.authenticate(authToken);
			// authManager 는 서비스의 UserDetails 와 이 정보를 비교한다. 
			// 비교해서 이후 토큰 발급 여부에 영향
			
		} catch(Exception e) {
			e.printStackTrace();								// 에러 프린트 하고 
			// 401 UNAUTHORIZED 에러를 응답하면 문자열 한 줄 보내기
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패!");
			//throw new RuntimeException("아이디 혹은 비밀번호가 틀려요");	// 또, 500 번 오류 발생 시킴
			// ResponseEntity 타입을 사용하면 원하는 에러를 직접 발생 시켜서 몇 번 오류인지에 따라 세부적 처리를 할 수 있다.
		}
		
		
		// Authentication 객체에는 인증된 사용자 정보가 들어있다. userName, role 등등
		// 현재는 role 을 하나만 부여하기 때문에 0 번 방에 있는 데이터만 불러오면 된다.
		GrantedAuthority authority = authentication.getAuthorities().stream().toList().get(0);
		// ROLE_XXX 형식
		String role = authority.getAuthority();
		// "role" 이라는 키값으로 Map 에 담기
		Map<String, Object> claims = Map.of("role", role);
		
		
		// 예외가 발생하지 않고 여기까지 실행이 된다면 인증을 통과한 것이다.
		String token = jwtUtil.generateToken(dto.getUserName(), claims);
		// 발급 받은 토큰 문자열을 ResponseEntity 에 담아서 리턴한다.
		return ResponseEntity.ok("Bearer " + token);
	}
}
