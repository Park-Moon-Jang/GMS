<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> <!-- 기본기능 -->
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %> <!-- 포멧 기능 (형식지정)-->
<%@  taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> <!-- 함수 기능 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<link href="${pageContext.servletContext.contextPath}/resources/css/userPage.css" rel="stylesheet" type="text/css">
<body>
	
	<style>
	ul{width:100%;height:30px;background:skyblue;list-style:none;padding-top:15px}
	ul li{float:left;margin-right:10px;font-family:dotum;font-size:12px;color:white;font-weight:bold}
	ul li a{float:center;font-size:12px;color:white;font-weight:bold;text-decoration:none}
	.white a{color:#fff}
</style>
	<div id="header">
	<a class="mainLink" href="${pageContext.servletContext.contextPath}/user/main">GMS(Goods-Mangement-Service)</a>
			<ul class="menu">

						<li>${session_user}님</li>
						<li><a
							href="${pageContext.servletContext.contextPath}/user/logout">logout</a></li>

			</ul>
	</div>
	<div id="topMenu">
		<ul>
			<li><a class="menuLink" id="userItem" href="${pageContext.servletContext.contextPath}/user/item">물품현황</a></li>
			<li><a class="menuLink" id="userLast" href="${pageContext.servletContext.contextPath}/user/suggestions">건의사항</a></li>
			<li><a class="menuLink" id="userScrape" href="${pageContext.servletContext.contextPath}/user/scrape">my Scrape</a></li>
			<li><a class="menuLink" id="userPage" href="${pageContext.servletContext.contextPath}/user/page">my Page</a></li>
		</ul>
	</div>
</body>
</html>