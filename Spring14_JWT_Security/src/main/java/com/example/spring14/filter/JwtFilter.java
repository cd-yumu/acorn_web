package com.example.spring14.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring14.service.CustomUserDetailsService;
import com.example.spring14.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{//OncePerRequestFilter 클래스를 상속받는다.
	
	@Autowired JwtUtil jstUtil;
	
	//쿠키에 저장된 token 의 이름 
	@Value("${jwt.name}") String jwtName;
	
	@Autowired CustomUserDetailsService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwtToken="";
		
		
		//쿠키에서 token 추출
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
        	//반복문 돌면서 
            for (Cookie cookie : cookies) {
            	// custum.properties 파일에 설정된  "jwtToken" 이라는 쿠키이름으로 저장된 value 가 있는지 확인해서
                if (jwtName.equals(cookie.getName())) {
                	//있다면 그 value 값을 디코딩해서 지역변수에 담기 
                    jwtToken = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    break;
                }
            }
        }
        // jwtToken 이라는 쿠키 데이터가 있는지 확인 / 있으면 데이터 내놔 (jwtToken 에 저장)
        
        
        //만일 쿠키에서 추출된 토큰이 없다면 
		if(jwtToken.equals("")) {
			//요청의 Header 에 "Authorization" 이라는 키값으로 전달된 문자열이 있는지 읽어와 본다.
			String authHeader = request.getHeader("Authorization");
			if(authHeader != null && authHeader.startsWith("Bearer ")) {
				jwtToken = URLDecoder.decode(authHeader, StandardCharsets.UTF_8);
			}
		}
		// 쿠키 없으면 헤더 검사해서 나오면 내놔 (jwtToken 에 저장)
       
		String userName=null;
		if(jwtToken.startsWith("Bearer ")) {
			// "Bearer " 를 제외한 뒤의 token 문자열을 얻어낸다.
			jwtToken = jwtToken.substring(7);
			// userName 을 token 으로 부터 얻어낸다.
			userName = jstUtil.extractUsername(jwtToken);
		
		}
		// 토큰에서 Bearer 제거하고 남은 코드로 사용자 이름(userName) 추출 (userName 에 저장)
		
		//userName 이 존재하고  Spring Security 에서 아직 인증을 받지 않은 상태라면 
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// 토큰이 유효한 토큰인지 체크한다음
			boolean isValid = jstUtil.validateToken(jwtToken);
			// 유효하다면 1회성 로그인(spring security 를 통과할 로그인) 을 시켜준다.
			if(isValid) {
				Claims claims = jstUtil.extractAllClaims(jwtToken); // JWT에서 모든 정보 가져오기
				String role = claims.get("role", String.class);
				
				/*	이름과 권한 정보만 저장할껀데 이렇게 까지 사용할 필요는 사실 없다.
				List<String> roles=List.of(role);
				List<SimpleGrantedAuthority> authorities=roles.stream().map(SimpleGrantedAuthority::new).toList();
				//토큰에 있는 정보를 이용해서 UserDetails 객체를 생성
				UserDetails ud=new User(userName, 
						"", //비밀번호는 필요 없음 
						authorities);
				*/
				
				// userName 과 role 정보를 담은 UserDeails 객체를 만든다.
				UserDetails ud = User.withUsername(userName)
						.password("")		// 비밀번호는 필요없지만 null 인 상태면 builder 에서 에러 발생
						.authorities(role)
						.build();
				
				//사용자가 제출한 사용자 이름과 비밀번호와 같은 인증 자격 증명을 저장
				UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(ud, null, 
							ud.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//Security 컨텍스트 업데이트 (1회성 로그인)
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		//다음 spring 필터 chain 진행하기
		filterChain.doFilter(request, response);
		
	} 
}