<%@ page language= "java" contentType= "text/html; charset=utf-8" pageEncoding= "utf-8" %>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script>
$(document).ready(function(){
	selDetailSPost();
	
	function selDetailSPost(){
		$.ajax({
			type:"POST",
			url:"/app/user/SelDetailSPost",
			success: function(data){
			$("#spost_No").val(data[0].spost_No)
			$("#category").val(data[0].category_Name)
			$("#title").val(data[0].title)
			$("#content textarea").text(data[0].content)
			if('${session_user}' == data[0].user_Id){
				$("#contentBtn").append('<button onclick="location=\'${pageContext.servletContext.contextPath}/user/updateSuggestions\'">수정</button>&nbsp;')
				$("#contentBtn").append('<button onclick="delSPost()">삭제</button>')
			}
			},
			error: function (jqXHR, Status, error){
				console.log("SelDetailSPost Error!");
			}
		})
	}
})

function delSPost(){
	$.ajax({
			type:"POST",
			url:"/app/user/delSPost",
			data:{"spost_No":$("#spost_No").val()},
			success: function(data){
			alert("삭제")
			location.href="suggestions"
			},
			error: function (jqXHR, Status, error){
				console.log("delSPost Error!");
			}
		})

}
</script>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content" align="center">
	Detail
	<table>
		<tr>
			<td>상품유형</td>
			<td>
				<input type="text" id="spost_No" value="" hidden="true">
				<input type="text" size="3" id="category" value="" readonly>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" size="50" id="title" value="" readonly></td>
		</tr>
		<table>
		<tr><td>글쓰기</td></tr>
		<tr>
		<td><textarea rows="30" cols="70" id="content" value="" readonly></textarea></td>
		</tr>
		</table>
	</table>
	<div id="contentBtn">
		
	</div>
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>