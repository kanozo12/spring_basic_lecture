<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<form method="post">
		<input type="text" name="userid" value="${email}" /> <br />
		<input type="password" name="password"/> <br />
		<input type="checkbox" name="remember" />이메일 기억하기 <br />
		
		<input type="submit" value="전송"/>
	</form>
</body>
</html>