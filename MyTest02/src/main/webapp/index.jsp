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
		<li><a href="${pageContext.request.contextPath}/user/login-form.jsp">LOGIN</a></li>
		<li><a href="${pageContext.request.contextPath}/post/list.jsp">POSTS</a></li>
	</ul>
</body>
</html>