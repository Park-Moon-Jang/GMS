<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> <!-- 기본기능 -->
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %> <!-- 포멧 기능 (형식지정)-->
<%@  taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> <!-- 함수 기능 -->

<div id="headwrap">

	<header id="header">
<script type="text/javascript">
	var id = "<%=(String)session.getAttribute("session_manager")%>";
	windows.onload =function(){
	if(id==null){
		alert("로그인을 해주세요");
		location.href="${pageContext.servletContext.contextPath}/"
	}
	}
	</script>
	


<style>
	ul{width:100%;height:30px;background:skyblue;list-style:none;padding-top:15px}
	ul li{float:left;margin-right:10px;font-family:dotum;font-size:12px;color:white;font-weight:bold}
	ul li a{float:center;font-size:12px;color:white;font-weight:bold;text-decoration:none}
	.white a{color:#fff}
</style>

		<div class="util">
			<ul class="menu">

						<li>${session_manager}님</li>

						<li><a
							href="${pageContext.servletContext.contextPath}/manager/userpage">userpage</a></li>
						<li><a
							href="${pageContext.servletContext.contextPath}/manager/viewmanageuser">회원관리</a></li>
						<li><a
							href="${pageContext.servletContext.contextPath}/manager/logout">logout</a></li>

			</ul>
		</div>

		<div class="header_top"></div>

		<nav id="gnb">
			<ul class="depth1">
				<li class="gnb01"><a 
					href="${pageContext.servletContext.contextPath}/manager/managerhome">HOME</a></li>
				<li class="gnb02"><a
					href="${pageContext.servletContext.contextPath}/manager/viewitemlist">물품현황</a></li>
				<li class="gnb03"><a
					href="${pageContext.servletContext.contextPath}/manager/viewitemstored">입고관리</a></li>
				<li class="gnb04"><a
					href="${pageContext.servletContext.contextPath}/manager/viewitemreleased">출고관리</a></li>					
				<li class="gnb05"><a
					href="${pageContext.servletContext.contextPath}/store/viewstore">매장관리</a></li>
				<li class="gnb06"><a
					href="${pageContext.servletContext.contextPath}/store/statistics">선호도통계</a></li>
				<li class="gnb07"><a
					href="${pageContext.servletContext.contextPath}/manager/managerboard">관리자 공지사항</a></li>
	
			</ul>
		</nav>

	</header>
</div>