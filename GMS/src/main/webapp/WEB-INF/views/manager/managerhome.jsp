<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page session="true"%>

<html>
<head>
<title>ManagerHome</title>

<style>
table {
	width: 100%;
}

table, th, td {
	border: 1px solid #bcbcbc;
}
</style>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
</head>

<%@include file="managerHeader.jsp"%>

<body>
	<h1>Manager Home!</h1>
	<br>
	<br>
	<div style="float: left; width: 33%; padding:10px;">
	<table>
		<thead>
			<tr>
				<th colspan="7" style="width: 50%">신상품목</th>
			</tr>

		</thead>
		<tbody>
			
			<c:forEach var="vo" items="${itemlist}">
				<tr>
					<td>${vo.item_No}</td>
					<td>${vo.item_Name}</td>
					<td>${vo.category_No}</td>
					<td>${vo.company_No}</td>
					<td>${vo.price}</td>
					<td>${vo.amount}</td>
					<td>${vo.carry_Date}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		


	</table>
	</div>
	<div style=" float: left; width: 33%; padding:10px;">
<canvas id="myChart" ></canvas>
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
<div style=" float: left; width: 33%; padding:10px;">
<table>
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
			<c:forEach var="vo" items="${SList}" > 
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
<div style=" float: left; width: 33%; padding:10px;">
<table>
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
<<<<<<< HEAD
			</c:forEach>
		</tbody>
		
	</table>
</div>
=======
			</tbody>
			<thead>
				<tr>
					<th colspan="7">인기상품</th>
					<th>공지사항</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="7">인기상품1</td>
					<td>공지사항1</td>
				</tr>
			</tbody>
		</table>
			


		</form>


>>>>>>> refs/heads/park
</body>

</html>
