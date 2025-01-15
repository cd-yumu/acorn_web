<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방문자 페이지</title>
<jsp:include page="/include/resource.jsp" />
</head>
<body>
		
	<div class="container">
	
		<h1>방문자 목록</h1>
		<a href="insertform.jsp">새 글 작성</a>
		<table>
			<thead>
				<tr>
					<th>No</th>
					<th>이름</th>
					<th>내용</th>
					<th>등록일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	
	</div>
	
</body>
</html>