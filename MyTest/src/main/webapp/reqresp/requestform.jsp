<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 페이지</title>
<jsp:include page="/include/resource.jsp" />
</head>
<body>
	<div class="container">
	<nav  style="--bs-breadcrumb-divider: '>';">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Index</a></li>
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/reqresp">요청 페이지</a></li>
		</ol>
	</nav>
	
	
	<p>아무거나 요청해보고 다양한 방법으로 응답 받아보기</p>
	
	<ul>
		<li><a href="responsejsp.jsp">JSP 파일이 응답</a></li>
		<li><a href="${pageContext.request.contextPath}/responsejava">Java 파일의 Servlet 객체가 응답</a>
			<p>responsejava 로 요청하면 "/MyTest/reqresp/responsejava" 왜???</p>
			<p>나는 "/MyTest/responsejava" 가 된다고 생각했는데..?</p>
		</li>
	</ul>
	
	</div>
</body>
</html>