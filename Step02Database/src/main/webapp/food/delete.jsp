<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 삭제할 번호를 읽어와 Dao 객체를 이용해 삭제한다.
	boolean isSuc = new FoodDao().delete(Integer.parseInt(request.getParameter("num")));

	// 응답하기
	// 특정 경로로 요청을 다시 하라는 리다일렉트 응답하기
	// list.jsp => delete.jsp => list.jsp 이런 이동이기 때문에 마치 새로 고침하는 듯한 느낌을 줄 수 있다.
	
	// Context path 는 HttpServletRequest 객체를 이용해서 얻어낸 다음 사용해야 한다.
	String cPath = request.getContextPath(); // 지금 시점에는 /Step02Database 가 읽어와진다.
	response.sendRedirect(cPath + "/food/list.jsp");
%>
2