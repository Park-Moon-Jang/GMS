<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<link href="${pageContext.servletContext.contextPath}/resources/css/userPage.css" rel="stylesheet" type="text/css">
<body>
<script>
$(document).ready(function(){
	selectPhoto()
	function selectPhoto(){
		var scrapArray = [];
		var tags = document.getElementsByClassName("text")
		for ( var x = 0; x < tags.length; x++ ){ 
			   scrapArray.push(tags[x].value);
		      } 
		
		$.ajax({
			type:"POST",
			url:"/app/user/photoSel",
			data:{"scrapArray":scrapArray},
			success: function(data){
			if(data == ""){
				for(var x = 0; x < scrapArray.length; x++){
					var img = document.createElement('img');
					
					img.src='';
					img.height='100';
					img.width='100'
					document.getElementById(scrapArray[x]).appendChild(img);
				}
			}
				$.each(data, function(i){
					var item_No = data[i].item_No;
					var str = data[i].photo_Data;
					for(var x = 0; x < scrapArray.length; x++){
						if(scrapArray[x] == item_No){
							var img = document.createElement('img');
							
							img.src='data:image/png;base64,'+str;
							img.height='100';
							img.width='100'
							document.getElementById(item_No).appendChild(img);
						}else{
							var img = document.createElement('img');
							
							img.src='';
							img.height='100';
							img.width='100'
							document.getElementById(scrapArray[x]).appendChild(img);
						}
							
					}
					
				})
			},
			error: function (jqXHR, Status, error){
				console.log("photoSel Error!");
			}
		}); 
	}
});
</script>

	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content" align="center">
		<table>
			<thead>
				<tr>
					<th>신규상품</th>
					<th>myScrap</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><table id="newItem">
					<tr>
					<td>상품이름</td>
					<td>유형</td>
					<td>브랜드</td>
					<td>가격</td>
					<td>수량</td>
					<td>입고일</td>	
					</tr>
						<c:forEach var="vo" items="${itemlist}">
				<tr>
					<td>${vo.item_Name}</td>
					<td>${vo.category_Name}</td>
					<td>${vo.company_Name}</td>
					<td>${vo.price}</td>
					<td>${vo.amount}</td>
					<td>${vo.carry_Date}</td>
				</tr>
			</c:forEach>
			
					</table>
					</td>
					<td><table id="myScrap">
					<tr>
						<td>이미지</td>
						<td>상품이름</td>
						<td>수량</td>
						</tr>
						<c:forEach var="vo" items="${IList}">
						<tr>
								<td><div id="${vo.item_No}"></div></td>								
								<td><input type="text" value="${vo.item_No}" class="text" hidden="true">${vo.item_Name}</td>
								<td>${vo.amount}</td>
						</tr>
						</c:forEach>
					</table></td>				
				</tr>
			</tbody>
			<thead>
				<tr>
					<th>인기상품</th>
					<th>건의사항</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><table id="hitItem">
					<div>
						<canvas id="myChart"></canvas>
					</div>
					</table></td>
					<td><table id="mySPost">
						<td>상품유형</td>
						<td>제목</td>
						</tr>
						<c:forEach var="vo" items="${SList}">
						<tr>
								<td>${vo.category_Name}</td>								
								<td>${vo.title}</td>
						</tr>
						</c:forEach>
					
					</table></td>						
				</tr>
			</tbody>
		</table>	
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
	
	
	<script type="text/javascript">
var item_name= new Array();
var item_amount= new Array();
<c:forEach items="${map.list}" var="vo">
item_name.push("${vo.item_Name}");
item_amount.push("${vo.amount}");
</c:forEach>
var max_y = item_amount[0];
console.log(document.getElementById("hitItem"))
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