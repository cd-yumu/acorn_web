<%@page import="com.google.gson.Gson"%>
<%@page import="test.post.dto.CommentDto"%>
<%@page import="test.post.dao.CommentDao"%>
<%@page import="test.user.dto.SessionDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 세션에 저장된 user name 을 얻는다.
	SessionDto sessionDto =  (SessionDto)session.getAttribute("sessionDto");
	String writer = sessionDto.getUserName();
	
	// fetch() 를 이용해서 전송되는 정보를 추출한다. - 댓글의 번호, 부모 번호, 부모 작성자, 내용
	long postNum = Long.parseLong(request.getParameter("postNum"));	//댓글 번호(원글의 글번호)
	String targetWriter = request.getParameter("targetWriter");		//원글의 작성자
	String content = request.getParameter("content");				//댓글 내용
	// 원글의 댓글일 경우 parentNum은 넘어오지 않아 null 이다.
	String strPatrNum = request.getParameter("parentNum");			//댓글의 부모 번호
	long parentNum = 0;
	
	// 댓글 정보를 DB 에서 추출할 수 있는 객체 생성
	CommentDao dao = CommentDao.getInstance();
	// 저장할 댓글의 글번호를 얻는다.
	Long num = dao.getSequence();
	
	// 만일 parentNum 이 넘어오지 않으면
	if(strPatrNum == null){
		parentNum = num; // 댓글의 글번호가 parentNum 이 된다. 새로 얻은 댓글번호
	// 넘어온다면 넘어오는 값을 parentNum 으로 설정한다.
	} else parentNum = Long.parseLong(strPatrNum); // 기존의 댓글번호
	
	// 저장할 댓글 정보
	CommentDto dto = new CommentDto();
	dto.setNum(num);						// 위에서 구한 댓글의 글번호
	dto.setWriter(writer);					// 세션에서 구한 댓글 작성자
	dto.setPostNum(postNum);				// fetch 에서 넘어온 파라미터 추출해 얻은 원글번호
	dto.setTargetWriter(targetWriter);		// fetch 에서 넘어온 파라미터 추출해 얻은 원글작성자
	dto.setContent(content);				// fetch 에서 넘어온 파라미터 추출해 얻은 댓글내용
	dto.setParentNum(parentNum);			// 위에서 구한 댓글의 부모번호
	
	boolean isSuccess = dao.insert(dto);
	if(!isSuccess){
		response.sendError(500, "댓글 추가 실패!");
		return;
	}
	
	// DB 에 저장된 정보를 다시 읽어오기
	dto = dao.getData(num);
	//Gson 객체를 이용해서 CommentDto 에 저장된 정보를  json 문자열로 변환해서 응답한다.
	Gson gson=new Gson();
%>
<%=gson.toJson(dto) %>