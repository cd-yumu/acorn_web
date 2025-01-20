<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/my_hello.jsp</title>
</head>
<body>
	<c:forEach var="i" begin="0" end="4"> 
		<p>안녕하세요!!! ${i}</p>
	</c:forEach>
</body>
</html>