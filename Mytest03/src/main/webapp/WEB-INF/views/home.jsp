<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
	<h1>Home Page</h1>
	<p>Context Path: ${pageContext.request.contextPath}</p>
	
	<ul>
		<li>다른 페이지 요청: <a href="${pageContext.request.contextPath}/fortune">오늘의 운세</a></li>
	</ul>
</body>
</html>