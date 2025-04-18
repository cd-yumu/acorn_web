<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 결과</title>
</head>
<body>
	<div class="container">
		<h1>업로드 결과 페이지</h1>
		<p>파일을 업로드 했습니다.</p>
		<p>title: <strong>${title }</strong></p>
		<%-- requestScope.title 인데 el 을 쓰면 생략할 수 있다. --%>
		<p>orgFileName: <strong>${orgFileName }</strong></p>
		<p>saveFileName: <strong>${saveFileName }</strong></p>
		<p>fileSize: <strong>${fileSize }</strong> byte</p>
		<p>uploadPath: <strong>${uploadPath }</strong></p>
		<p>
			<a href="${pageContext.request.contextPath }/file/download?orgFileName=${orgFileName }&saveFileName=${saveFileName}&fileSize=${fileSize}">다운로드</a>
		</p>
	</div>
</body>
</html>