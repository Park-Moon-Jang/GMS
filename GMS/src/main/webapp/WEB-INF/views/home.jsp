<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
<title>로그인</title>
<script type="text/javascript">

function radio_onoff(id) 
{
	if(id=="user")
		{
			document.all["on"].style.display='';
			document.all["off"].style.display='none';
			
		}
	else
		{
			document.all["on"].style.display='none';
			document.all["off"].style.display='';
		}
}
</script>
</head>
<body>
<%-- <table>
<tr>
<td>
<label><input type="radio" id="User"  checked="checked"> 사용자</label>
<label><input type="radio" id="Manager"   >관리자</label>
</td>
</tr>
<tr>
<td>
<label id="userlogin">
<form name ="userloginform" action="${pageContext.servletContext.contextPath}/user/login" method="post">
아이디<input type="text" name="id"><br>
비밀번호<input type="text" name="pw">
<input type="button" value="회원가입"><input type="button" value="ID/PW찾기"><input type="submit" value="로그인">
</form>
</label>
<label id="managerlogin" >
<form name ="managerloginform" action="${pageContext.servletContext.contextPath}/manager/select" method="post">
아이디<input type="text" name="id"><br>
비밀번호<input type="text" name="pw">
<input type="submit" value="로그인">
</form>
</label>
</td>
</tr>
</table> --%>

<table>
<tr>
<td>
<label><input type="radio" name ="radio_bt" id="radio_bt" value="1" onclick="radio_onoff('user');" checked/> 사용자</label>
<label><input type="radio"  name ="radio_bt" id="radio_bt" value="0" onclick="radio_onoff('manager');" />관리자</label>
</td>
</tr>
<tr id="on" style="display:'';">
<td>
<form name ="userloginform" action="${pageContext.servletContext.contextPath}/user/login" method="post">
아이디<input type="text" name="id"><br>
비밀번호<input type="text" name="pw">
<input type="button" value="회원가입"><input type="button" value="ID/PW찾기"><input type="submit" value="로그인">
</form>
</td>
</tr>

<tr id="off" style="display: none;">
<form name ="managerloginform" action="${pageContext.servletContext.contextPath}/manager/select" method="post">
아이디<input type="text" name="id"><br>
비밀번호<input type="text" name="pw">
<input type="submit" value="로그인">
</tr>
</table>
	

</body>
</html>
