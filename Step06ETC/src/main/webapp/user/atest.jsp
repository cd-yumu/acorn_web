<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>atest.jsp</title>
<!-- BootStrap CSS 로드 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="conainer">
		<h1>회원가입 폼</h1>
		<form action="signup.jsp" method="post" novalidate>
			<div class="id">
				<label for="id"></label>
				<input type="text" id="id" name="id" />
				<small>영문자로 시작하는 영문자 또는 숫자 6~20자</small>
				<div class="invalid-feedback" style="color:red; display:none">사용할 수 없는 아이디 입니다.</div>
<!-- 				<div class="valid-feedback"></div> -->
			</div>
			<button type="submit">가입</button>
		</form>
		
	</div>
	<script>
		// 유효성 유무
		let isValidID = false;
	
		// 아이디 체크 정규표현식
		const reg_id = /^[a-z]+[a-z0-9]{5,19}$/g;
		
		/* 
		예제1: 아이디가 입력되고 버튼을 눌렀을 때 정규표현식으로 확인한다.
		만약 유효하지 않으면 form 의 submit event X
		
		예제2: 아이디가 입력될 때마다 유효성 검사를 하고
		유효하거나 유효하지 않으면 바로 표시 및 버튼 활성, 비활성
		거기에 bootstrap css 사용하기
		*/
		
		
		/* // type 이 submit 인 요소에 input 이벤트가 발생 했을 때 
		document.querySelector("button[type=submit]").addEventListener("input",(e)=>{
			// 아이디 입력값 추출
			const id = document.querySelector("#id").value;
			
			// 아이디 유효성 검사
			boolean isValid = reg_id.test(id);
			
			// 만약 아이디가 유효하다면
			if(isValid){
				
			} else { // 만약 아이디가 유효하지 않다면
				
			}
		}); */
		
		/* // type 이 submit 인 요소에 click 이벤트가 발생 했을 때
		document.querySelector("button[type=submit]").addEventListener("click",(e)=>{
			// 아이디 입력값 추출
			const id = document.querySelector("#id").value;

			// 만약 유효하지 않은 아이디면
			if(!reg_id.test(id)){
				// 유효하지 않음을 표시하기
				document.querySelector(".invalid-feedback").style.display = "block";
				// submit 이벤트 막기
				e.preventDefault();
			} else {
				
			}
		}); */
	</script>
</body>
</html>