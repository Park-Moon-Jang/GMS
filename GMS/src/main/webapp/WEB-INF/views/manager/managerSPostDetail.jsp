<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
$(document).ready(function(){
$(document).on("click","#comentSubmit",function(){
	var coment = $("#coment").val()
	$.ajax({
		type:"POST",
		url:"/app/manager/insertSPostComent",
		data:{"coment":coment},
		success: function(data){
			location.href="managerSPost";
		},
		error: function (jqXHR, Status, error){
			console.log("insert Error!");
		}
	}); 
})
})
</script>
<%@include file="managerHeader.jsp"%>
<div id="content" align="center">
	<table>
		<c:forEach var="vo" items="${SList}">
		<tr>
			<td>상품유형</td>
			<td>
				<input type="text" size="3" id="category" value="${vo.category_Name}" readonly>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" size="50" id="title" value="${vo.title}" readonly></td>
		</tr>
		
		<table>
		<tr><td>내용</td></tr>
		<tr>
		<td><textarea rows="30" cols="70" id="content" value="" readonly>${vo.content}</textarea></td>
		</tr>
		
		</table>
		</c:forEach>
	</table>
	<table id="comentTable">
		
			<c:forEach var="vo" items="${Coment}">
			
			<c:if test="${!empty vo}">
			<tr>
				<th colspan="2" align="left">댓글</th>
			</tr>
			<tr>
			<td>${vo.manager_Id}</td>
			<td>
			<fmt:formatDate value="${vo.write_Date}" pattern="yyyy-MM-dd"/>
			</td>
				<td></td>
				
			</tr>
			<tr>
			<td colspan="3" height="70" width="500">${vo.content}</td>
			</tr>
			
		</c:if>
			</c:forEach>
		
			
	</table>
	<table>
			<tr>
				<td><textarea rows="4" cols="80" id="coment"></textarea></td>
				<td><button id="comentSubmit">등록</button></td>
			</tr>
		</table>
</body>
</html>