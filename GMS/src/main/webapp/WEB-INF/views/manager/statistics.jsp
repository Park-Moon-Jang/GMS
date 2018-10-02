<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
</head>
<%@include file="managerHeader.jsp"%>

<body>
<div style="position: relative; height:30vh; width:60vw">
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

</body>
</html>