package com.example.spring10.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostListDto {
	private List<PostDto> list;		// 글 목록 정보
	private int startPageNum;		// 시작 페이지 번호
	private int endPageNum;			// 끝 페이지 번호
	private int totalPageCount;		// 총 페이지 개
	private int pageNum;			// 현태 페이지 번호
	private int totalRow;			// 총 글 개수
	private String findQuery;		// 페이지 및 검색 조건 쿼리문
	private String condition;		// 검색 조건
	private String keyword;			// 검색 키워드
}