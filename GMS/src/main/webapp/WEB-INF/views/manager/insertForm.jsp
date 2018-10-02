<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
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
	
<form action="${pageContext.servletContext.contextPath}/manager/insertBoard" method="post" >
<input type="hidden" name="manager_id" value="${session_manager}">
<table>
<tr>
<td>제목</td><td><input type="text" name="title"></td>
</tr>
<tr>
<td>내용</td>
</tr><tr>
<td><input type="text" name="content"></td>
</tr><tr>
</tr>
</table>
<input type="submit" value="글쓰기">
</form>
<button onclick="location='${pageContext.servletContext.contextPath}/manager/managerboard'">뒤로가기</button>
</body>
</html>