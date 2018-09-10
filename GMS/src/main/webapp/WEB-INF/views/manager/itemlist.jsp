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
		
		$.ajax({
			type:"POST",
			url:"/app/manager/companySel",
			success:function(data){
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

	
})


function selBtn(){
	
	var company_No = $("#company").val();
	var category_No = $("#category").val();
	var from_Date = $("#from_date").val();
	var to_Date = $("#to_date").val();
	
	function my_curr_date() {      
	    var currentDate = new Date()
	    var day = currentDate.getDate();
	    var month = currentDate.getMonth() + 1;
	    var year = currentDate.getFullYear();
	    var my_date = year+"-"+ month +"-"+day;
	    
	    return my_date;
	}
	
	
	if(!company_No){

		alert("회사명 입력 ");
		company_No = 0;
		}
	if(!category_No){
		alert("카테고리 입력 안했네");
		category_No = 0;
	}
	if(!from_Date){
		alert("시작날짜 입력 안했네");
		from_Date = "2017-01-01";
	}
	if(!to_Date){
		alert("끝 날짜 입력 안했네");
		to_Date = my_curr_date();

	}
	
	
	alert(company_No);
	alert(category_No);
	alert(from_Date);
	alert(to_Date);
// 	carry_date1 = date1.getYear()+"-"+date
	
	
	var source = {"company_No":company_No, "category_No":category_No, "from_Date":from_Date, "to_Date":to_Date};
// 	var source = JSON.stringify(data);
	
	var values = []; //ArrayList 값을 받을 변수를 선언

	$.ajax({
		
		type:"post",
		url:"/app/manager/itemlist",
		dataType:"json",
		data: source,
		
		success: function(data){
			console.log("성공");
			alert("성공");
			var str = '<table id="itemTab"><tr><td>상품명</td><td>생산업체 번호</td><td>카테고리 번호</td><td>수량</td><td>입고일</td></tr>';
			
			values = data.list;
			
			$.each(values, function(i){
				str += "<tr id='"+i+"'>"
				str += "<td>"+values[i].item_Name+"</td>";
				str += "<td>"+values[i].company_No+"</td>";
				str += "<td>"+values[i].category_No+"</td>";
				str += "<td>"+values[i].amount+"</td>";
				str += "<td>"+values[i].carry_Date+"</td>";
				str += "</tr>"
//					alert(data[i].category_Name+","+data[i].item_Name+","+data[i].amount+","+data[i].price)
				
			})
			str += "</table>";
			$("#itemTab").html(str);
		},
		error: function (jqXHR, Status, error){
			console.log("list Error!");
		}
		
		
		
	});
	
	///////////////////////

	///////////////////////
	
	
	
// 	검색할 코드를 넘겨서 값을 가져온다.		
// 	$.post(
// 		"/app/manager/itemlist", 
// 		data,
// 		function(retVal) {
// 			if(retVal.code == "OK") { //controller에서 넘겨준 성공여부 코드
				
// 				values = retVal.list; //java에서 정의한 ArrayList명을 적어준다.
				
// 				$.each(values, function() {
// 				   console.log(values); //Book.java 의 변수명을 써주면 된다.
// 				});
				
// 				alert("성공");
// 			}
// 			else {
// 				alert("실패");
// 			}					
// 		}
// 	);
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
			<thead>
				<tr>
					<th corspan="4" ">물품현황</th>
				</tr>
				<tr>
		
					<td>
						<select id="company">
							<option>생산업체</option>
						</select>
					</td>
					<td>
						<select id="category">
							<option>상품유형</option>
						</select>
					</td>
					<td>	
						<input type="date" id="from_date" >
					</td>
					<td>	
						<input type="date" id="to_date" >
					</td>
					<td>	
						<button id="selBtn" onclick="selBtn()">검색</button>
					</td>
				</tr>
			</thead>
			<tbody>
				<table id="itemTab">
					<tr>
								<td>상품명</td>
								<td>생산업체 번호</td>
								<td>카테고리 번호</td>
								<td>수량</td>
								<td>입고일</td>
					</tr>
				</table>
			</tbody>
			
		</table>
	</div>

</body>
</html>