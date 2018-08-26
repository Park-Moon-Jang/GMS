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
	
		<form name="checkform" action="${pageContext.servletContext.contextPath}/test/insert" method="post">
		
		<br><br>
		번호와 이름 입력
		<br><br><br>
		<input type= "number" name="num">
		<br><br><br>
		
		<input type="submit" value= "입력" >

		</center>
		
		</form>




</body>
</html>
