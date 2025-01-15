<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인덱스 페이지</title>
<%-- 이 위치에 JSP 를 호출해서 공동으로 페이지를 만들어간다. --%>
<jsp:include page="/include/resource.jsp" />
</head>
<body class="d-flex flex-column min-vh-100">
	<div class="main flex-grow-1">
		
		<%-- 네비게이션 바 --%>
		<jsp:include page="/include/navbar.jsp">
			<jsp:param name="current" value="index"/>
		</jsp:include>
		
		<%-- 본 컨텐츠 --%>
		<div class="container">
		
		
		<ul>
			<li><a href="reqresp/requestform.jsp">여러 요청 방법</a></li>
			<li><a href="cafe/insertform.jsp">카페 게시글 작성</a></li>
			<li><a href="connection/test">DB 연결 테스트</a></li>
			<li><a href="guest/list.jsp">방문자 페이지</a></li>
		</ul>
		
		
		
		</div>
		
	</div>
</body>
<%-- 푸터 : body 요소 다음에 둬야 맨 아래로 내려간다. --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
</html>