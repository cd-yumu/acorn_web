<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//DB 에서 읽어온 데이터 가정
	List<String> names = new ArrayList<>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/friends.jsp</title>
</head>
<body>
	<h1>친구 목록</h1>
	<ul>
		<%for(String tmp:names){%>
			<li><%=  tmp %></li>
		<%};%>
		
		<li><%=names.get(0) %></li>
		<li><%=names.get(1) %></li>
		<li><%=names.get(2) %></li>
	</ul>
</body>
</html>