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
<c:set value="${map.boardvo}" var="vo"/>
<form action="${pageContext.servletContext.contextPath}/manager/updateform" method="POST" >

<input type="hidden" name="board_no" value="${vo.board_no}">
<table>
<tr>
<td>제목</td><td><input type="text" name="title" value="${vo.title}"></td>
</tr>
<tr>
<td>내용</td>
</tr><tr>
<td><input type="text" name="content" value="${vo.content}"></td>
</tr><tr>
</tr>
</table>
<input type="submit" value="수정하기">
</form>
<button onclick="location='${pageContext.servletContext.contextPath}/manager/managerboard'">뒤로가기</button>
</body>
</html>




