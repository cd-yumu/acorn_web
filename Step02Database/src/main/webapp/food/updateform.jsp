<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 수정할 해당 리스트 데이터 가져오기
	int num = Integer.parseInt(request.getParameter("num"));
	FoodDto dto = new FoodDao().getData(num);
	
	String type = dto.getType();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>음식 수정 양식</h1>
		
		<form action="update.jsp">
		<input type="hidden" name="num" value="<%=num%>"/>
		<!-- 화면에 보이지는 않지만 폼 제출할 때 -->
		
		<div>
			<label for="type">타입: </label> 
			<select name="type" id="type">
				<option <%=(type.equals("한식"))?"selected":""%>>한식</option>
				<option <%=(type.equals("중식"))?"selected":""%>>중식</option>
				<option <%=(type.equals("양식"))?"selected":""%>>양식</option>
				<option <%=(type.equals("일식"))?"selected":""%>>일식</option>
				<option <%=(type.equals("기타"))?"selected":""%>>기타</option>
			</select>
		</div>
		<div>
			<label for="name">이름: </label> <input type="text" name="name"
				id="name" value="<%=dto.getName() %>" />
		</div>
		<div>
			<label for="price">가격: </label> <input type="text" name="price"
				id="price" value="<%=dto.getPrice() %>" />
		</div>
		
		<button type="submit">수정하기</button>
		<button type="reset">취소하기</button>
	</form>
		
	</div>

</body>
</html>