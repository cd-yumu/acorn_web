<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// GET 방식 요청 파라미터 읽어오기
	String msg = request.getParameter("msg");
	System.out.println("msg: "+msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send 페이지</title>
</head>
<body>
	<p>메시지 잘 받았어 클라이언트야!</p>
</body>
</html>