<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
	<h1>Index Page</h1>
	<ul>
		<li><a href="${pageContext.request.contextPath}/user/signup-form.jsp">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath}/user/login-form.jsp">로그인</a></li>
		<li><a href="${pageContext.request.contextPath}/post/list.jsp">글 목록</a></li>
	</ul>
</body>
</html>