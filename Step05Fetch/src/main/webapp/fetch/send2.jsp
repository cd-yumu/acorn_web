<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- /fetch/send2.jsp 페이지의 내용 --%>
<%
	// GET 방식 요청 파라미터 읽어오기
	String msg = request.getParameter("msg");
	System.out.println("msg: " + msg);
%>
{
	"isSuccess" : true
	"message" : "메시지 잘 받았어 클라이언트야"
}