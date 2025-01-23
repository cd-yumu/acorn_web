<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty sessionScope.sessionDto}">
				<a href="${pageContext.request.contextPath}/user/signup-form.jsp">SignUp</a>
				<a href="${pageContext.request.contextPath}/user/login-form.jsp">Login</a>
			</c:when>
			<c:otherwise>
				<p>
					<a href="${pageContext.request.contextPath}/user/protected/info.jsp">${sessionDto.userName }</a> 님 로그인중..
					<a href="${pageContext.request.contextPath}/user/logout.jsp">Logout</a>
				</p>
			</c:otherwise>
		</c:choose>
		
		<h1>This is Index Page.</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/jstl/hello.jsp">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/member-only/play.jsp">Go to Play</a></li>
		</ul>
	</div>
</body>
</html>