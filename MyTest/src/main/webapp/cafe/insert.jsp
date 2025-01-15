<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 요청과 함께 전송된 데이터 얻기
// 제목
String title = request.getParameter("title");
// 내용
String content = request.getParameter("content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카페 새 글 작성 결과</title>
<jsp:include page="/include/resource.jsp" />
</head>
<body>
	<div class="container">
		<nav  style="--bs-breadcrumb-divider: '>';">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Index</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/cafe/insertform.jsp">새 글 작성</a></li>
				<li class="breadcrumb-item">새 글 저장</li>
			</ol>
		</nav>
		<fieldset> 
			<legend>글 작성 결과</legend> 
			<label>
			<p><strong>제목: </strong> <%=title%></p>
			<p><strong>내용</strong></p><%=content%>
			</label> 
		</fieldset>
	</div>

</body>
</html>