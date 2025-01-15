<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 목록 추가</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav style="--bs-breadcrumb-divider: '|||';">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/member/list.jsp">Member
						List</a></li>
				<li class="breadcrumb-item active">Add Member</li>
			</ol>
		</nav>

		<form action="insert.jsp">
			<div class="mb-2">
				<label class="form-label" for="type">타입: </label> <select
					class="form-control" name="type" id="type">
					<option selected>한식</option>
					<option>중식</option>
					<option>양식</option>
					<option>일식</option>
					<option>기타</option>
				</select>
			</div>
			<div class="mb-2">
				<label class="form-label" for="name">이름: </label> <input
					class="form-control" type="text" name="name" id="name"
					placeholder="음식 이름을 입력하세요..." />
			</div>
			<div class="mb-2">
				<label class="form-label" for="price">가격:</label> <input
					class="form-control" type="number" name="price" id="price"
					step="100" max="100000" min="1000" />
			</div>

			<button class="btn btn-outline-success btn-sm" type="submit">추가하기</button>
		</form>
	</div>


</body>
</html>