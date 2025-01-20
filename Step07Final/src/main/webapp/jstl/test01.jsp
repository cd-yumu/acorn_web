<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 사용을 위한 태그 라이브러리 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
	// 테스트를 위해 sample 데이터를 request scope 에 담는다.
	List<String> names = new ArrayList<String>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
	// list 라는 키값으로 request scope 에 ArrayList 객체 담아두기
	request.setAttribute("list",names);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/test01.jsp</title>
</head>
<body>
	<h1>친구 목록</h1>
	<%
		// request 영역에 "list" 라는 키값으로 담긴 친구목록을 얻어와서 원래 type 으로 casting
		List<String> list = (List<String>)request.getAttribute("list"); // 오브젝트 타입이 리턴된다.
	%>
	<ul>
		<%for(String tmp:list){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
	<%-- server 에서 완성된 html 형식을 받아서 출력된다. (JSP의 기본)--%>
	<%-- 그러나 이 방법은 자바 코드를 중간중간 입력해야 한다. html 마크업 언어와 어울리지 않는다. 알아보기 힘들다. --%>
	
	<h1>친구 목록 EL+JSTL</h1>
	<ul>
		<c:forEach var="tmp" items="${requestScope.list}">
			<li>${tmp}</li>
		</c:forEach>
	</ul>
	 
	 <h1>친구 목록 인덱스 표시</h1>
	 <ul>
	 	<c:forEach var="tmp" items="${list}" varStatus="status">
	 		<li>${tmp } <strong>인덱스: ${status.index }</strong></li>
	 	</c:forEach>
	 </ul>
	 
	 <h1>친구 목록 순서 표시</h1>
	 <ul>
	 	<c:forEach var="tmp" items="${list}" varStatus="status">
	 		<li>${tmp } <strong>인덱스: ${status.count }</strong></li>
	 	</c:forEach>
	 </ul>
	 
	 <h1>친구 목록 첫번째 순서인지 여부</h1>
	 <ul>
	 	<c:forEach var="tmp" items="${list}" varStatus="status">
	 		<li>${tmp } <strong>인덱스: ${status.first }</strong></li>
	 	</c:forEach>
	 </ul>
	 
	 <h1>친구 목록 마지막 번째 순서인지 여부</h1>
	 <ul>
	 	<c:forEach var="tmp" items="${list}" varStatus="status">
	 		<li>${tmp } <strong>인덱스: ${status.last }</strong></li>
	 	</c:forEach>
	 </ul>
	 
	 
	 
</body>
</html>