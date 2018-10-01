<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager board</title>
</head>
<%@include file="managerHeader.jsp"%>
<body>
	<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>날짜</td>
			
		</tr>
		<c:forEach var="vo" items="${map.boardlist}" > 
				<tr>
								<td>${vo.board_no}</td>
								<td><a href="${pageContext.servletContext.contextPath}/manager/boardDetail/${vo.board_no}">${vo.title}</a></td>
								<td>${vo.manager_id}</td>
								<td>${vo.board_date}</td>
				</tr>
			</c:forEach>

	</table>
	<input type="button" onclick="location='${pageContext.servletContext.contextPath}/manager/insertForm'" value="글쓰기">
	
</body>
</html>