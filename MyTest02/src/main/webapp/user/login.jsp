<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");

	session.setAttribute("id", id);
	
	
	String cPath = request.getContextPath();
	response.sendRedirect(cPath + "/index.jsp");
%>