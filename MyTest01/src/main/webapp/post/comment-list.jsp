<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="test.post.dao.CommentDao"%>
<%@page import="test.post.dto.CommentDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		댓글의 페이지 번호와 원글의 번호 데이터를 추출해내서
		댓글 정보 DTO 객체에 
		한 페이지에 표시할 객체 개수와 전체 개수를 가지고 보여줄 댓글의 수를 계산한 값을 담고
		그 DTO 객체를 이용해 DB에서 댓글 데이터를 추출하는 메소드를 사용해
		댓글 DTO 들이 담긴 List 데이터를 뽑아내서 
		전체 댓글 개수와 함께 응답한다.
	*/

	// 댓글의 페이지 번호
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	// 원글의 글번호
	long postNum = Long.parseLong(request.getParameter("postNum"));
	
	CommentDto dto = new CommentDto();
	dto.setPostNum(postNum); // 원글의 글번호 dto에 담기
	
	// 한 페이지에 댓글을 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=10;

	// 보여줄 페이지의 시작 ROWNUM
	int startRowNum = 1+(pageNum-1)*PAGE_ROW_COUNT;
	// 보여줄 페이지의 끝 ROWNUM
	int endRowNum = pageNum*PAGE_ROW_COUNT;
	// 계산된 값을 dto 에 담는다.
	dto.setStartRowNum(startRowNum);
	dto.setEndRowNum(endRowNum);
	
	// 전체 댓글의 갯수
	int totalRow = CommentDao.getInstance().getCount(postNum);
	// 전체 페이지의 갯수 구하기
	int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	
	// pageNum 에 해당하는 댓글 목록을 얻어낸다.
	List<CommentDto> list = CommentDao.getInstance().getList(dto); 
	
	// Gson 객체에 전달할 Map 객체를 구성한다.
	Map<String, Object> map = new HashMap<>();
	map.put("list", list);
	map.put("totalPageCount", totalPageCount);
	
	Gson gson = new Gson();
	
%>
<%=gson.toJson(map) %>