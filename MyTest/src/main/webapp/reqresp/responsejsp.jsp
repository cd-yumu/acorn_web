<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 파일이 응답</title>
<jsp:include page="/include/resource.jsp" />
</head>
<body>
	<div class="container">
		
		<nav  style="--bs-breadcrumb-divider: '>';">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Index</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/reqresp/requestform.jsp">여러 요청 방법</a></li>
			</ol>
		</nav>
		
		
		<p>요청을 responsejsp.jsp 에게 했다.</p>
		<p>그래서 responsejsp.jsp 이 응답한다.</p>
	</div>
</body>
</html>