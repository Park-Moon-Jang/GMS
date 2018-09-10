<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	$("#updateC").click(function(){
		$("#update").show();
		$("#delete").hide();
		
	});
	$("#deleteC").click(function(){
		$("#update").hide();
		$("#delete").show();
		
	});
})

</script>
</head>
<body>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<br>
	<button id="updateC" onclick="click('updateC');">��������</button><button id="deleteC" onclick="click('deleteC');">ȸ��Ż��</button>
	<div id="update" style="display: none;">
		<form action="${pageContext.servletContext.contextPath}/user/updatePost" method="post">
			<input type="hidden" name="user_id" value="${session_user}" />
			<table>
				<tr>
					<td>�н�����</td>
					<td><input type="password" name="user_pw"></td>
				</tr>
				<tr>
					<td>�г���</td>
					<td><input type="text" name="user_nickname"></td>
				</tr>
				<tr>
					<td>�̸�</td>
					<td><input type="text" name="user_name"></td>
				</tr>
				<tr>
					<td>�ڵ���</td>
					<td><input type="number" name="user_phon"></td>
				</tr>
				<tr>
					<td>�̸���</td>
					<td><input type="email" name="user_email"></td>
				</tr>
				<tr>
					<td>����</td>
					<td><input type="number" name="user_age"></td>
				</tr>
			</table>
			<button
				onclick="location='${pageContext.servletContext.contextPath}/'">�ڷΰ���</button>
			<input type="submit" value="����">
		</form>
	</div>
	<div id="delete" style="display: none;">
		<form action="${pageContext.servletContext.contextPath}/user/deletePost" method="post">
	<input type="hidden" name="user_id" value="${session_user}">
	��й�ȣ <input type="password" name="user_pw">
	<input type="submit" value="Ż��">
	</form>
	</div>
	
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>