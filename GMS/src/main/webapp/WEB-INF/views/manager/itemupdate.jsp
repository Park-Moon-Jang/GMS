<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

	companySel();
	categorySel();
	getPhoto();
	
	function companySel(){
		
		$.ajax({
			type:"POST",
			url:"/app/manager/companySel",
			success:function(data){
				$("select[name=company]").find("option").remove().end().append("<option value=''>생산업체</option>");
				$.each(data, function(i){
					
					$("select[name=company]").append("<option value='"+data[i].company_No+"'>"+data[i].company_Name+"</option>")
					
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
				
				$("select[name=category]").find("option").remove().end().append("<option value=''>상품유형</option>");
				$.each(data, function(i){
					
					$("select[name=category]").append("<option value='"+data[i].category_No+"'>"+data[i].category_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
			
		});
	}
	
	$(document).on("change", "#photo_Data", handleImgFileSelect);
	
	var sel_file;
	
	function  handleImgFileSelect(e){
		
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
			sel_file = f;
			
			var reader = new FileReader();
			reader.onload = function(e){
				
				var img =  $('<img id="img" width="100" height="100"/>');
				$("#itemImage").find("img").remove();
				
				
				img.attr("src", e.target.result);
				img.appendTo("#itemImage");
			}
			reader.readAsDataURL(f);
			
		})
	}
	
	
	
	
	function getPhoto() {
		
		var photo = $("#encoded_Photo").val();
		var img =  $('<img id="img" width="100" height="100"/>');
        img.attr('src','data:image/png;base64,'+photo);
        img.appendTo("#itemImage");

	}
	
})


$(document).on("click", "#update", function () {

	if(!$("input[name=item_Name]").val()){
		alert("상품명을 기입해주세요");
		return;
	}
	if(!$("select[name=company]").val()){
		alert("생산업체를 선택해주세요");
		return;
	}
	if(!$("select[name=category]").val()){
		alert("카테고리를 선택해주세요");
		return;
	}
	if(!$("input[name=registerNum]").val()){
		alert("입고번호를 기입해주세요");
		return;
	}
	if(!$("input[name=amount]").val()){
		alert("수량을 기입해주세요");
		return;
	}
	if(!$("input[name=price]").val()){
		alert("가격을 기입해주세요");
		return;
	}
	if(!$("input[name=carry_Date]").val()){
		alert("입고일자를 선택해주세요");
		return;
	}
	if(!$("input[name=registerNum]").val()){
		alert("입고번호를 기입해주세요");
		return;
	}	
	if(!$("input[name=photo_Name]").val()){
		alert("사진제목을 기입해주세요");
		return;
	}
	if(!$("input[name=photo_Data]")[0].files[0]){
		alert("사진을 선택해주세요");
		return;
	}
	
	
	var formData = new FormData();
	
	formData.append("item_Name", $("input[name=item_Name]").val());
	formData.append("ex_Item_No", $("input[name=ex_Item_No]").val());
	
	formData.append("company", $("select[name=company]").val());
	formData.append("category", $("select[name=category]").val());
	
	formData.append("registerNum", $("input[name=registerNum]").val());
	formData.append("amount", $("input[name=amount]").val());
	formData.append("price", $("input[name=price]").val());
	formData.append("carry_Date", $("input[name=carry_Date]").val());
	formData.append("photo_Name", $("input[name=photo_Name]").val());
	formData.append("photo_Data", $("input[name=photo_Data]")[0].files[0]);
	

	$.ajax({
		url: "/app/manager/itemupdate.do",
		data: formData, 
		processData: false, 
		contentType: false, 
		type: "POST", 
		success: function(data){
			alert("success"); 
			alert(data.check);
		},
		error: function(){ 
			alert("error");

		} 	
		
	});

});

</script>


</head>
<body>
	<p>Item update</p>
	
	<div id="content">
		<form id = "ajaxform" method = "post" enctype="multipart/form-data">
			수정할 상품번호: 
			<p> ${ivo.item_No} </p>
			<table>

				<tr>
					<th colspan="2">입고 수정</th>
					
				</tr>
				<tr>
					<td>상품명</td>
					<td>	
						<input type="text" name="item_Name" value = "${ivo.item_Name}" />
						<input type="hidden" name="ex_Item_No" value = "${ivo.item_No}"/>
					</td>
				</tr>
				<tr>
					<td>생산업체</td>
					<td>
					<select name="company">
							<option>생산업체</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>상품유형</td>
					<td>
						<select name="category">
							<option>상품유형</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>입고번호</td>
					<td>	
						<input type="number" name="registerNum">
					</td>
				</tr>
				<tr>
					<td>수량</td>
					<td>
						<input type="number" name="amount" value="${ivo.amount}">
					</td>
				</tr>
				<tr>	
					<td>가격</td>
					<td>	
						<input type="number" name="price" value="${ivo.price}">
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
					<input type= "text" name="photo_Name"><br>
					<input type="file" name ="photo_Data" id= "photo_Data" value = "${pvo.photo_Data}">
					<input type="hidden" id="encoded_Photo" value = "${encoded_Photo}">
					<div id="itemImage"></div>
 
					</td>
				</tr>
				<tr>
					<td colspan="2">	
						<input type="button" id="update" value ="수정">
					</td>
				</tr>

			</table>
		</form>	
	</div>	
	

	
	

	
</body>
</html>