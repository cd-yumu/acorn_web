<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 이미지를 바로 보여주기</title>
</head>
<body>
	<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="${pageContext.request.contextPath}/file/upload3" method="post" 
			enctype="multipart/form-data" id="myForm">
			<input type="text" name="title" placeholder="설명 입력" /><br /> 
			이미지  
			<input type="file" name="myImage" accept="image/*" /><br />
			<button type="submit">업로드</button>
		</form>
		<img id="image" width="300" />
	</div>
	<script>
		document.querySelector("#myForm").addEventListener("submit",(event)=>{
			/* 여기서 event 는 발생한 이벤트에 대한 정보를 담고 있는 object 가 매개변수에 전달된다. */
			// 기본 동작(폼제출)
			event.preventDefault();
			// event.target => 해당 이벤트가 발생한 바로 그 요소의 참조값 (form의 참조값)
			/* submit 이벤트는 바로 form 요소에서 발생했기 때문에 여기서는 form 을 가르킨다. 
				document.querySelect("#myForm") === event.target
			*/
			const data = new FormData(event.target); // 폼의 입력 및 선택된 정보가 담긴다.
			
			// fetch 함수를 이용해서 FormData 전송하기
			fetch("${pageContext.request.contextPath}/file/upload3",{
				 // 경로 다음에 요청에 대한 옵션을 오브젝트에 담아 전달한다.
				method:"post",
				body:data // 만든 폼 객체를 body 에 실어서 보낸다.
			})
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				// data.saveFileName 은 upload 폴더에 저장된 파일명이다.
				const requestPath = "${pageContext.request.contextPath}/upload" + data.saveFileName;
				// image 요소에 src 속성 추가하기
				document.querySelector("#image").setAttribute("src", requestPath);
			});
		});
	</script>
</body>
</html>