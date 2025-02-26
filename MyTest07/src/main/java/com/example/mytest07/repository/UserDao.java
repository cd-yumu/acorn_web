package com.example.mytest07.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mytest07.dto.UserDto;

@Repository
public interface UserDao {
	
	public UserDto getDataById(String userId);
	
}
