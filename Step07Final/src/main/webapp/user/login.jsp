<%@page import="java.net.URLEncoder"%>
<%@page import="test.user.dto.SessionDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 폼 전송 되는 데이터를 읽어오기
	String userName = request.getParameter("userName");
	String password = request.getParameter("password");
	// 아이디에 해당되는 회원정보를 얻어오기
	UserDto dto = UserDao.getInstance().getData(userName);
	// 실제로 존재하는 아이디 이고 존재한다면 비민번호도 일치하는지 비교해서
	
	boolean isLoginSuccess = false;
	if(dto != null){
		if(dto.getPassword().equals(password)){
			// 로그인 처리를 한다(로그인 된 정보를 세션영역에 담는다.)
			SessionDto sessionDto = new SessionDto(dto.getNum(), dto.getUserName(), dto.getRole());
			session.setAttribute("sessionDto", sessionDto);
			isLoginSuccess = true;
		}
	}
	
	
	// 로그인 후 가야할 목적지 정보
	String url = request.getParameter("url");
	// 로그인 실패를 대비해서 목적지 정보를 인코딩한 결과도 준비한다.
	String encoderUrl = URLEncoder.encode(url,"UTF-8");
	
	
	// 일치하면 로그인 처리 후 응답, 일치하지 않으면 일치하지 않다고 응답.
	
	// 쌤팁: 로직 짜는게 햇갈릴 때 논리에 맞는 주석을 작성한 후에 코딩하는 것이 좋다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%if(isLoginSuccess){%>
			<p>
				<strong><%=dto.getUserName() %></strong>님 로그인되었습니다.
				<a href="<%=url%>">확인</a>
			</p>
		<%} else { %>
			<p>
				아이디 혹은 비밀번호가 일치하지 않습니다.
				<a href="${pageContext.request.contextPath}/user/login-form.jsp?url=<%=encoderUrl %>">다시 입력</a>
			</p>
		<%} %>
	</div>
</body>
</html>