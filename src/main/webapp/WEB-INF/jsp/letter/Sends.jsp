<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!-- 
회원 목록
-->
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>받은 편지 목록</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<p>전체 ${totalCount }건</p>
	<form action="./app/articles">
		<input type="number" name="page" value="${param.page }" placeholder="페이지"
			min="1" max="${totalCount / 100 + 1 }" step="1" style="width: 50px;">
		<button type="submit">조회</button>
	</form>
	<form action="./app/main">
	<button type = "submit">첫 화면</button>
	</form>
	<table>
		<thead>
			<tr>
				<td>편지 번호</td>
				<td>제목</td>
				<td>받은 사람 학번</td>
				<td>받은 사람</td>
				<td>등록날짜</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="letter" items="${letters}">
				<tr>
					<td>${letter.letterId }</td>
					<td>${letter.title }</td>
					<td>${letter.receiverId }</td>
					<td>${letter.receivername }</td>
					<td>${letter.cdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</head>
</html>