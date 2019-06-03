<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글쓰기</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="./app/article/AddSuccess" method="post">
		<p>	
			글제목:<br> <input type="text" name="title" value= "${param.title }">
		</p>	
		<p>
			글내용
		</p>
		<p>
		<textarea name="content" style="width: 100%; height: 200px;" required></textarea>
		</p>
		<button type="submit">글쓰기</button>
	</form>
</body>
</html>