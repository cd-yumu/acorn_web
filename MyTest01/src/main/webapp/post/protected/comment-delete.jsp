<%@page import="test.post.dao.CommentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 삭제할 댓글번호를 얻어낸다.
	long num=Long.parseLong(request.getParameter("num"));
	//CommentDao 객체를 이용해서 삭제하고 성공 여부를 응답받는다.
	boolean isSuccess=CommentDao.getInstance().delete(num);
	//성공여부 응답
%>
{"isSuccess":<%=isSuccess%>}