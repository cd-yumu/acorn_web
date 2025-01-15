<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
	.invalid-feedback {
		display: none;
		color: red;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>회원 가입 폼 입니다.</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" />
				<small>영문자 소문자로 시작하고 5-10 글자 이내로 입력하세요.</small>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
				<div></div>
			</div>
			<button type="submit">가입</button>
		</form>
	</div>
	<script>
		/*
			폼에 submit 이벤트가 발생하면 입력한 내용을 검증해서
			조건을 만족하지 못하면 폼 제출을 막는 예제
		*/
		// 아이디를 검증할 정규 표현식
		const reg_id = /^[a-z].{4,9}$/;
		
		//button[type=submit] == #singupForm button(?)
		// 폼 안에 있는 submit 버튼을 누르면 form 이 제출되는 기본 동작을 한다.
		document.querySelector("button[type=submit]").addEventListener("click",(event)=>{
			// 입력한 id 를 읽어온다.
			const inputId = document.querySelector("#id").value;
			
			// 만일 아이디를 검증하는 정규표현식을 통과하지 않았다면
			if(!reg_id.test(inputId)){
				// 폼 제출 막기
				event.preventDefault();
				// .invalid-feedback 인 div 를 보이게 해보세요(display:bock)
				document.querySelector(".invalid-feedback").style.display = "block";
				
			}
		});
		
		
		
		/* document.querySelector("button").addEventListener("click",(event)=>{
			// 자동 페이지 이동 일단 막고
			event.preventDefault();
			// 정규 표현식 객체 생성
			const reg = new RegExp(reg_id);
			// 사용자 입력값을 가져와
			const id = document.querySelector("#id").value;
			// 정규 표현식에 맞는지 확인
			const result = reg.test(id);
			
			// 만약 정규 표현식에 맞다면
			if(result){
				const data = new FormData(e.target);
				
				fetch("${pageContext.request.contextPath}/user/signup.jsp",{
					method:"post",
					body:data
				})
				.then(res=>res.json())
				.then(data=>{});
			}
		}); */
		
		
	</script>
</body>
</html>