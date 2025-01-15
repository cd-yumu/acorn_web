<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%
	// 폼 전송되는 writer, content, pwd 추출
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	String pwd = request.getParameter("pwd");
	
	// DB 에 저장한다.
	// 글 정보를 GeustDto 에 담는다.
	GuestDto dto = new GuestDto();
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setPwd(pwd);
	
	// GuestDao 객체의 참조값 얻어오기
	GuestDao dao = GuestDao.getInstance();
	boolean isSuc = dao.insert(dto);
	
	// 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가 결과</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<%if(isSuc){ %>
		<p>
			<%=writer %> 님이 작성한 글을 성공적으로 저장 했습니다.
			<a href="list.jsp">목록보기</a>
		</p>
	<%} else { %>
		<p>
			저장 실패!
			<a href="insertform.jsp">다시 작성</a>
		</p>
	<%} %>
</body>
</html>