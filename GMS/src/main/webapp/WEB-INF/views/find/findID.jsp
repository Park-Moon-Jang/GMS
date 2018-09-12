<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
</head>
<body>
<input type="text" disabled="disabled" value="${find_user}">
<br>
<button onclick="location='${pageContext.servletContext.contextPath}/user/find'">비밀번호찾기</button><button onclick="location='${pageContext.servletContext.contextPath}/'">홈</button>
</body>
</html>