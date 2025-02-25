package com.example.spring10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired CommentDao dao;
	
	@Override
	public int addComment(CommentDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int editComment(CommentDto dto) {
		return dao.update(dto);
	}

	@Override
	public int deleteComment(long num) {
		return dao.delete(num);
	}

	@Override
	public long getSequence() {
		return dao.getSequence();
	}

	@Override
	public CommentDto getData(long num) {
		return dao.getData(num);
	}

	@Override
	public List<CommentDto> getList(CommentDto dto) {
		return dao.getList(dto);
	}
	
	
	
}
