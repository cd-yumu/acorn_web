package com.example.mytest07.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mytest07.dto.UserDto;
import com.example.mytest07.repository.UserDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired private UserDao dao;
	
	// 로그인 처리에 사용될 서비스
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {	// /user/login/요청이 오면 Spring Security 가 직접 호출한다.
		// DB 에서 Dao 를 애용해 username 에 해당하는 사용자 정보(UserDto) 를 얻어와야 한다.
		UserDto dto = dao.getDataById(id);
		System.out.println("가져온 Dto: " + dto);
		
		// 만일 존재하지 않은 사용자라면
		if(dto == null) {
			throw new UsernameNotFoundException("존재 하지 않는 사용자 입니다.");
		}
		
		//권한 목록을 List 에 담아서  (지금은 1개 이지만)
		List<GrantedAuthority> authList=new ArrayList<>();
		authList.add(new SimpleGrantedAuthority(dto.getRole()));
		System.out.println("dto.getRole(): " + dto.getRole());
		
		//UserDetails 객체를 생성해서 
		UserDetails ud=new User(dto.getId(), dto.getPwd(), authList);
		
		System.out.println(ud);
		//리턴해준다.
		return ud; // 리턴해준 이 데이터를 가지고 Spring 이 처리해준다.
	}
	
}
