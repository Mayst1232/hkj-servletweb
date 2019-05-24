<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!-- 
회원 목록
-->
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>회원 목록</title>
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
				<td>글번호</td>
				<td>제목</td>
				<td>학번</td>
				<td>이름</td>
				<td>등록날짜</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td><a href="./app/article/List?articleId=${article.articleId}">${article.articleId }</a></td>
					<td>${article.title }</td>
					<td>${article.userId }</td>
					<td>${article.name }</td>
					<td>${article.cdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</head>
</html>
