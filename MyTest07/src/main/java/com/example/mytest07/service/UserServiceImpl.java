package com.example.mytest07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mytest07.dto.UserDto;
import com.example.mytest07.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserDao dao;
	@Autowired private PasswordEncoder endcoder;
	
	@Override
	public void newUser(UserDto dto) {
		//비밀번호 암호화 해서 저장할 것!
		String encodedPwd = endcoder.encode(dto.getPwd());
		dto.setPwd(encodedPwd);
		
		int resultCount = dao.insertUser(dto);
	}

}
