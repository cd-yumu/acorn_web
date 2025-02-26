package com.example.mytest07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mytest07.dto.PostDto;
import com.example.mytest07.repository.PostDao;

@Service
public class PostServiceImpl implements PostService {

	@Autowired PostDao dao;
	
	@Override
	public List<PostDto> getList() {
		return dao.getList();
	}

}
