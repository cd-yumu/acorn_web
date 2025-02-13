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
		let totalPageCount=0;
		let currentPage=1;
		
		document.querySelector("#moreBtn").addEventListener("click",()=>{
			if(currentPage >= totalPageCount){
				alert("댓글의 마지막 페이지 입니다.");
			}
			// 댓글 페이지 번호를 1 증가시키고
			currentPage++;
			// 해당 페이지의 정보를 요청해서 받아온다.
			fetch(`comment-list.jsp?pageNum=\${currentPage}&postNum=${dto.num}`)
			.then(res=>res.json())
			.then()
		});
	</script>
</body>
</html>