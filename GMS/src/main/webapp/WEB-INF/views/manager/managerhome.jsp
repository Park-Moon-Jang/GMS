<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>

<%@include file="managerHeader.jsp"%>

<body>
<h1>
	Manager Home!  
</h1>
	<br><br>

		<table>
			<thead>
				<tr>
					<th colspan="7" style="width: 50%">신상품목</th>
					<th style="width: 50%">판매통계</th>
				</tr>
				
			</thead>
			<tbody>
			
			<c:forEach items="${map.list}" var="vo" > 
				<tr>
								<td>${vo.item_no}</td>
								<td>${vo.item_name}</td>
								<td>${vo.category_no}</td>
								<td>${vo.company_no}</td>
								<td>${vo.price}</td>
								<td>${vo.amount}</td>
								<td>${vo.carry_date}</td>
				</tr>
			</c:forEach>
				<tr>
				</tr>
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


</body>

</html>
