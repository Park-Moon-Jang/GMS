<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>item list page</title>
<script>
$(document).ready(function(){
	companySel();
	categorySel();
	
	function companySel(){
		
		$ajax({
			type:"POST",
			url:"/app/manager/companySel",
			syccess:function(data){
				
				$("#brand").find("option").remove().end().append("<option value=''>브랜드</option>");
				$.each(data, function(i){
					console.log(data[i].company_no)
					$("#brand").append("")
					
				})
			}
		})
	}
})




</script>






</head>
<%@include file="managerHeader.jsp"%>
<body>
<p>item list page</p>

	<div id="content">
		<table>
			<tr><td colspan="4">물품현황</td></tr>
			<tr>
			<td colspan="4">
			<select id="company">
				<option>생산업체</option>
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

</body>
</html>