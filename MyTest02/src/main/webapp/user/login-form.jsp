<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<h1>Login Form</h1>
	<form action="login.jsp" method="post" id="loginForm">
		<label for="id">ID</label>
		<input type="text" name="id" id="id"/>
		<label for="pwd">PWD</label>
		<input type="text" name="pwd" id="pwd"/>
		<button>Login</button>
	</form>
</body>
</html>