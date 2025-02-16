<%@page import="dto.CommentDto"%>
<%@page import="dao.CommentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int postNum = Integer.parseInt(request.getParameter("postNum"));
	String postWriter = request.getParameter("postWriter");
	String content = request.getParameter("content");
	
	CommentDto dto = new CommentDto();
	dto.setPostNum(postNum);
	dto.setTargetWriter(postWriter);
	dto.setContent(content);
	
	boolean isSuccess = CommentDao.getInstance().insert(dto);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>