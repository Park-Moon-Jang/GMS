<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> <!-- 기본기능 -->
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %> <!-- 포멧 기능 (형식지정)-->
<%@  taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> <!-- 함수 기능 -->
<div id="footerwrap">
	<footer id="footer">

<style>

ul{width:650px;height:30px;background:gray;list-style:none;padding-top:15px}
ul li{float:left;margin-right:10px;font-family:dotum;font-size:12px;color:white;font-weight:bold}
ul li a{float:center;font-size:12px;color:white;font-weight:bold;text-decoration:none}
.white a{color:#fff}
</style>
<!-- 		<h1> -->
<%-- 			<a href="${pageContext.servletContext.contextPath}"><img --%>
<%-- 				src="${pageContext.servletContext.contextPath}/resources/img/logo.png" --%>
<!-- 				alt="" /></a> -->
<!-- 		</h1> -->
		<p class="addr">
			<br /> Park Jinhyeong / Moon Seongsik / Jang Woojin<span></span>
		</p>
			<ul>
				<li>https://github.com/Park-Moon-Jang/GMS.git</li>
			</ul>
		<p class="copyright">COPYRIGHT (c) 2018 MY SITE. ALL RIGHTS
			RESERVED.</p>

		<div class="btn_top">
			<a href="#top"><img
				src="${pageContext.servletContext.contextPath}/resources/img/btn_top.gif"
				alt="TOP" /></a>
		</div>

		<div class="header_top"></div>


	</footer>
</div>