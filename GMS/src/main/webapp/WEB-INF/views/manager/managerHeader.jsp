<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> <!-- 기본기능 -->
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %> <!-- 포멧 기능 (형식지정)-->
<%@  taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> <!-- 함수 기능 -->

<div id="headwrap">

<header id="header">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript">

	var id = "<%=(String)session.getAttribute("session_manager")%>";
	windows.onload =function(){
		if(id==null){
			alert("로그인을 해주세요");
			location.href="${pageContext.servletContext.contextPath}/"
		}
	}
</script>
	<nav class ="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class = "navbar-header">
				<a class = "navbar-brand" href="#">GMS</a>
			</div>
		<div id = "navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">

						<li><a>${session_manager}님</a></li>

						<li><a
							href="${pageContext.servletContext.contextPath}/manager/userpage">userpage</a></li>
						<li><a
							href="${pageContext.servletContext.contextPath}/manager/viewmanageuser">회원관리</a></li>
						<li><a
							href="${pageContext.servletContext.contextPath}/manager/logout">logout</a></li>

			</ul>
		</div>
	</div>
	</nav>
		
			
		<div class = "col-sm-3 col-md-2 sidebar	" >
			<ul class="nav nav-sidebar">
				<li class="active"><a 
					href="${pageContext.servletContext.contextPath}/manager/managerhome">HOME</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/manager/viewitemlist">물품현황</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/manager/viewitemstored">입고관리</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/manager/viewitemreleased">출고관리</a></li>					
				<li><a
					href="${pageContext.servletContext.contextPath}/store/viewstore">매장관리</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/store/statistics">선호도통계</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/manager/managerboard">관리자 공지사항</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/manager/managerSPost">건의사항</a></li>
			</ul>
		</div>
	</header>
</div>