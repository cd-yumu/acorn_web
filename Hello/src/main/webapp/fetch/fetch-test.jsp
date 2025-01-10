<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>Fetch</h1>
		<h3>fetch 함수를 이용해 JavaScript 로 서버에 요청을 보낼 수 있습니다.</h3>
		<p>응답 형식이 jsㄹㄹㄹon 형식이면 res.json</p>
		<p>응답 형식이 html, xml, plain text 형식이면 res.text()</p>
		<script>
		
			fetch("${pageContext.request.contextPath}/fetch/sub.jsp")
			.then(res=>res.text())
			.then(data=>{
				console.log(data);
			})
		
		</script>
		
	</div>
</body>
</html>