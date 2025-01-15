<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 요청된 데이터 얻기
	String type = request.getParameter("type");
	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
	
	// DB 에 반영하기
	boolean isSuc = new FoodDao().insert(new FoodDto(0, type, name, price));

	// 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		<%if(isSuc){%>
			alert("<%=name%> 을(를) 추가했습니다.")
			location.href = "list.jsp";
		<%} else {%>
			alert("새 목록 추가에 실패했습니다.")
			location.href = "insertform.jsp";
		<%}%>
	</script>
</body>
</html>