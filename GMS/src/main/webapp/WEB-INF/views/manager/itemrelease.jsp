<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> <!-- 기본기능 -->
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %> <!-- 포멧 기능 (형식지정)-->
<%@  taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> <!-- 함수 기능 -->
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

	storeSel();

	function storeSel(){
		
		$.ajax({
			
			type:"post",
			url:"/app/manager/storeSel",
			success:function(data){
				
				$("#store").find("option").remove().end().append("<option value=''>출고매장</option>");
				$.each(data, function(i){
				
					$("#store").append("<option value='"+data[i].store_No+"'>"+data[i].store_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
			
		});
	}

})

$(document).on("click", "#release", function () {

	var item_No_List = [];
	var amount_List = [];
	var rel_Amount_List = [];
	var size = $("td[name = 'item_No']").length;
	
	if(!$("#store").val()){
		alert("출고매장을 선택해주세요");
		return; 
	}
	var store_No = $("#store").val();
	
	for(var i = 0; i < size ; i++){
		
		if(parseInt($("input[name='rel_Amount']").eq(i).val()) <= 0){
			alert("출고수량은 0보다 커야 합니다");
			return false;
		}
		
		if(parseInt($("td[name='amount']").eq(i).attr("value")) < parseInt($("input[name='rel_Amount']").eq(i).val())){
			alert("재고수량보다 출고수량이 많은 아이템이 있습니다");
			return false;
		}
		item_No_List.push($("td[name='item_No']").eq(i).attr("value"));
		amount_List.push($("td[name='amount']").eq(i).attr("value"));
		rel_Amount_List.push($("input[name='rel_Amount']").eq(i).val());
		
	}
	alert(item_No_List);
	alert(amount_List);
	alert(rel_Amount_List);

	$.ajax({
		type:"post",
		url: "/app/manager/itemrelease.do",
		dataType:"json",
		data: {"item_No_List":item_No_List,"amount_List":amount_List,"rel_Amount_List":rel_Amount_List, "store_No": store_No}, 
		success: function(data){
			console.log("itemrelease Success!");
	         window.close();
			
		},
	    error: function (jqXHR, Status, error){
	          console.log("itemrelease Error!");
	    }
		
	});

});

</script>


</head>
<body>


	<p>Item release</p>

	<div id="content">
	
		<form id = "ajaxform" method = "post">

			<table>
				<tr>
					<th colspan="2">출고상품 정보</th>
					<th>
						<select id="store">
							<option>출고매장</option>
						</select></th>
				</tr>
				<tr>
					<td>상품번호</td>
					<td>상품명</td>
					<td>생산업체</td>
					<td>상품유형</td>
					<td>재고수량</td>
					<td>가격</td>
					<td>출고수량</td>
				</tr>
				<div id= "itemData">
			<c:forEach var="vo" items= "${itemList}">
				<tr>
					<td name = "item_No" value ="${vo.item_No}" >${vo.item_No}</td>
					<td>${vo.item_Name}</td>
					<td>${vo.company_Name}</td>
					<td>${vo.category_Name}</td>
					<td name = "amount" value="${vo.amount}">${vo.amount}</td>
					<td>${vo.price}</td>
					<td>
						<input type="number" name = "rel_Amount" max="${vo.amount}" placeholder="재고수량 내로 입력해주세요">
					</td>
				</tr>
			</c:forEach>
				</div>
				<tr>
					<td>
						<input type = "button" id = "release" value="출고">
					</td>
				</tr>
				
			</table>

		</form>	
	</div>	

</body>
</html>