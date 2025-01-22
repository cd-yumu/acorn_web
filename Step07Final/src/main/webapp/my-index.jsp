<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
</head>
<body>
	<div class="container">
		<h1>This is Index Page</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/my-index.jsp">홈</a></li>
			<li><a href="${pageContext.request.contextPath}/my-user/my-signup-form.jsp">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath}/my-user/my-login-form.jsp">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/my-member-only/my-play.jsp">Play(member only)</a></li>
		</ul>
	</div>
</body>
</html>