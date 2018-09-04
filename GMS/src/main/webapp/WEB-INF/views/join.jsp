<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/user/joinPost" method="post">
<table>
<tr>
<td>아이디</td><td><input type="text" name="user_id"></td>
</tr><tr>
<td>패스워드</td><td><input type="password" name="user_pw"></td>
</tr><tr>
<td>닉네임</td><td><input type="text" name="user_nickname"></td>
</tr><tr>
<td>이름</td><td><input type="text" name="user_name"></td>
</tr><tr>
<td>핸드폰</td><td><input type="number" name="user_phon"></td>
</tr><tr>
<td>이메일</td><td><input type="email" name="user_email"></td>
</tr><tr>
<td>생년월일</td><td><input type="date" name="birth"></td>
</tr>
</table>
<button onclick="location='${pageContext.servletContext.contextPath}/'">뒤로가기</button>
<input type="submit" value="가입하기">
</form>
</body>
</html>