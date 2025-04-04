<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 폼 전송되는 수정할 회원의 정보를 읽어온다.
	int num = Integer.parseInt(request.getParameter("num"));
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	String pwd = request.getParameter("pwd");
	
	// GuestDao 객체의 참조값
	GuestDao dao = GuestDao.getInstance();
	
	// DB 에 저장된 비밀번호
	String savedPwd = dao.getData(num).getPwd();
	
	// 작업의 성공 여부를 저장할 변수를 만들고 false 를 초기값으로 부여한다.
	boolean isSuc = false;
	// 만약 비밀번호가 일치한다면
	if(pwd.equals(savedPwd)){
		// 수정할 글 정보를 GuestDto 객체에 담고
		GuestDto dto = new GuestDto();
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setContent(content);
		// DB 에 수정 반영한다.
		isSuc = dao.update(dto);
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업데이트 결과</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuc){ %>
			<p>
				<strong><%=writer %></strong> 님이 작성한 글이 수정되었습니다.
				<a href="list.jsp">목록 보기</a>
			</p>
		<%} else { %>
			<p>
				수정 실패! 
				<a href="updatefrom.jsp?num=<%=num%>">다시 수정</a>
				<a href="list.jsp">목록 보기</a>
			</p>
		<%} %>
	</div>
</body>
</html>