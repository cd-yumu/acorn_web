<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 폼</title>
</head>
<body>
	<%-- 
		[파일 업로드 폼 구성 방법]
		
		method = "post"
		entype = "multipart/form-data"
		<input type="file">
	 --%>
	 
	 <div class="container">
	 	<h3>파일 업로드 폼</h3>
	 	<form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
	 		<input type="text" name="title" placeholder="설명 입력..." />
	 		<br />
	 		<input type="file" name="myFile" />
	 		<br />
	 		<button type="submit">업로드</button>
	 	</form>
	 </div>
</body>
</html>