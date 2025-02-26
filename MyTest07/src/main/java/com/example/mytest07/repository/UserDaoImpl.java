package com.example.mytest07.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mytest07.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired SqlSession session;
	
	@Override
	public UserDto getDataById(String id) {
		System.out.println("쿼리 실행 전 검색할 id:" + id);
		System.out.println("session.selectOne(\"user.getDataById\", id): " + session.selectOne("user.getDataById", id));
		return session.selectOne("user.getDataById", id);
	}

	@Override
	public int insertUser(UserDto dto) {
		System.out.println("쿼리문 실행 전 dto 확인"+dto);
		return session.insert("user.insertUser", dto);
	}

}
