package com.example.mytest07.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mytest07.dto.PostDto;

@Repository
public class PostDaoImpl implements PostDao {

	@Autowired SqlSession session;
	
	@Override
	public List<PostDto> getList() {
		return session.selectList("post.selectList");
	}

}
