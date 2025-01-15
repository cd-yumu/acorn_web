<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 폼 전송되는 이름과 주소를 추출
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
	// 2. MemberDto 객체에 담는다.
	MemberDto dto = new MemberDto();
	dto.setName(name);
	dto.setAddr(addr);
	
	// 3. DB 에 저장
	MemberDao dao = new MemberDao();
	boolean isSuccess = dao.insert(dto);
	
	// 4. 응답 하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5">
		<h3>알림</h3>
		<%if(isSuccess){%>
			<p class="alert alert-success">
				<strong><%=name %></strong> 님의 정보를 저장했습니다.
				<a class="alert-link" href="list.jsp">>Go Back List Page...</a>
			</p>
		<%} else { %>
			<p class="alert alert-danger"><strong><%=name %></strong> 님의 정보 저장을 실패하였습니다.</p>
			<a class="alert-link" href="list.jsp">>Go Back List Page...</a>
		<%} %>
	</div>
</body>
</html>