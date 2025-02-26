package com.example.mytest07.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mytest07.dto.UserDto;

@Repository
public interface UserDao {
	
	// 입력된 아이디에 맞는 정보 가져오는 메소드
	public UserDto getDataById(String userId);
	
	// 새 사용자 추가 메소드
	public int insertUser(UserDto dto);
}
