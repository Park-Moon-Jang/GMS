<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
	
<title>Insert title here</title>

<script type="text/javascript">

$(document).ready(function(){

	companySel();
	categorySel();
	
	function companySel(){
		
		$.ajax({
			type:"POST",
			url:"/app/manager/companySel",
			success:function(data){
				$("#company").find("option").remove().end().append("<option value=''>생산업체</option>");
				$.each(data, function(i){
					
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
					
					$("#category").append("<option value='"+data[i].category_No+"'>"+data[i].category_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
			
		});
	}

	
})

function register(){
	alert("온다");
	var item_Name = $("#item_Name").val();
	var company_No = $("#company").val()*1;
	var category_No = $("#category").val()*1;
	var amount = $("#amount").val();
	var price = $("#price").val();
	var carry_Date = $("#carry_Date").val();
	var registerNum = $("#registerNum").val();
	
	alert("상품명:"+item_Name);
	alert("회사명:"+company_No);
	alert("타입:"+typeof company_No);
	alert("카테고리명:"+category_No);
	alert("타입"+typeof category_No);
	alert("수량"+amount);
	alert("가격"+price);
	alert("날짜:"+carry_Date);
	alert("입고번호:"+registerNum);
	
	var source = {"item_Name":item_Name, "company_No":company_No, "category_No":category_No, "amount":amount, "price":price, "carry_Date":carry_Date };
	
	$.ajax({
		
		type:"post",
		url:"/app/manager/itemregister",
		data: source,
		
		success: function(){
			
			alert("등록되었습니다 ");
			
		},
		error: function (jqXHR, Status, error){
			console.log("list Error!");
		}
		
	});
} 

</script>


</head>
<body>
	<p>Item register</p>
	
	<div id="content">
	
		<table>

				<tr>
					<th colspan="2">입고 등록</th>
				</tr>
				<tr>
					<td>상품명</td>
					<td>	
						<input type="text" id="item_Name">
					</td>
				<tr>
					<td>생산업체</td>
					<td>
					<select id="company">
							<option>생산업체</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>상품유형</td>
					<td>
						<select id="category">
							<option>상품유형</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>입고번호</td>
					<td>	
						<input type="number" id="registerNum">
					</td>
				</tr>
				<tr>
					<td>수량</td>
					<td>	
						<input type="number" id="amount">
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>	
						<input type="number" id="price">
					</td>
				</tr>
				<tr>
					<td>입고일자</td>
					<td>	
						<input type="date" id="carry_Date" >
					</td>
				</tr>
				<tr>
					<td colspan="2">	
						<button id="register" onclick="register()">등록</button>
					</td>
				</tr>

		</table>
	</div>	
	

	
</body>
</html>