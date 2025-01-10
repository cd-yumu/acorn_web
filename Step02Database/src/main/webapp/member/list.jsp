<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// MemberDao 객체를 이용해서 회원 목록 얻어오기
	MemberDao dao = new MemberDao();
	List<MemberDto> list = dao.getLsit();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/list</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<%-- navbar.jsp 페이지를 포함 시키고 해당 페이지에 "current" 라고 파라미터 명으로 "member" 전달하기 --%>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="member" name="current"/>
	</jsp:include>
	<div class="container">
		<a href="insertform.jsp">
			<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-add" viewBox="0 0 16 16">
	  			<path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
	  			<path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
			</svg>
			<span class="visually-hidden">Add Member</span>
		</a>
		
		
		<h1>Member Info</h1>
		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
					<th>Number</th>
					<th>Name</th>
					<th>Address</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<% for(MemberDto tmp : list){ %>
					<tr>
						<td><%=tmp.getNum()%></td>
						<td><%=tmp.getName()%></td>
						<td><%=tmp.getAddr()%></td>
						<td>
							<a href="updateform.jsp?num=<%=tmp.getNum()%>">
								<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
  								<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
								</svg>
								<span class="visually-hidden">Edit</span>
							</a>
						</td>
						<td>
							<a href="delete.jsp?num=<%=tmp.getNum()%>">
								<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
								  <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"/>
								</svg>
								<span class="visually-hidden">Delete</span>
							</a>
						</td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>