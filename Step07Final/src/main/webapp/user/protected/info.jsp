<%@page import="test.user.dto.SessionDto"%>
<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
	// session 영역에서 SessionDto 객체를 얻어낸다.
	SessionDto sessionDto = (SessionDto)session.getAttribute("sessionDto");
	
	// 테스트용 출력
	System.out.println(session.getAttribute("sessionDto")); // test.user.dto.SessionDto@2897299
	// 세션 영역에서 해당 키값으로 찾아낸 결과는 SessionDto 객체가 아니다.
	// 때문에 SessionDto 타입으로 타입 캐스팅을 해야 한다.
	
	
	// UserDto 정보 얻어내기
	UserDto dto = UserDao.getInstance().getData(sessionDto.getNum());
	
	// el 에서 dto 를 사용가능하게 request 영역에 담는다.
	request.setAttribute("dto", dto);
	
	// 아래에서 가입 정보를 응답한다.
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/info.jsp</title>
<style>
	#profileImage{
		width:100px;
		height:100px;
		border:1px solid #cecece;
		border-radius:50%;
	}
</style>
</head>
<body>
	<div class="container">
		<a href="update-form.jsp">개인정보 수정</a>
	
		<h1>가입 정보입니다.</h1>
		<table>
			<tr>
				<th>아이디</th>
				<td>${dto.userName }</td> <%-- EL --%>
				<%-- el 사용하지 않는다면 <td><%=dto.getUserName() %></td> 이렇게 코딩할 수 있지만
					el 을 사용하여 마크업 친화적으로 코딩하는 것이 좋다. --%>
			</tr>
			<tr>
				<th>비밀번호</th><%-- 비밀번호를 바로 보여주지 않고 수정하는 페이지를 따로 만들었다. --%>
				<td>
					<a href="pwd-update-form.jsp">수정하기</a>
				</td>
			</tr>
			<tr>
				<th>프로필 이미지</th>
				<td>
					<c:choose> <%-- JSTL --%>
						<%-- 만약 UserDto 객체에 프로필 이미지 데이터가 없다면 --%>
						<c:when test="${empty dto.profileImage }">
							<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
								<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
								<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
							</svg>
						</c:when>
						<c:otherwise>
							<img id="profileImage" src="${pageContext.request.contextPath}/upload/${dto.profileImage}" alt="프로필 이미지" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.email }</td>
			</tr>
			<tr>
				<th>가입일</th>
				<td>${dto.createdAt }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${dto.updatedAt }</td>
			</tr>
		</table>
	</div>
</body>
</html>			