<%@page import="test.post.dao.CommentDao"%>
<%@page import="test.post.dto.CommentDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
	
%>