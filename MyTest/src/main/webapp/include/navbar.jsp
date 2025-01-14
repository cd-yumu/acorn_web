<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//navbar.jsp 페이지가 어떤 페이지에 include 되었는지 정보 읽어오기
	String currentPage = request.getParameter("current");
%>


<nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
  <div class="container">
  
  	<%-- 로고 , 누르면 메인 인덱스 페이지로 가기 --%>
    <a class="navbar-brand" href="MyTest/">Mine</a>
    
    <%-- 화면 크기 작아지면 일반 네비바 버튼 사라지고 서랍 토글 생성 --%>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
		<span class="navbar-toggler-icon"></span>
	</button>
  
    <%-- 일반 네비바 버튼들 --%>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">하나</a></li>
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">두울</a></li>
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">세엣</a></li>
      </ul>
    </div>
    
    
  </div>
</nav>