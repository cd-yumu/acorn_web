<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 단독 업로드 폼</title>
<style>
	#image{
		display:none;
	}
	
	#profileImage{
		width: 200px;
		height: 200px;
		border: 1px soild #cecece;
		border-radius: 50%;
	}
	
</style>
</head>
<body>
	<div class="container">
		<h3>이미지 단독 업로드 테스트</h3>
		<a href="javascript:" id="profileLink" >
			<svg xmlns="http://www.w3.org/2000/svg" width="200" height="200" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
				<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			</svg>
		</a>
		<br />
		<input type="file" id="image" accept="image/*" />
	</div>
	<script>
		// 링크를 클릭했을 때
		document.querySelector("#profileLink").addEventListener("click",()=>{			
			// input type = "file" 요소를 강제 클릭해서 파일 선택 창을 띄운다.
			document.querySelector("#image").click();
		})
		
		// 새로운 이미지를 선택했을 때 input 요소에는 change 이벤트가 발생한다.
		document.querySelector ("#image").addEventListener("change",(event)=>{
			// 여기서 event.target 은 input type="file" 요소 이다.
			// 선택된 파일 데이터
			const fileData = event.target.files[0]; // 0번 방에 파일 데이터가 담겨있다.
			// FormData 객체에 myImage 라는 키값으로 파일 데이터 담기
			const data = new FormData();
			data.append("myImage",fileData);
			// fetch() 함수를 이용해서 업로드 하고 응답받은 데이터를 이용해서 이미지 출력하기
			
			// 여기서부터는 다음 시간에
			fetch("${pageContext.request.contextPath}/file/upload4",{
				method: "post",
				body: data
			})
			.then(res=>res.json())
			.then(data=>{
				// data.saveFileName 은 업로드된 이미지의 저장파일명이다.
				console.log(data);
				
				// img 요소를 만들 문자열 구성하기
				//*Context Path => webapp 폴더다!
				const img = `<img id = "profileImage" src = "${pageContext.request.contextPath}/upload/\${data.saveFileName}">`;
				
				//const img = '<img src = "${pageContext.request.contextPath}/upload/\${data.saveFileName}">';
				// 이건 안 됨
				
				/*
					<img src = "${pageContext.request.contextPath}/upload/${data.saveFileName}"> 이렇게 써버리면
					${data.saveFileName} 이 함수는 웹 브라우저가 해석을 해야 하는 부분이다.
					즉, 서버가 미리 해석하면 안 되는 부분이다.
					그래서 역슬래시를 붙여서 서버가 해석하지 못하도록 한다.
				*/
				
				document.querySelector("#profileLink").innerHTML = img;
				
				
				/* 내 시도 
				const requestPath = "${pageContext.request.contextPath}/upload/" + data.saveFileName;
				document.querySelector("#profileLink").innerText = "";
				const src = document.createElement("img");
				src.setAttribute("src",requestPath);
				src.setAttribute("width","200");
				src.setAttribute("height","200");
				document.querySelector("#profileLink").append(src); */
			})
		});
		 
	</script>
</body>
</html>