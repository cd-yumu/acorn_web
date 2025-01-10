<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
<!-- 이 부분은 resource.jsp 가 응답한다. -->
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body class="d-flex flex-column min-vh-100">
	<div class="main flex-grow-1">
		<!-- 이 부분은 navbar.jsp 가 응답한다. -->
		<jsp:include page="/include/navbar.jsp">
			<jsp:param value="index" name="current" />
		</jsp:include>
		<div class="container">
			<h1>Index Page</h1>
			<ul>
				<li><a href="connection/test.jsp">Connection TEST</a></li>
				<li><a href="member/list.jsp">Member</a></li>
				<li><a href="food/list.jsp">Food</a></li>
				<li><a href="guest/list.jsp">Guest</a></li>
			</ul>
		</div>

	</div>
</body>
<jsp:include page="/include/footer.jsp"/>
</html>
