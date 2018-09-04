<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<link href="${pageContext.servletContext.contextPath}/resources/css/userPage.css" rel="stylesheet" type="text/css">
<body>
	
	<div id="header">
	<a class="mainLink" href="#">GMS(Goods-Mangement-Service)</a><a class="logOut" href="#" >로그아웃</a>
	
	</div>
	<div id="topMenu">
		<ul>
			<li><a class="menuLink" id="userItem" href="${pageContext.servletContext.contextPath}/user/item">물품현황</a></li>
			<li><a class="menuLink" id="userLast" href="${pageContext.servletContext.contextPath}/user/last">최근본목록</a></li>
			<li><a class="menuLink" id="userScrape" href="${pageContext.servletContext.contextPath}/user/scrape">my Scrape</a></li>
			<li><a class="menuLink" id="userPage" href="${pageContext.servletContext.contextPath}/user/page">my Page</a></li>
		</ul>
	</div>
</body>
</html>