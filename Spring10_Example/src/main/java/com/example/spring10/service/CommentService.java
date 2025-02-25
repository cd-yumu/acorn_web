package com.example.spring10.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring10.dto.CommentDto;

@Service
public interface CommentService {
	
	public int addComment(CommentDto dto);
	public int editComment(CommentDto dto);
	public int deleteComment(long num);
	public long getSequence();
	public CommentDto getData(long num);
	public List<CommentDto> getList(CommentDto dto);
	
	
}
