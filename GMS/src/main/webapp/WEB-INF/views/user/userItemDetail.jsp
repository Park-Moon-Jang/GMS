<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script>
$(document).ready(function(){
	itemDetailSel(); 
	
	function itemDetailSel(){
		$.ajax({
			type:"POST",
			url:"/app/user/itemDetalSel",
			success: function(data){
				
				 
				
				$.each(data, function(i){
					str = '<td id="brand">'+data[i].company_Name+'</td>';
					$("#brand").html(str);
					str = '<td id="category">'+data[i].category_Name+'</td>';
					$("#category").html(str);
					str = '<td id="item_Name">'+data[i].item_Name+'</td>';
					$("#item_Name").html(str);
					str = '<td id="amount">'+data[i].amount+'</td>';
					$("#amount").html(str);
					str = '<td id="price">'+data[i].price+'</td>';
					$("#price").html(str);
				})
			},
			error: function (jqXHR, Status, error){
				console.log("itemDetailSel Error!");
			}
		}); 
	}
})

function insertScrap(){
	$.ajax({
		type:"POST",
		url:"/app/user/insertScrap",
		success: function(data){
			alert("스크랩 추가!")
			location.reload();
		},
		error: function (jqXHR, Status, error){
			console.log("insertScrap Error!");
		}
	}); 
}

function deleteScrap(){
	$.ajax({
		type:"POST",
		url:"/app/user/deleteScrap",
		success: function(data){
			alert("스크랩 삭제!")
			location.reload();
		},
		error: function (jqXHR, Status, error){
			console.log("deleteScrap Error!");
		}
	}); 
}
</script>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content" align="center">
		
		<table>
			<tr><th colspan="3">detail</th><th>
			<c:choose>
				<c:when test="${session_scrape == 'false' }">
					<a href="javascript:;" onclick="insertScrap()">☆</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:;" onclick="deleteScrap()">★</a>
				</c:otherwise>
			</c:choose>
			
			
			&nbsp;<button onclick="window.close()">닫기</button></th></tr>
			<tr><td colspan="2" rowspan="6" id="itemImg"><img src="" width="200" height="200"></td><td colspan="2">상품정보</td></tr>
			<tr><td>브랜드</td><td id="brand"></td></tr>
			<tr><td>상품유형</td><td id="category"></td></tr>
			<tr><td>상품명</td><td id="item_Name"></td></tr>
			<tr><td>수량</td><td id="amount"></td></tr>
			<tr><td>가격</td><td id="price"></td></tr>
		</table>	
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>