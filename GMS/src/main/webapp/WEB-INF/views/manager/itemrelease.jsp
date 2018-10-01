<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>

<title>Insert title here</title>

<script type="text/javascript">

$(document).ready(function(){
	getPhoto();
	
	function getPhoto() {
		
		var photo = $("#encoded_Photo").val();
		var img =  $('<img id="img" width="100" height="100"/>');
        img.attr('src','data:image/png;base64,'+photo);
        img.appendTo("#itemImage");

	}
})
////////////
$(document).on("click", "#register", function () {

	var formData = new FormData();
	
	formData.append("item_Name", $("input[name=item_Name]").val());
	formData.append("company", $("select[name=company]").val());
	formData.append("category", $("select[name=category]").val());
	
	formData.append("registerNum", $("input[name=registerNum]").val());
	formData.append("amount", $("input[name=amount]").val());
	formData.append("price", $("input[name=price]").val());
	formData.append("carry_Date", $("input[name=carry_Date]").val());
	formData.append("photo_Name", $("input[name=photo_Name]").val());
	formData.append("photo_Data", $("input[name=photo_Data]")[0].files[0]);

	$.ajax({
		url: "/app/manager/itemregister.do",
		data: formData, 
		processData: false, 
		contentType: false, 
		type: "POST", 
		success: function(data){
			alert("success"); 
		},
		error: function(){ 
			alert("error");

		} 	
		
	});

});

</script>


</head>
<body>


	<p>Item register</p>
	
	<div id="content">
		<form id = "ajaxform" method = "post" enctype="multipart/form-data">
			<table>
				<tr>
					<th colspan="2">출고</th>
				</tr>
				<p>상품정보</p>
				<tr>
					<td>상품번호</td>
					<td>	
						<p name="item_No" value = "${ivo.item_No}"></p>
					</td>
				</tr>
				<tr>
					<td>상품명</td>
					<td>	
						<p name="item_Name" value = "${ivo.item_Name}"></p>
					</td>
				</tr>
				<tr>
					<td>생산업체</td>
					<td>
						<p name="company" value = "${ivo.company_Name}"></p>
					</td>
				</tr>
				<tr>
					<td>상품유형</td>
					<td>
						<p name="category" value = "${ivo.category_Name}"></p>
					</td>
				</tr>
				<tr>
					<td>수량</td>
					<td>
						<p name="amount" value = "${ivo.amount}"></p>
					</td>
				</tr>
				<tr>	
					<td>가격</td>
					<td>
						<p name="price" value = "${ivo.price}"></p>
					</td>
				</tr>
				<tr>
					<td>입고일자</td>
					<td>	
						<input type="date" name="carry_Date" value="${ivo.carry_Date}" >
					</td>
				</tr>
				<tr>
					<td>
						사진
					</td>
					<td>
						<p name="photo_Name" value = "${pvo.photo_Name}"></p>
					</td>
					<td>
						<img id ="img" width="100" height="100"/>
					</td>
				</tr>
				<tr>
					<td>출고매장</td>
					<td>
						<select name="store">
							<option>매장선택</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>출고수량</td>
					<td>
						<input type="number" name= "rel_amount">
					</td>
				</tr>
				<tr>
					<td colspan="2">	
						<input type="button" id="register" value ="등록">
					</td>
				</tr>
			</table>
		</form>	
	</div>	

</body>
</html>