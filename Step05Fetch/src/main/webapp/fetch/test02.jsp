<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test02 페이지</title>
</head>
<body>
	<input type="text" id="msg" placeholder="메시지 입력..." />
	<button id="sendBtn">전송</button>
	<button id="sendBtn2">전송2</button>
	
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		// id 가 sendBtn2 인 곳에 "click" 이벤트가 발생하면 호출될 함수 등록
		$("#sendBtn2").on("click",()=>{
			// id 가 msg 인 곳의 value 값을 읽어와서 msg 라는 상수에 담기
			const msg = $("#msg").val();
			fetch("send2.jsp?msg="+msg)
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
			})
			.catch(error=>{
				console.log(error);
			})
		});
	
		document.querySelector("#sendBtn").addEventListener("click", ()=>{
			//입력한 문자열 읽어오기
			const msg=document.querySelector("#msg").value;
			//fetch 함수를 호출하면서 GET 방식 파라미터로 send.jsp 페이지를 요청하면서 전달한다.
			fetch("send.jsp?msg="+msg)
			.then(res=>res.text())
			.then(data=>{
				console.log(data);
			});
		});
		
		
	</script>
</body>
</html>