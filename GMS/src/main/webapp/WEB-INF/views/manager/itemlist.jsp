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
	
	alert("오늘날짜"+new Date());
	var today = new Date();
	
	alert(today);

	
})

function selBtn(){
	
	var company_no = $("#company").val();
	var category_no = $("#category").val();
	var date1 = $("#carry_date1").val();
	var date2 = $("#carry_date2").val();
	
	alert(company_no);
	alert(category_no);
	alert(date1);
	alert(date2);
// 	carry_date1 = date1.getYear()+"-"+date
	
	
	var data = {"company_no":company_no, "category_no":category_no, "carry_date1":date1, "carry_date2":date2}
	var source = JSON.stringify(data);
	
	var values = []; //ArrayList 값을 받을 변수를 선언

	//검색할 코드를 넘겨서 값을 가져온다.		
	$.post(
		"/app/manager/itemlist", 
		data,
		function(retVal) {
			if(retVal.code == "OK") { //controller에서 넘겨준 성공여부 코드
				
				values = retVal.list; //java에서 정의한 ArrayList명을 적어준다.
				
				$.each(values, function() {
				   console.log(values); //Book.java 의 변수명을 써주면 된다.
				});
				
				alert("성공");
			}
			else {
				alert("실패");
			}					
		}
	);
	// 리스트 가져올 ajax 만들 공간
// 	$.ajax({
		
// 		type:"post",
// 		url:"/app/manager/itemlist",
// 		dataType:"json",
// 		data:{"company_no":company_no, "category_no":category_no, "carry_date1":date1, "carry_date2":date2},
		
// 		success:function(data){
// 			console.log("성공");
// 			$.each(data, function(){
// 				console.log(data[i])
// 			})
// 		},
// 		error: function (jqXHR, Status, error){
// 			console.log("list Error!");
// 		}
		
		
		
// 	});
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
						<input type="date" id="carry_date1" >
					</td>
					<td>	
						<input type="date" id="carry_date2" >
					</td>
					<td>	
						<button id="selBtn" onclick="selBtn()">검색</button>
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "vo" items= "${list}">
					<tr>
								<td>${vo.item_Name}</td>
								<td>${vo.category_No}</td>
								<td>${vo.company_No}</td>
								<td>${vo.amount}</td>
								<td>${vo.carry_Date}</td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
	</div>

</body>
</html>