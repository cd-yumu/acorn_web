<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button id="getBtn">회원 정보 가져오기</button>
	<p>번호: <strong id="num"></strong></p>
	<p>이름: <strong id="name"></strong></p>
	<p>주소: <strong id="addr"></strong></p>
	
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		console.log("1")	
	
		$("#getBtn").on("click",()=>{
			
			console.log("3")
			
			fetch("member.jsp")
			.then(res=>res.json())
			.then(data=>{
				console.log("5")
				
				$("#num").text(data.num);
				$("#name").text(data.name);
				$("#addr").text(data.addr);
			})
			.catch(error=>{
				console.log(error);
			})	
			
			console.log("4")
		});
		
		console.log("2")
		
	</script>
	
</body>
</html>