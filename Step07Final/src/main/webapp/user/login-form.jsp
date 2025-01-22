<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// get 방식 파라미터 url 이라는 이름으로 전달되는 값이 있는지 읽어와 본다.
	String url = request.getParameter("url");
	// 만일 넘어오는 값이 없으면
	if(url==null){
		// 로그인 후에 인덱스 페이지로 갈 수 있도록 한다.
		String cPath = request.getContextPath();
		url = cPath + "/index.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login-form.jsp</title>
</head>
<body>
	<h1>로그인 폼</h1>
	<form action="${pageContext.request.contextPath}/user/login.jsp" method="post">
		<%-- 로그인 후에 이동할 페이지 정보도 같이 전송되도록 한다. --%>
		<input type="hidden" name="url" value="<%=url %>" />
		<div>
			<label for="userName">아이디</label>
			<input type="text" name="userName" id="userName"/>
		</div>
		<div>
			<label for="password">비밀번호</label>
			<input type="password" name="password" id="password"/>
		</div>
		<button type="submit">로그인</button>
	</form>
</body>
</html>