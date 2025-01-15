<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// Get 방식 파라미터로 전달되는 회원 번호 추출 (updateform.jsp?num=x)
	int num = Integer.parseInt(request.getParameter("num"));
	
	// num 에 해당하는 회원 정보를 MemberDao 객체를 이용해 얻어낸다.
	MemberDto dto = new MemberDao().getData(num);
	
	// 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/updateform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav  style="--bs-breadcrumb-divider: '|||';">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath}/">Home</a>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath}/member/list.jsp">Member List</a>
				</li>
				<li class="breadcrumb-item active">Edit Member Form</li>
			</ol>
		</nav>
	
		<h1>Edit Member Form</h1>
		
		<h3><%=num %>번의 정보 입니다.</h3>
		<p>이름과 주소를 수정하실 수 있습니다.</p>
		
		<form action="update.jsp" method="post">
			<div class="mb-3">
				<label class="form-label" for="num">번호</label>
				<input class="form-control" type="text" name = "num" id = "num" value="<%=dto.getNum()%>" readonly/>
			</div>
			<div class="mb-3">
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name" value="<%=dto.getName()%>"/>
			</div>
			<div class="mb-3">
				<label class="form-label" for="addr">주소</label>
				<input class="form-control" type="text" name="addr" id="addr" value="<%=dto.getAddr()%>"/>
			</div>
			<button class="btn btn-success" type="submit">SUBMIT</button>
			<button class="btn btn-danger" type="reset">Cancel</button>
		</form>
	</div>
</body>
</html>