<!doctype html>
<html> 
<head> 
<base href="${pageContext.request.contextPath }/" /> 
<title>조회</title> 
</head> 
<body> 
<p> 글번호 : ${article.articleId }.</p>
<p> 제목 : ${article.title }.</p>
<p> 내용 : ${article.contentHtml }.</p>
<p> 학번 : ${article.userId }.</p>
<p> 작성자 : ${article.name }.</p>
<p> 작성날짜 : ${article.cdate }.</p>
<form action="./app/main2">
	<button type = "submit">첫 화면</button>
</form>
<form action="./app/article/Update">
	<button type = "submit">수정</button>
</form>
<form action="./app/article/Delete">
<button type = "submit">삭제</button>
</form>
</body> 
</head> 
</html>

