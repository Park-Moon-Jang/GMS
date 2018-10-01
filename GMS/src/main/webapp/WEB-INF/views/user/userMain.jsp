<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

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
					<td><table id="hitItem"></table></td>
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
</body>
</html>