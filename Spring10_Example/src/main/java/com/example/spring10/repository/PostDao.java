package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.PostDto;

public interface PostDao {
	public List<PostDto> getList(PostDto dto);
	public int insert(PostDto dto);
	public int delete(long num);
	public int update(PostDto dto);
	public int getCount(PostDto dto);
	// 저장 할 글 번호를 생성해서 리턴해주는 메소드
	public long getSequence();
	public PostDto getData(long num);		// 글 번호만으로 가져오는 메소드
	public PostDto getDetail(PostDto dto);	// dto 정보를 이용해 가져오는 메소드 (동적SQL)
	public int insertReaded(long num, String sessionId);
	public boolean isReaded(long num, String sessionId);
	public int addViewCount(long num);
}
