<%@page import="java.util.ArrayList"%>
<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// DB 에서 가져온 회원 목록 가정
	List<MemberDto> list = new ArrayList<>();
	list.add(new MemberDto(1, "김구라", "노량징"));
	list.add(new MemberDto(2, "해골", "행신동"));
	list.add(new MemberDto(3, "원숭이", "동물원"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<%for(MemberDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getAddr() %></td>
				</tr>
			<%} %>
		</tbody>
	</table>
</body>
</html>