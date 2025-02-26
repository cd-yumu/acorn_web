package com.example.mytest07.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mytest07.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired SqlSession session;
	
	@Override
	public UserDto getDataById(String userId) {
		return session.selectOne("user.getDataById", userId);
	}

	@Override
	public int insertUser(UserDto dto) {
		return session.insert("user.insert", dto);
	}

}
