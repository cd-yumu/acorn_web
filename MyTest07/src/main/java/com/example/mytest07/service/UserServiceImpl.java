package com.example.mytest07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mytest07.dto.UserDto;
import com.example.mytest07.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserDao dao;
	
	@Override
	public void newUser(UserDto dto) {
		dao.insertUser(dto);
	}

}
