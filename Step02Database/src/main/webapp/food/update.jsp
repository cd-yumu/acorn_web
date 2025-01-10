<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 폼 전송되는 파라미터를 읽어와서 
	int num = Integer.parseInt(request.getParameter("num"));
	String type = request.getParameter("type");
	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
	
	// DB 에 정보 수정하기
	boolean isSuc = new FoodDao().update(new FoodDto(num,type,name,price));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%if(isSuc){ %>
	<p>음식 정보를 수정했습니다.</p>
		<a href="list.jsp">확인</a>
	<%} else { %>
		<p>음식 정보 수정 실패</p>
		<a href="updateform.jsp?name=<%=num%>">다시 수정하러 가자</a>
	<%} %>
	
	<%--
	<script>
		<%if(isSuc){%>
			alert("수정했습니다.")
			location.href = "list.jsp";
		<%} else {%>
			alert("수정을 실패했습니다.")
			location.href = "updateform.jsp?num=<%=num%>";
		<%}%>
	</script>
	 --%>
	 
</body>
</html>