<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지 3</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>회원 가입 폼 입니다.</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id" />
				<small class="form-text">영문자 소문자로 시작하고 5-10 글자 이내로 입력하세요.</small>
				<div class="valid-feedback">가능한 아이디 입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
				<div></div>
			</div>
			
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="pwd2"/>
			</div>
			
			<button class="btn btn-success" type="submit" disabled="disabled">가입</button>
		</form>
	</div>
	<script>
			
		//// 아이디 확인
		document.
		
		
		//// 비밀번호 확인
		
		
		
		//// 이메일 확인
		
	</script>
</body>
</html>