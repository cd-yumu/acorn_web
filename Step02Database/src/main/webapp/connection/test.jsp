<%@page import="test.util.DbcpBean"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// DbcpBean 객체를 이용해서 Connection 객체 얻어오기
	Connection conn = new DbcpBean().getConn();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/connection/test.jsp</title>
</head>
<body>
	<div class = "container">
		<h1>Connection Test Page</h1>
		<!-- 위 문자열은 무조건 클라이언트에게 출력된다. -->
		<%if(conn != null){%>
			<!-- Conn 이 null 이 아니면 여기 있는 문자열이 클라이언트에게 출력됨 -->
			 <p>Connection 객체를 성공적으로 얻어왔습니다.</p>
		<%} else{%>
			<!-- Conn 이 null 이면 여기에 있는 문자열이 클라이언트에게 출력됨 -->
			<p>Connection 객체 얻어오기 실패</p>
		<%} %>
	</div>
</body>
</html>