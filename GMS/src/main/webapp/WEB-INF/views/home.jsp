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
	
		<form name="insertform" action="${pageContext.servletContext.contextPath}/test/insert" method="post">
		
		<br><br>
		번호와 이름 입력
		<br><br><br>
		<input type= "number" name="num">
		<br>
		<input type= "text" name="name">
		<br><br><br>
		
		<input type="submit" value= "입력" >
	
		</form>

</body>
</html>
