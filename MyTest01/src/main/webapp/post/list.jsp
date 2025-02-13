<%@page import="test.post.dto.PostDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 검색 조건이 있는지 읽어와 본다
	String condition = request.getParameter("condition");
	String keyword = request.getParameter("keyword");
	
	// 페이지 요청시 함께 보낼 쿼리 문자열
	String findQuery = null;
	
	// 있다면 dto 에 해당 정보를 담는다.
	PostDto dto = new PostDto();
	
	// condtion 파라미터 값이 있다면
	if(condition != null){	
		//
		dto.setCondition(condition);
		dto.setKeyword(keyword);
		findQuery = "&condition="+condition+"&keyword"+keyword;
	}
 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	
		<h1>게시글 목록입니다.</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	
	</div>
</body>
</html>