<%@page import="com.google.gson.Gson"%>
<%@page import="dto.CommentDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.CommentDao"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int postNum = Integer.parseInt(request.getParameter("postNum"));
	List<CommentDto> list = CommentDao.getInstance().getList(postNum);
	
	Gson gson = new Gson();
%>
<%=gson.toJson(list) %>