<%@page import="dto.PostDto"%>
<%@page import="dao.PostDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int postNum = Integer.parseInt(request.getParameter("postNum"));
	PostDto dto = PostDao.getInstance().getData(postNum);
	request.setAttribute("dto", dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post - ${dto.title}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>


</style>
</head>
<body>
	<div class="container">
		<table class="table table-bordered">
			<tr>
				<th>Title</th>
				<td>${dto.title}</td>
			</tr>
			<tr>
				<th>Writer</th>
				<td>${dto.writer}</td>
			</tr>
			<tr>
				<th>View Count</th>
				<td>${dto.viewCount}</td>
			</tr>
			<tr class="mh-100"  style="height: 200px;">
				<td colspan="2">
					<div>${dto.content}</div>
				</td>
			</tr>
			<tr>
				<th>Created Date</th>
				<td>${dto.createdAt}</td>
			</tr>
			<tr>
				<th>Updated Date</th>
				<td>${dto.updatedAt}</td>
			</tr>
		</table>
		
		<a href="${pageContext.request.contextPath}/post/list.jsp">Back</a>
		<a href="">Update</a>
		<a href="">Delete</a> 
		
		<!-- 댓글 -->
		<h4>Comments</h4>
		<!-- 댓글 작성 폼 -->
		<form action="${pageContext.request.contextPath}/post/comment-insert.jsp" 
			method="post" style="display: flex;">
			<input type="hidden" name="postNum" value="${dto.num}"/>
			<input type="hidden" name="postWriter" value="${dto.writer}"/>
			<textarea name="content" id="" style="height:80px; width:90%;"></textarea>
			<button style="width:10%;">Add</button>
		</form>
		<!-- 댓글 목록 -->
		<div class="comments">
			<ul>
			
			</ul>
		</div>
	</div>
	
	<script>
		function refreshComments(){
			// 출력된 내용을 모두 지우기
			document.querySelector(".comments ul").innerHTML = "";
			
			// 댓글 데이터 요청
			fetch("${pageContext.request.contextPath}/post/comment-list.jsp?postNum=${dto.num}")
			.then(res=>res.json())
			.then(data=>{
				console.log("result fetch to json: " + data);
				
				data.forEach(comment=>{
					console.log("comment.num: " + comment.num);
					console.log("comment.parentNum: " + comment.parentNum);
					console.log(comment.num != comment.parentNum);
					
					// li 요소 생성
					const li = document.createElement("li");
					
					
					// 그것이 댓댓글이면 들여쓰기
					li.classList.add(comment.num !== comment.parentNum ? "indent" : "not-indent");
					
					// 그것이 삭제된 댓글이면 li 요소에 삭제된 댓글의 요소 추가
					if(comment.deleted == "yes"){
						li.innerHTML = `
							<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
				  				<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
							</svg>
							<pre>삭제된 댓글입니다.</pre>
						`;
					// 아니면 li 요소에 댓글 내용 추가
					} else { 
						li.innerHTML = `
							<!-- 댓글 아이콘 -->
							<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
				  				<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
							</svg>
							<!-- 댓글 -->
							<dl>
								<!-- 댓글 상단: 댓글작성자 / 답글, 수정, 삭제 버튼 -->
								<dt>
									<span>
										\${comment.writer}
										<!--  -->
										\${comment.num != comment.parentNum ? '@' + comment.targetWriter : ''}
									</span>
									<small>\${comment.createdAt}</small>
									<!-- 댓글 수정했을 경우 -->
									<small>\${comment.createdAt != comment.updatedAt ? '| Updated At '+ comment.updatedAt : ''}</small>
									<a href="javascript:">Add Comment</a>
								</dt>
								<!-- 댓글 본문 -->
								<dd>
									<pre>\${comment.content}</pre>
								</dd>
							</dl>
						`;
					}
					
					// ul 요소에 li 추가
					document.querySelector(".comments ul").append(li);
					
				});
			});
		}
		
		refreshComments();
		
	</script>
</body>
</html>