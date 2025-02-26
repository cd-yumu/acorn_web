package com.example.mytest07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	// 인증 설정
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		// Spring Security 가 인증과정을 거치지 않는 요청 경로
		String[] whiteList= {"/","/user/signup-form","/user/signup-proc","/user/login-success","/user/login-fail"};
		
		httpSecurity
		.headers(header ->
			// iframe 을 사용할 수 있도록 설정 (default 값은 사용 불가, SmartEditor 에서 필요함)
			header.frameOptions(option -> option.sameOrigin())
		)
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config ->
			config
				.requestMatchers(whiteList).permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
		)
		.formLogin(config ->
			config
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 
				.loginPage("/user/login-form")
				//로그인 처리를 할때 요청될 url 설정 ( spring security 가 login 처리를 대신 해준다)
				.loginProcessingUrl("/user/login-proc")
				//로그인 처리를 대신 하려면 어떤 파라미터명으로 username 과 password 가 넘어오는지 알려주기 
				.usernameParameter("id")
				.passwordParameter("pwd")
				.successHandler(new AuthSuccessHandler()) //로그인 성공시 핸들러 등록 
				.failureForwardUrl("/user/login-fail")	// 로그인 실패시 이동될 페이지 (로그인 폼으로 다시 이동)
				.permitAll() //위에 명시한 모든 요청경로를 로그인 없이 요청할수 있도록 설정 
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout-proc")//Spring Security 가 자동으로 로그아웃 처리 해줄 경로 설정
				.logoutSuccessUrl("/")//로그 아웃 이후에 리다일렉트 시킬 경로 설정
				.permitAll()
		)
		.exceptionHandling(config ->
			//403 forbidden 인 경우 forward 이동 시킬 경로 설정 
			config.accessDeniedPage("/user/denied")
		)
		.sessionManagement(config -> 
			config
				.maximumSessions(1) //최대 허용 세션 갯수
				.expiredUrl("/user/expired") //허용 세션 갯수가 넘어서 로그인 해제된 경우 리다일렉트 이동시킬 경로
		);
		
		// 설정 정보를 가지고 있는 HttpSecurity 객체의 built() 메소드를 호출해서 리턴되는 객체를 리턴해준다.
		return httpSecurity.build();
	}
	
	// 비밀번호 암호화 메소드
	@Bean
	PasswordEncoder passwordEncoder() { 
		//여기서 리턴해주는 객체도 bean 으로 된다.
		return new BCryptPasswordEncoder();
	}
	
	// 인증 메소드
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http,
		BCryptPasswordEncoder encoder, UserDetailsService service) throws Exception{
		//적절한 설정을한 인증 메니저 객체를 리턴해주면 bean 이 되어서 Spring Security 가 사용한다 
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(service)
				.passwordEncoder(encoder)
				.and()
				.build();
	}
}
