<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
						<c:forEach var="vo" items="${map.IList}">
						<tr>
								<td>
								<c:if test="${empty map.PList}">
									<img src="x.jpg" size="20">
								</c:if>
								<c:forEach var="img" items="${map.PList}">
								
								
								<c:choose>
								<c:when test="${img.item_No eq vo.item_No}">
									${img.photo_Data}
								</c:when>
								
	
								<c:otherwise>
									<img src="x.jpg" size="20">
								</c:otherwise>
								</c:choose>
								</td> 
								</c:forEach>
								
								<td>${vo.item_Name}</td>
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
						
					
					</table></td>						
				</tr>
			</tbody>
		</table>	
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>