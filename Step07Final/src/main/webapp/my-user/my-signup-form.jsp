<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/my-user/my-signup-form.jsp</title>
<style>
	.container{
	border: 1px dotted red; /* 경계선 확인용 */
	max-width: 700px;
	margin: 0 auto;
	}
</style>
</head>
<body>
	
	<div class="container">
	
		<h1>회원 가입</h1>
		
		<form action="my-signup.jsp" method="post" id="signupform" novalidate>
		
			<table>
				<tr>
					<th><label for="name">이름</label></th>
					<td>
						<input type="text" id="name" name="name"/>
					</td>
				</tr>
				
				<tr>
					<th><label for="userName">아이디</label></th>
					<td>
						<input type="text" id="id" name="id"/>
						<button type="button">중복확인</button>
						<small>영문자 소문자로 시작하고 5 - 10 글자여야 합니다.</small>
					</td>
				</tr>
				
				<tr>
					<th><label for="pwd">비밀번호</label></th>
					<td>
						<input type="password" id="pwd" name="pwd" />
						<small>특수문자가 포함된 숫자 또는 영문자로 5 - 10 글자여야 합니다. </small>
					</td>
				</tr>
				
				<tr>
					<th><label for="pwd2">비밀번호 확인</label></th>
					<td>
						<input type="password" id="pwd2" name="pwd2" />
					</td>
				</tr>
				
				<tr>
					<th><label for="email">이메일</label></th>
					<td>
						<input type="text" id="email" name="email"/>
						<small>이메일 형식으로 입력해야 합니다.</small>
					</td>
				</tr>
			</table>
		
			
			<button type="submit">제출</button>
		
		</form>
		
	</div>
	
</body>
</html>