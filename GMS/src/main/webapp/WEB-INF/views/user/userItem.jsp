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
	
	//브랜드 option 불러오기
	function brandSel(){
		$.ajax({
			type:"POST",
			url:"/app/user/brandSel",
			success: function(data){
				
				$("#brand").find("option").remove().end().append("<option value='0'>브랜드</option>");
				$.each(data, function(i){
// 					console.log(data[i].comPany_No)
					$("#brand").append("<option value='"+data[i].company_No+"'>"+data[i].company_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("brand Error!");
			}
		}); 
	}
	 
	//상품유형 option 불러오기
	function categorySel(){
		$.ajax({
			type:"POST",
			url:"/app/user/categorySel",
			success: function(data){
				$("#category").find("option").remove().end().append("<option value='0'>상품유형</option>");
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
	
	//매장 option 불러오기
	function storeSel(){
		$.ajax({
			type:"POST",
			url:"/app/user/storeSel",
			success: function(data){
				
				$("#store").find("option").remove().end().append("<option value=''>매장</option>");
				$.each(data, function(i){
// 					console.log(data[i].store_No)
					$("#store").append("<option value='"+data[i].store_Name+"'>"+data[i].store_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("store Error!");
			}
		})
	}
	
	})
	
	//검색버튼 클릭 이벤트
	function selBtn(){
		company_No = $("#brand").val()
		category_No = $("#category").val();
		store_Name = $("#store").val()
		source = {"company_No":company_No,"category_No":category_No,"store_Name":store_Name}
		
		$.ajax({
			type:"POST",
			url:"/app/user/selBtn",
			dataType:"json",
			data:source,
			success: function(data){
				var str = '<table id="selTab"><tr></tr><th>상품유형</th><th>상품명</th><th>수량</th><th>가격</th>';
				$.each(data, function(i){
					str += "<tr id='"+i+"'>"
					str += "<td>"+data[i].category_Name+"</td>";
					str += '<td><a href="javscript:;" class="itemClick"><input type="text" class="item_No" hidden="hidden" value="'+data[i].item_No+'">'+data[i].item_Name+'</td>';
					str += "<td>"+data[i].amount+"</td>";
					str += "<td>"+data[i].price+"</td>";
					str += "</tr>"
// 					alert(data[i].category_Name+","+data[i].item_Name+","+data[i].amount+","+data[i].price)
					
				})
				str += "<table>";
				$("#selTab").html(str);
			},
			error: function (jqXHR, Status, error){
				console.log("selBtn Error!");
			}
		})
		
		$(document).on("click",".itemClick",function(){
			var item_No = $(this).find(".item_No").val()
			window.open("${pageContext.servletContext.contextPath}/user/itemDetail?"+item_No);
		})
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
			<table id ="selTab">
				<tr>
					<th>상품유형</th>
					<th>상품명</th>
					<th>수량</th>
					<th>가격</th>
				</tr>
			</table>
		<table>
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>