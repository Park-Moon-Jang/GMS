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
<script type="text/javascript">

function checkid(id,no){

	var ss_id="${session_manager}";
	if(ss_id==id)
		{
			location.href="${pageContext.servletContext.contextPath}/manager/updateboard/"+no;
		}
	else
		{

			alert("작성자가 아닙니다.");
			return false;
		}
}
function checkboard(id,no){
	var ss_id="${session_manager}";
	if(ss_id==id)
		{
			location.href="${pageContext.servletContext.contextPath}/manager/deleteBoard/"+no;
		}
	else
		{

			alert("작성자가 아닙니다.");
			return false;
		}
}


</script>
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
<td>${vo.content}</td>
</tr>
</table>
<input type="button" onclick="checkid('${vo.manager_id}','${vo.board_no}')" value="수정">
<input type="button" onclick="checkboard('${vo.manager_id}','${vo.board_no}')" value="삭제">
<button onclick="location='${pageContext.servletContext.contextPath}/manager/managerboard'">뒤로가기</button>
</body>
</html>