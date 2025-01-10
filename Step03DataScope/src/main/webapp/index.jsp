<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인덱스 페이지</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/fortune">오늘의 운세</a></li>
			<li><a href="${pageContext.request.contextPath}/member">회원 한 명의 정보</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list">회원 목록</a></li>
			<li><a href="${pageContext.request.contextPath}/fortune.jsp">테스트1</a></li>
		</ul>
		
		<form action="${pageContext.request.contextPath}/test/save.jsp" method="post">
			<input type="text" name="nick" placeholder="입력..." />
			<button type="submit">닉네임 기억 시키기</button>
		</form>
		
		<%
			// session scope 에 "nick" 이라는 키값으로 저장된 문자열이 있는지 읽어와 본다.
			String nick = (String)session.getAttribute("nick");
		%>
		
		<%-- session scope (세션영역)에 값이 있으면 선택적으로 출력 --%> 
		<%if(nick!=null){ %>
			<p><strong><%=nick %></strong> 님 반갑습니다.</p>
			<a href="${pageContext.request.contextPath}/test/logout.jsp">로그아웃</a>
		<%} %>
		<%--<p><a href="${pageContext.request.contextPath}/test/sessionReset.jsp">기억(세션) 초기화</a></p> --%>
	</div>
</body>
</html>