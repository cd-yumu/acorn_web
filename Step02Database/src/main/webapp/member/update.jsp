<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 폼 전송되는 수정될 회원의 번호, 이름, 주소를 추출한다.
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
	// 2. DB 에 수정 반영한다.
	MemberDto dto = new MemberDto(num, name, addr);
	MemberDao dao = new MemberDao();
	boolean isSuccess = dao.update(dto);
	
	// 3. 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<h3>알림</h3>
	<%if(isSuccess){ %>
		<p class="alert alert-success">
			<%=num %>번 회원의 정보를 수정하였습니다.
			<a class="alert-link" href="list.jsp">>Go Back List Page...</a>
		</p>
	<%} else { %>
		<p class="alert alert-danger">
			<%=num %>번 회원의 정보를 수정하는데 실패하였습니다
			<a class ="alert-link" href="list.jsp">>Go Back List Page...</a>
		</p>
	<%} %>
</body>
</html>