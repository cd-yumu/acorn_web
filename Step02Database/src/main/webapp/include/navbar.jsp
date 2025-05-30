<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// navbar.jsp 페이지가 어떤 페이지에 include 되었는지 정보 읽어오기
	String currentPage = request.getParameter("current");
%>
	<!-- /include/navbar.jsp 의 내용 -->
	<nav class="navbar navbar-expand-md bg-primary" data-bs-theme="dark">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Acorn</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link <%=currentPage.equals("member")?"active":""%>" href="${pageContext.request.contextPath}/member/list.jsp">Member</a>
					</li>
					<li class="nav-item">
						<a class="nav-link <%= currentPage.equals("food")?"active":"" %>" href="${pageContext.request.contextPath}/food/list.jsp">Food</a>
					</li>
					<li class="nav-item">
						<a class="nav-link <%= currentPage.equals("food")?"active":"" %>" href="${pageContext.request.contextPath}/guest/list.jsp">Guest</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>