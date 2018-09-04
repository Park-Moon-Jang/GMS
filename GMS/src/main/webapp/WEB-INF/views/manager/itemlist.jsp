<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>item list page</title>
<script>
	$(document).ready(function(){
		$("#btnSearch").click(function(){
			
		})
	})

</script>






</head>
<%@include file="managerHeader.jsp"%>
<body>
<p>item list page</p>

		<form name="form1" method="post" action="${pageContext.servletContext.contextPath}/item/list">
	        <select name="searchOption">            
	            <option value="all" <c:out value="${map.search == 'all'?'selected':''}"/> >전체</option>
	            <option value="id" <c:out value="${map.search == 'id'?'selected':''}"/> >작성자</option>
	            <option value="content" <c:out value="${map.search == 'content'?'selected':''}"/> >내용</option>
	            <option value="title" <c:out value="${map.search == 'title'?'selected':''}"/> >제목</option>
	        </select>
	        <input name="keyword" value="${map.keyword}">
	        <input type="submit" value="조회">
        </form>

	
	
</body>
</html>