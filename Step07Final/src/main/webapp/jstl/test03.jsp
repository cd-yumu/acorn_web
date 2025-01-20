<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/test03.jsp</title>
</head>
<body>
	<c:if test="true">
		<p>이 문장이 출력될까? 어떤 문장이 무조건 출력이 아닌 출력 될지 말지 결정할 수 있다.</p>
	</c:if>
	<c:if test="false">
		<p>요건 출력이 안될껄?</p>
	</c:if>
</body>
</html>