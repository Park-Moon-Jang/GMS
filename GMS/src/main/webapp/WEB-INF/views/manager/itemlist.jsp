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
	
	alert("페이지 준비되었다");
	companySel();
	categorySel();
	
	function companySel(){
		
		alert("companySel 함수 실행");
		
		$.ajax({
			type:"POST",
			url:"/app/manager/companySel",
			success:function(data){
				alert("companySel ajax 성공");
				$("#company").find("option").remove().end().append("<option value=''>생산업체</option>");
				$.each(data, function(i){
					console.log(data[i].company_No)
					
					$("#company").append("<option value='"+data[i].company_No+"'>"+data[i].company_Name+"</option>")
					
				})
			},
			error: function (jqXHR, Status, error){
				console.log("company Error!");
			}
		});
	}
	
	function categorySel(){
		
		$.ajax({
			
			type:"post",
			url:"/app/manager/categorySel",
			success:function(data){
				
				$("#category").find("option").remove().end().append("<option value=''>상품유형</option>");
				$.each(data, function(i){
					console.log(data[i].category_no)
					$("#category").append("<option value='"+data[i].category_No+"'>"+data[i].category_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
			
		});
	}
	
	document.getElementById('carry_date').valueAsDate = new Date();
	
})

function selBtn(){
	
	company_no = $("#company").val()
	category_no = $("#category").val();
	carry_date = $("#carry_date").val();
	
	source = {"company_no":company_no,"category_no":category_no,"carry_date":carry_date}

	alert(source)
	
	// 리스트 가져올 ajax 만들 공간
	
	//
}




</script>






</head>
<%@include file="managerHeader.jsp"%>
<body>
<p>item list page</p>

	<div id="content">
		<table>
			<tr>
				<td colspan="4">물품현황</td>
			</tr>
			<tr>
				<td colspan="4">
					<select id="company">
						<option>생산업체</option>
					</select>
					<select id="category">
						<option>상품유형</option>
					</select>
					<input type="date" id="carry_date" >
					
					<button id="selBtn" onclick="selBtn()">검색</button>
				</td>
			</tr>
			<tr>
				<td>생산업체</td>
				<td>상품유형</td>
				<td>수량</td>
				<td>가격</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>

</body>
</html>