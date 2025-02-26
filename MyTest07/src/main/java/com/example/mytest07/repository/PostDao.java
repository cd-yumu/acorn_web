package com.example.mytest07.repository;

import java.util.List;

import com.example.mytest07.dto.PostDto;

public interface PostDao {
	
	public List<PostDto> getList();
}
