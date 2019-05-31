<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글쓰기</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="./app/letter/AddSuccess" method="post">
		<p>	
			편지 제목:<br> <input type="text" name="title" value= "${param.title }">
		</p>	
		<p>
			편지내용
		</p>
		<p>
		<textarea name="content" style="width: 100%; height: 200px;" required></textarea>
		</p>
		<button type="submit">편지쓰기</button>
	</form>
</body>
</html>