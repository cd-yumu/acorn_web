<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fetch 함수 테스트</title>
</head>
<body>
	<h3>fetch 함수 테스트</h3>
	<button id="myBtn">눌러보셈</button>
	<script>
		document.querySelector("#myBtn").addEventListener("click",()=>{
			// JavaScript 로 서버에 요청
			
			
			/*
				서버(jsp or servlet)에서 응답한 문자열이 json 형식이면
				return res.json();
			
				그 이외의 형식이면 html, xml, palin text 등등
				return res.text();
			*/
			fetch("${pageContext.request.contextPath}/index.jsp")
			.then(res=>res.text())
			.then(data=>{
				console.log(data);
			})
			/*
				위의 then() 함수에서 res.json() 을 리턴하면 data 는 응답된 json 문자열을 
				JSON.parse() 과정을 이미 거친 object 나 array 이다.
				
				위의 then() 함수에서 res.text() 를 리턴하면 data 는 서버가 응답한 문자열(string) 이다.
			*/
			
			/*
			fetch 함수는 주어진 경로로 요청을 한다.
			
			페이지 전환없이 javascript 로 요청하면 응답된 문자열이 
			두번째 then()에 전달한 함수의 매개변수에 전달된다.
			*/
			
			/*
			let a = fetch("${pageContext.request.contextPath}/index.jsp");
			a.then()
			*/
			
			/*
			let a = fetch("${pageContext.request.contextPath}/index.jsp");
			res=>res.text();
			data=>console.log(data);
			*/
			
			
			
			
			// 화살표 함수를 또 줄였다
			/*
			fetch("${pageContext.request.contextPath}/index.jsp")
			.then((res)=>
				return res.text();
			)
			.then(data=>{
				console.log(data);
			})
			*/
			
			/*
			fetch("${pageContext.request.contextPath}/index.jsp")
			.then(function(res){
				return res.text();
			})
			.then(data=>{
				console.log(data);
			})
			*/
			
		});
	</script>
</body>
</html>