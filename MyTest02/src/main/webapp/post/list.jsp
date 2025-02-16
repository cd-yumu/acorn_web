<%@page import="dto.PostDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.PostDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	List<PostDto> list = PostDao.getInstance().getList();
	request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post List Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<h1>Post List Page</h1>
		<table class="table table-striped text-center">
			<thead class="table-dark">
				<tr>
					<th>No</th>
					<th>Title</th>
					<th>Writer</th>
					<th>View</th>
					<th>CreatedDate</th>
					<th>UpdatedDate</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${list}">
					<tr>
						<td>${post.num}</td>
						<td><a href="${pageContext.request.contextPath}/post/post.jsp?postNum=${post.num}">${post.title}</a></td>
						<td>${post.writer}</td>
						<td>${post.viewCount}</td>
						<td>${post.createdAt}</td>
						<td>${post.updatedAt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>