package com.example.spring10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.repository.PostDao;

@Repository
public class PostServiceImpl implements PostService{
	
	@Autowired private PostDao postDao;

	/*
	 *  pageNum 과 검색 조건, 키워드가 담겨있을 수 있는 PostDto 를 전달하면
	 *  해당 글 목록을 리턴하는 메소드
	 *  
	 *  *어디서든지 페이지 번호와 검색 조회 정보가 담긴 Dto 만 전달하면 사용할 수 있음으로
	 *  Post 에서만 한정된 것은 아니다!
	 */
	
	@Override
	public PostListDto getPosts(int pageNum, PostDto search) {
		
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=5;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;
		
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=postDao.getCount(search);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}	
		
		// startRowNum 과 endRowNum 을 PostDto 객체에 담아서
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		// 글 목록 얻어오기
		List<PostDto> list = postDao.getList(search);
		
		String findQuery = "";
		if(search.getKeyword() != null) {
			findQuery = "&keyword=" + search.getKeyword() + "&condition=" + search.getCondition();
		}
		
		// 예전에 요청 범위에 일일이 넣었던 것을 전용 dto 로 만들어서 한 줄 코딩으로 빌드 후 그 객체로 전달한다.
		PostListDto dto = PostListDto.builder()
				.list(list)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.pageNum(pageNum)
				.totalRow(totalRow)
				.findQuery(findQuery)
				.condition(search.getCondition())
				.keyword(search.getKeyword())
				.build();
		return dto;
	}

	@Override
	public void insertPost(PostDto dto) {
		
	}

}
