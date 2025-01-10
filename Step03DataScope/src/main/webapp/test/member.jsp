<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto dto = (MemberDto)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	<div class="container">
		<h1><%=dto.getNum() %>번 회원 정보</h1>
		<p>이름: <%=dto.getName() %></p>
		<p>주소: <%=dto.getAddr() %></p>
		<br/>
		<%-- EL 을 이용해서 requestScope 에 담긴 내용을 추출할 수 있다. --%>
		<p>이름: ${requestScope.dto.getName()}</p>
		<p>주소: ${requestScope.dto.getAddr()}</p>
		<br/>
		<%-- requestScope --%>
		<p>이름: ${dto.getName() }</p>
		<p>주소: ${dto.getAddr() }</p>
		
	</div>
</body>
</html>