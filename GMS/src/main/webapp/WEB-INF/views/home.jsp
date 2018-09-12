<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
window.onload = function(){
	radio_onoff("user");
}
</script>
</head>
<body>


<table>
<tr>
<td>
<label><input type="radio" name ="radio_bt" id="radio_bt" value="1" onclick="radio_onoff('user');" checked="checked"/> 사용자</label>
<label><input type="radio"  name ="radio_bt" id="radio_bt" value="0" onclick="radio_onoff('manager');" />관리자</label>
</td>
</tr>
<tr id="on" style="display:'';">
<td>
<form name ="userloginform" action="${pageContext.servletContext.contextPath}/user/loginPost" method="post">
아이디<input type="text" name="user_id"><br>
비밀번호<input type="password" name="user_pw"><br>

<input type="button" onclick="location='${pageContext.servletContext.contextPath}/user/join'" value="회원가입">
<input type="button" onclick="location='${pageContext.servletContext.contextPath}/user/find'" value="id/pw찾기">
<input type="submit" value="로그인">
</form>
</td>
</tr>
<tr id="off" style="display:'none';">
<td>
<form name ="managerloginform" action="${pageContext.servletContext.contextPath}/manager/loginPost" method="post">
아이디<input type="text" name="manager_id"><br>
비밀번호<input type="password" name="manager_pw">
<input type="submit" value="로그인">
</form>
</td>
</tr>
</table>
<<<<<<< HEAD
=======
	
 
>>>>>>> branch 'moon' of https://github.com/Park-Moon-Jang/GMS.git
</body>
</html>
