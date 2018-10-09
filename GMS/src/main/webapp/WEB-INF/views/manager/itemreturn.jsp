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


$(document).on("click", "#return", function () {

	var item_No_List = [];
	var amount_List = [];
	var ret_Amount_List = [];
	var size = $("td[name = 'item_No']").length;
	
	for(var i = 0; i < size ; i++){
		
		if(parseInt($("input[name='ret_Amount']").eq(i).val()) <= 0){
			alert("반납수량은 0보다 커야 합니다");
			return false;
		}
		
		if(parseInt($("td[name='amount']").eq(i).attr("value")) < parseInt($("input[name='ret_Amount']").eq(i).val())){
			alert("반납수량보다 재고수량이 많은 아이템이 있습니다");
			return false;
		}
		item_No_List.push($("td[name='item_No']").eq(i).attr("value"));
		amount_List.push($("td[name='amount']").eq(i).attr("value"));
		ret_Amount_List.push($("input[name='ret_Amount']").eq(i).val());
		
	}

	$.ajax({
		type:"post",
		url: "/app/store/itemreturn.do",
		dataType:"json",
		data: {"item_No_List":item_No_List,"amount_List":amount_List,"ret_Amount_List":ret_Amount_List}, 
		success: function(data){
			
			alert(data.check);
			location.reload();
			
		},
	    error: function (jqXHR, Status, error){
	          console.log("itemreturn Error!");
	    }
		
	});

});

</script>


</head>
<body>


	<p>Item return</p>

	<div id="content">
	
		<form id = "ajaxform" method = "post">

			<table>
				<tr>
					<th colspan="2">출고상품 정보</th>
				</tr>
				<tr>
					<td>상품번호</td>
					<td>상품명</td>
					<td>생산업체</td>
					<td>상품유형</td>
					<td>재고수량</td>
					<td>가격</td>
					<td>반납수량</td>
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
						<input type="number" name = "ret_Amount" max="${vo.amount}" placeholder="재고수량 내로 입력해주세요">
					</td>
				</tr>
			</c:forEach>
				</div>
				<tr>
					<td>
						<input type = "button" id = "return" value="반납">
					</td>
				</tr>
				
			</table>

		</form>	
	</div>	

</body>
</html>