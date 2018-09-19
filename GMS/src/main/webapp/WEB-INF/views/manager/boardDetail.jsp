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
<table>
<c:set value="${map.boardvo}" var="vo"/>
<tr>
<td>글번호</td><td>${vo.board_no}</td><td>작성시간</td><td>${vo.board_date}</td>
</tr><tr>
<td>제목</td><td>${vo.title}</td>
<td>작성자</td><td>${vo.manager_id}</td>
</tr>
<tr>
<td>내용</td>
<td>${vo.content }</td>
</tr>
</table>
<button onclick="location='${pageContext.servletContext.contextPath}/manager/managerboard'">뒤로가기</button>
</body>
</html>