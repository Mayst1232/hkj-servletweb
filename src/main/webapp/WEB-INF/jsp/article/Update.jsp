<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시글 수정</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h2>개시글 수정</h2>
	<c:if test="${mode=='FAILURE' }">
		<p style="color: red;">사용자 아이디가 다릅니다.</p>
	</c:if>
	<form action="./app/articles" method="post">
		<p>
			<label>제목 :</label><br /> <input type="text" name="title"
				required />
		</p>
		<p>
			<label>내용 :</label><br /> <input type="text" name="content"
				required />
		</p>
		<p>
			<button type="submit">확인</button>
		</p>
	</form>
</body>
</html>