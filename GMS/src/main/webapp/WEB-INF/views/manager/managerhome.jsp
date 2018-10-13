<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page session="true"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>ManagerHome</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<%-- <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet"> --%>
<%-- <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
<link href="${pageContext.servletContext.contextPath}/resources/dashboard.css" rel="stylesheet">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	getPhoto();

	function getPhoto() {
		
		var img =  $('<img name="img" width="100" height="100"/>');
		var photo = "";
		for(var i = 0; i < 3; i++){
			
			photo = $("td[name='encoded_Photo']").eq(i).attr("value");
	        img.attr('src','data:image/png;base64,'+photo);
	        img.appendTo("div[name='itemImage']");
	        photo = "";
		}

		


	}
})
</script>

</head>

<%@include file="managerHeader.jsp"%>

<body>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1>Manager Home!</h1>



		<div class ="table-responsive" style="font-size:1.5em; 	position: absolute; left:10px; top:100px;  width: 50%; padding:20px;">
		<table>
			<thead>
				<tr>
					<th colspan="7" style="width: 50%">신상품목</th>
				</tr>
	
			</thead>
			<tbody>
			<tr>
				<c:forEach var="pvo" items="${map.photolist}">
					
						<td>
							<img name="img" src="data:image/png;base64,${pvo.encoded_Data}"  width="200" height="200"/> 	
						</td>

				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="pvo" items="${map.photolist}">
					
						<td style="font-size: 1.5em" align="center">
							${pvo.item_Name}
						</td>

				</c:forEach>
			</tr>	
			</tbody>
		</table>
		</div>
		<div class="table-responsive"  style="font-size:1.5em; position: absolute; right:10px; top:100px;  width: 50%; padding:20px;">
			<table class = "table table-striped">
			<thead>
				<tr>
					<th colspan="7" style="width: 50%">선호도 통계</th>
				</tr>
	
			</thead>
			<tbody>
				<tr>
					<td>
					<canvas id="myChart" ></canvas>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
<script type="text/javascript">
	var item_name= new Array();
	var item_amount= new Array();
	<c:forEach items="${map.list}" var="vo">
	item_name.push("${vo.item_Name}");
	item_amount.push("${vo.amount}");
	</c:forEach>
	var max_y = item_amount[0];
	var ctx = document.getElementById("myChart").getContext('2d');
	
	var myChart = new Chart(ctx, {
	    type: 'bar',
	
	    data: {
	        labels: item_name,
	        datasets: [{
	            data: item_amount,
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true,
	                    fixedStepSize: 1
	                    
	                }
	            }]
	        },
	    scaleOverride : true,
		scaleShowGridLines : true,
	    scaleSteps : 1,
	    scaleStepWidth : 1,
	    scaleStartValue : 0,
	    legend: {
	        display: false
	    }
	    }
	});
	</script>
		<div class="table-responsive" style="font-size:1.5em; position: absolute; left:10px; top:500px;  width: 50%; padding:20px;">
	<table class="table table-striped">
			<thead>
				<tr>
					<th colspan="7" style="width: 50%">건의사항</th>
				</tr>
					
			</thead>
			<tbody>
				
				<tr>
					<td>글번호</td>
					<td>상품코드</td>
					<td>제목</td>
					<td>작성자</td>
				</tr>
				<c:forEach var="vo" items="${map.SList}" > 
					<tr>
									<td>${vo.spost_No}</td>
									<td>${vo.category_No}</td>
									<td><a href="${pageContext.servletContext.contextPath}/manager/managerSPostDetail?spost_No=${vo.spost_No}">${vo.title}</a></td>
									<td>${vo.user_Id}</td>
					</tr>
				</c:forEach>
			</tbody>
		
		</table>
	</div>
	<div class="table-responsive" style="font-size:1.5em; position: absolute; right:10px; top:500px;  width: 50%; padding:20px;">
	<table class = "table table-striped">
			<thead>
				<tr>
					<th colspan="7" style="width: 50%">공지사항</th>
				</tr>
	
			</thead>
			<tbody>
				
				<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				
			</tr>
			<c:forEach var="vo" items="${map.boardlist}" > 
					<tr>
									<td>${vo.board_no}</td>
									<td><a href="${pageContext.servletContext.contextPath}/manager/boardDetail/${vo.board_no}">${vo.title}</a></td>
									<td>${vo.manager_id}</td>
									<td>${vo.board_date}</td>
					</tr>
	
				</c:forEach>
			</tbody>
			
		</table>
	</div>
	
</div>



<script src="js/bootstrap.min.js"></script>
</body>

</html>
