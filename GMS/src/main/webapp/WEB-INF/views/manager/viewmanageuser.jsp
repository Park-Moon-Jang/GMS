<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<style>
table {
	width: 100%;
}

table, th, td {
	border: 1px solid #bcbcbc;
}
</style>
</head>

<%@include file="managerHeader.jsp"%>
<body>
	회원관리페이지
	<table>
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>닉네임</td>
			<td>이름</td>
			<td>핸드폰</td>
			<td>이메일</td>
			<td>나이</td>
			<td>변경/삭제</td>
		</tr>
		<c:forEach items="${map.userlist}" var="vo">
		<tr>
			<td>${vo.user_id }</td>
			<td>${vo.user_pw }</td>
			<td>${vo.user_nickname }</td>
			<td>${vo.user_name }</td>
			<td>${vo.user_phon }</td>
			<td>${vo.user_email }</td>
			<td>${vo.user_age }</td>
			<td>
			<button onclick="location='${pageContext.servletContext.contextPath}/manager/deleteUser/${vo.user_id}'">삭제</button>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>