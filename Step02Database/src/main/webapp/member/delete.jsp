<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 1. Get 방식 파라미터로 전달되는 삭제할 회원의 번호를 얻어온다.
int num = Integer.parseInt(request.getParameter("num"));
//	getParamter 는 무조건 문자형 데이터가 리턴된다.

// 2. MemberDao 객체를 이용해서 실제 DB 에서 삭제
MemberDao dao = new MemberDao();
boolean isSuccess = dao.delete(num);

// 3. 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/delete.jsp</title>
</head>
<body>
	<div class="container">
		<h3>Result</h3>
		<%if (isSuccess) {%>
		<p><%=num %>번 회원 정보 삭제 성공</p>
		<a href="list.jsp">>Go Back List Page...</a>
		<%} else {%>
		<p>삭제 실패</p>
		<a href="list.jsp">>Go Back List Page...</a>
		<%}%>
	</div>
</body>
</html>