<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 요청한 파라미터 읽어오기
	String msg = request.getParameter("msg");
	// 읽어온 내용 콘솔에 테스트로 출력하기
	System.out.println(msg);
	//json 문자열 응답
%>
{"fromServer":"메시지 잘 받았어 클라이언트야!!!"}