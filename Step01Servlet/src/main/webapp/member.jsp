<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// DB 에서 읽어온 데이터 가정
	MemberDto dto = new MemberDto(1, "김구라", "노량진");
	
	String num = request.getParameter("num");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member.jsp</title>
</head>
<body>
	<h3><%=num %>번의 회원 정보</h3>
	<p>NUM: <%=dto.getNum() %></p>
	<p>NAME: <%=dto.getName() %></p>
	<p>ADDR: <%=dto.getAddr() %></p>
</body>
</html>