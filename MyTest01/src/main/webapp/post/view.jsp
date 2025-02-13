<%@page import="test.post.dao.PostDao"%>
<%@page import="test.post.dto.PostDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//검색조건이 있는지 읽어와 본다.
	String condition=request.getParameter("condition");
	String keyword=request.getParameter("keyword");
	String findQuery=null;
	
	//있다면 dto 에 해당 정보를 담는다.
	PostDto findDto = new PostDto();
	if(condition != null){
		//findDto=new PostDto();
		findDto.setCondition(condition);
		findDto.setKeyword(keyword);
		// 글 목록으로 돌아갈 때 검색 조건 그대로 돌아가기 위함
		findQuery="&condition="+condition+"&keyword="+keyword; 
	}
	
	//자세히 보여줄 글의 번호를 읽어온다. 
	int num=Integer.parseInt(request.getParameter("num"));
	findDto.setNum(num);	// 역시 DTO 에 담는다.
	
	//DB 에서 해당 글의 정보를 얻어와서 
	PostDto dto=PostDao.getInstance().getData(findDto);
	
	//세션 아이디를 읽어와서 
	String sessionId = session.getId(); // 컴퓨터마다 고유의 세션 아이디가 존재
	//이미 읽었는지 여부를 얻어낸다 
	boolean isReaded = PostDao.getInstance().isReaded(num, sessionId);
	if(!isReaded){
		//글 조회수도 1 증가 시킨다
		PostDao.getInstance().addViewCount(num);
		//이미 읽었다고 표시한다. 
		PostDao.getInstance().insertReaded(num, sessionId);
	}
	
	request.setAttribute("dto", dto);
	request.setAttribute("findDto", findDto);
	request.setAttribute("findQuery", findQuery);
	//응답한다 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 자세히 보기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<style>
	.comment-form, .re-insert-form, .update-form{
		display: flex;
	}
	
	.comment-form textarea, .re-insert-form textarea, .update-form textarea{
		height: 100px;
		flex-grow: 1;
	}
	
	.comment-form button, .re-insert-form button, .update-form button{
		flex-basis: 100px;
	}
</style>
<body>
	<div class="container">
		<c:if test="${dto.prevNum ne 0} }">
			<a href="view.jsp?num=${dto.prevNum}${findQuery}">이전 글</a>
		</c:if>
		<c:if test="${dto.nextNum ne 0} }">
			<a href="view.jsp?num=${dto.nextNum}${findQuery}">이전 글</a>
		</c:if>
		<c:if test="${not empty findDto.condition}">
			<p>
				<strong>${findDto.condition }</strong> 조건
				<strong>${findDto.keyword }</strong>검색어로 검색된 내용 자세히보기
			</p>
		</c:if>
		<h3>글 상세 보기</h3>
		<table class="table table-bordered">
			<tr>
				<th>글번호</th>
				<td>${dto.num }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.viewCount }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.createdAt }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${dto.updatedAt }</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="contents">${dto.content }</div>
				</td>
			</tr>
		</table>
		
		<%-- 만일 글 작성자가 로그인된 아이디와 같다면 수정, 삭제 링크를 제공한다. --%>
		<c:if test="${dto.writer eq sessionDto.userName }">
			<a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath }/post/protected/edit.jsp?num=${dto.num }">수정</a>
			<a class="btn btn-outline-danger btn-sm" href="javascript:" onclick="deleteConfirm()">삭제</a>
			<script>
				function deleteConfirm(){
					const isDelete=confirm("이 글을 삭제 하겠습니까?");
					if(isDelete){
						//javascript 를 이용해서 페이지 이동 시키기
						location.href="${pageContext.request.contextPath }/post/protected/delete.jsp?num=${dto.num}";
					}
				}
			</script>
		</c:if>
		
		
		<h4>댓글을 입력해 주세요</h4>
		<!-- 원글에 댓글을 작성할 폼 -->	
		<form class="comment-form" action="protected/commet-insert.jsp" method="post">
			<!-- 원글의 글번호가 댓글의 postNum 이 된다. -->
			<input type="hidden" name="postNum" value="${dto.num}"/>
			<!-- 원글의 작성자가 댓글의 대상자가 된다. -->
			<input type="hidden" name="targetWriter" value="${dto.writer}" />
			<textarea name="content">${empty sessionDto ? '댓글 작성을 위해 로그인이 필요합니다' : ''}</textarea>
			<button type="submit">등록</button>
		</form>
		
		<!-- 댓글 목록 -->
		<div class="comments">
			<ul></ul>
			<div class="more">
				<button id="moreBtn">댓글 더보기</button>
			</div>
		</div>
	</div>
	
	<script>
		// 로그인된 사용자의 이름
		const userName = "${sessionDto.userName}";
	
		let totalPageCount=0;
		let currentPage=1;
		
		// 댓글 더보기 버튼을 눌렀을 때
		document.querySelector("#moreBtn").addEventListener("click",()=>{
			if(currentPage >= totalPageCount){
				alert("댓글의 마지막 페이지 입니다.");
			}
			// 댓글 페이지 번호를 1 증가시키고
			currentPage++;
			// 해당 페이지의 정보를 요청해서 받아온다.
			fetch(`comment-list.jsp?pageNum=\${currentPage}&postNum=${dto.num}`)
			.then(res=>res.json())
			.then(commentData=>{ // 댓글정보가담긴DTO가담긴 List 와 전체 댓글 수가 응답된다.
				// 전체 페이지 개수
				totalPageCount = commentData.totalPageCount;
				// 댓글 목록에 있는 댓글 정보를 하나 하나 참조하면서
				commentData.list.forEach(item=>{
					// 댓글 하나의 정보를 makeList 함수에 전달해 댓글 정보가 출력된 li 요소를 만들어낸다.
					const li = makeList(item);
					// 얻어낸 li 요소를 ul에 추가한다.
					document.querySelector(".comments ul").append(li);
				});
			})
		});
		
		
		function refreshComments(){
			// 출력된 내용을 모두 지우고 (댓글 목록의 div : comments)
			document.querySelector(".comments ul").innerHTML = "";
			
			// 댓글 1page 내용을 fetch()를 이용해서 받아온다
			fetch("comment-list.jsp?pageNum=1&postNum=${dto.num}")
			.then(res=>res.json())
			.then(commentData=>{
				// 전체 페이지 개수
				totalPageCount = commentData.totalPageCount;
				// 댓글 목록에 있는 댓글 정보를 하나 하나 참조하면서
				commentData.list.forEach(item=>{
					// 댓글 하나의 정보를 makeList 함수에 전달해 댓글 정보가 출력된 li 요소를 만들어낸다.
					const li = makeList(item);
					// 얻어낸 li 요소를 ul에 추가한다.
					document.querySelector(".comments ul").append(li);
				});
			})
			
		}
		
		// 페이지가 로드될 때 실행된다.
		refreshComments();
		
		function makeList(comment){ // dto 객체가 인자로 넘어온다.
			// li 요소를 만들어서
			const li = document.createElement("li");
			// 댓댓글이면 들여쓰기, 그냥 댓글이면 들여쓰기 안 함 - 만든 li 요소의 class 속성에 넣을지 말지
			li.classList.add(comment.num !== comment.pareNum ? "indent" : "indent")
			
			// 만약 삭제된 댓글이라면
			if(comment.deleted == "yes"){
				li.innerHTML = `
					<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
	  					<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
					</svg>
					<pre>삭제된 댓글입니다</pre>
				`;
				// 삭제된 댓글입니다가 출력된 li 를 바로 리턴해주고 끝
				return li;
			}
			
			// 프로필 이미지 처리
			const profileImage = comment.profileImage 
				? `<img class="profile-image" src="${pageContext.request.contextPath }/upload/\${comment.profileImage}" alt="Profile Image">`
				: `<svg class="profile-image default-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                </svg>`; 
                // 넘어온 인자 dto 에 profileImage 값이 있으면 그 이미지를 쓰고 아니면 기본 이미지 사용
                
			// 수정 삭제 링크 처리
			const link = userName == comment.writer
				? `
            		<a class="update-link" href="javascript:">수정</a>
					<a class="delete-link" href="javascript:">삭제</a>
            	` 
            	: "";
			
			li.innerHTML=`
				<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
	  				<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
				</svg>
				<dl>
					<dt class="comment-header">
					    <!-- 프로필 이미지 -->
					    <div class="comment-profile">
					        \${profileImage}
					        <div class="comment-meta">
					            <span class="comment-writer">
					            	\${comment.writer}
					            	\${comment.num != comment.parentNum ? '@' + comment.targetWriter : ''}
					            </span>
					            <small class="comment-date">\${comment.createdAt}</small>
				        	</div>
					    </div>
					
					    <!-- 답글, 수정, 삭제 버튼 -->
					    <div class="comment-actions">
					        <a class="reply-link" href="javascript:">답글</a>
					        \${userName == comment.writer ? `
					            <a class="update-link" href="javascript:">수정</a>
					            <a class="delete-link" href="javascript:">삭제</a>
					        ` : ''}
					    </div>
					</dt>

					<dd>
						<pre>\${comment.content}</pre>
					</dd>
				</dl>
				<!-- 댓글의 댓글 작성할 폼 미리 출력하기 -->
				<form class="re-insert-form"  method="post">
					<input type="hidden" name="postNum" value="${dto.num }"/>
					<input type="hidden" name="targetWriter" value="\${comment.writer }"/>
					<input type="hidden" name="parentNum" value="\${comment.parentNum }"/>
					<textarea name="content"></textarea>
					<button type="submit">등록</button>
				</form>
				<!-- 댓글 수정폼 -->
				<form  class="update-form"  method="post">
					<input type="hidden" name="num" value="\${comment.num}"/>
					<textarea name="content">\${comment.content}</textarea>
					<button type="submit">수정확인</button>
				</form>	
			`;
			
			// li 요소에 delete-link class 가 있다면 그 요소에 이벤트 리스너 달기
			li.querySelector(".delete-link") && li.querySelector(".delete-link").addEventListener("click", (e)=>{
				// .delete-link 를 누르면 삭제 확인 알림창 띄우기
				const isDelete = confirm("댓글을 삭제하시겠습니까?");
				// 응답이 긍정일 때
				if(isDelete){
					// 댓글 삭제 기능을 수행하는 jsp 페이지로 댓글번호와 함께 요청보내기
					fetch("protected/comment-delete.jsp?num=" + comment.num)
					.then(res=>res.json())
					.then(data=>{
						if(data.isSuccess){
							// 응답된 값이 성공이면 댓글이 있던자리에 "삭제된 댓글입니다."를 출력
							li.innerHTML = `
								<svg style="\${comment.num != comment.parentNum ? 'display:inline' : ''}"  class="reply-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
				  				<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
								</svg>
								<pre>삭제된 댓글입니다.</pre>
							`;
						}
					})
				}
			});
			
			// 수정 버튼을 눌렀을 때
			li.querySelector(".update-form").addEventListener("submit",(e)=>{
				// 폼 제출 이벤트를 막기
				e.preventDefault();
				
				// submit 이벤트가 일어난 form 의 참조값을 form 이라는 변수에 담기
				const form = e.target;
				
				// 폼에 입력된 전송할 내용을 query 문자열 형식으로 얻어내기
				const queryString = new URLSearchForm(new Formdata(form)).toString();
				
			});
			
			
			return li;
			
		}
		
	</script>
</body>
</html>