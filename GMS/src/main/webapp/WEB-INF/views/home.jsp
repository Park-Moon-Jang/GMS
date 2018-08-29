<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
	<br><br>
		<form name="insertform" action="${pageContext.servletContext.contextPath}/test/insert" method="post">
		insert
		<br><br><br>
		<input type= "number" name="category_no">
		<br>
		<input type= "text" name="category_name">
		<br><br><br>
		<input type="submit" value= "입력" >
		</form>
		
		<p>---------------------------------------</p>
		<form  name="deleteform" action="${pageContext.servletContext.contextPath}/test/delete" method="post">
		delete
		<br><br><br>
		<input type= "number" name="category_no">
		<br>
		<input type="submit" value= "삭제" >
		</form>
		<p>---------------------------------------</p>
		select
		<a href="${pageContext.servletContext.contextPath}/test/list">목록 보기</a>
		
				<br><br><br>
		<!-- 		목록보기 -->
		<c:forEach items="${map.list}" var="vo" >
			<table  >
				<tr>
					<td>${vo.category_no}</td>
					<td>${vo.category_name}</td>

<%-- 					<td><input type=button value="수정" OnClick="windowOpen('${pageContext.servletContext.contextPath}/guestbook/checkpw?no=${vo.no}&check=1')"></td> --%>
<%-- 					<td><input type=button value="삭제" OnClick="windowOpen('${pageContext.servletContext.contextPath}/guestbook/checkpw?no=${vo.no}&check=2')"></td> --%>

				</tr>
				</table>
		</c:forEach>			
		
		</form>
		<p>---------------------------------------</p>
		<form  name="updateform" action="${pageContext.servletContext.contextPath}/test/update" method="post">
		update
		<input type= "number" name="num">
		<br>
		<input type= "text" name="name">
		<br><br><br>
		<input type="submit" value= "입력" >		

		</form>
		


</body>
</html>
