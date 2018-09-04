<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	brandSel();
	categorySel();
	storeSel();
	 
	function brandSel(){
		$.ajax({
			type:"POST",
			url:"/app/user/brandSel",
			success: function(data){
				
				$("#brand").find("option").remove().end().append("<option value=''>브랜드</option>");
				$.each(data, function(i){
// 					console.log(data[i].comPany_No)
					$("#brand").append("<option value='"+data[i].comPany_No+"'>"+data[i].comPany_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("brand Error!");
			}
		}); 
	}
	 
	function categorySel(){
		$.ajax({
			type:"POST",
			url:"/app/user/categorySel",
			success: function(data){
				$("#category").find("option").remove().end().append("<option value=''>상품유형</option>");
				$.each(data, function(i){
// 					console.log(data[i].category_No)
					$("#category").append("<option value='"+data[i].category_No+"'>"+data[i].category_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
		}); 
	}
	
	function storeSel(){
		$.ajax({
			type:"POST",
			url:"/app/user/storeSel",
			success: function(data){
				
				$("#store").find("option").remove().end().append("<option value=''>매장</option>");
				$.each(data, function(i){
// 					console.log(data[i].store_No)
					$("#store").append("<option value='"+data[i].store_No+"'>"+data[i].store_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("store Error!");
			}
		})
	}
	
	})
	function selBtn(){
		ComPany_No = $("#brand").val()
		Category_No = $("#category").val();
		Store_No = $("#store").val()
		
		source = {"ComPany_No":ComPany_No,"Category_No":Category_No,"Store_No":Store_No}
	
		alert(source)
	}
</script>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content">
		<table>
			<tr><td colspan="4">물품현황</td></tr>
			<tr>
			<td colspan="4">
			<select id="brand">
				<option>브랜드</option>
			</select>
			<select id="category">
				<option>상품유형</option>
			</select>
			<select id="store">
				<option>매장</option>
			</select>
			<button id="selBtn" onclick="selBtn()">검색</button>
			</td>
			</tr>
			<tr>
				<td>상품유형</td>
				<td>상품명</td>
				<td>수량</td>
				<td>가격</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>