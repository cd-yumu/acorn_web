<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 수정할 글 번호 읽어오기
	int num = Integer.parseInt(request.getParameter("num"));
	// 글 번호에 해당하는 글 정보를 DB 에서 얻어오기
	GuestDto dto = GuestDao.getInstance().getData(num);
	// 글 수정 양식을 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업데이트 폼</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav style="--bs-breadcrumb-divider: '|||';">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/guest/list.jsp">Guest List</a></li>
				<li class="breadcrumb-item active">글 수정</li>
			</ol>
		</nav>
	
		<h1>방명록 글 수정 폼</h1>
		<form action="update.jsp" method="post">
			<div class="mb-2">
				<lebel class="form-label" for="num">번호</lebel>
				<input type="text" name="num" id="num" class="form-controle" value="<%=dto.getNum()%>" readonly/>
			</div>
			<div class="mb-2">
				<label for="writer" class="form-label">작성자</label>
				<input type="text" name="writer" id="writer" value="<%=dto.getWriter() %>" class="form-controle" />
			</div>
			<div class="mb-2">
				<label for="content" class="form-label">내용</label>
				<textarea name="content" id="content" style="height:200px"><%=dto.getContent() %></textarea>
			</div>
			<div class="mb-2">
				<label for="pwd" class="form-label">글 작성 시 입력한 비밀번호</label>
				<input type="text" name="pwd" id="pwd" class="form-controle" />
			</div>
			<button class="btn btn-outline-success btn-sm" type="submit">수정 확인</button>
			<button class="btn btn-outline-danger btn-sm" type="reset">수정 취소</button>
		</form>
	</div>
</body>
</html>