<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script type="text/javascript">
function chekpw()
{
	{
		var user_pw = document.getElementById("user_pw").value;
		var pwCheck = document.getElementById("pwCheck").value;
		if ( pwCheck == "")
                    {  
                           document.getElementById("same").innerHTML = "" } 
                     else if ( user_pw != pwCheck )
		{
			document.getElementById("same").innerHTML = "<b><font color=red size=5pt>비밀번호가 틀립니다.다시 입력</font></b>"
			return false;
		}
		else
		{
			document.getElementById("same").innerHTML = "<b><font color=green size=5pt>비밀번호가 같습니다.</font></b>"	
			return true;
		}
	}
}
</script>
</head>
<body>
<form name =userfindid action="${pageContext.servletContext.contextPath}/user/updatePW" method="post" onsubmit="return chekpw()">
<table>
<tr>
<tr class="register" height="30">
    <td width="5%" align="center">*</td>
    <td width="15%">비밀번호</td>
    <td><input type="password" name="user_pw" id="user_pw" ></td>
</tr>
<tr height="7">
    <td colspan="3"><hr /></td>
</tr>
<tr class="register" height="30">
    <td width="5%" align="center">*</td>
    <td width="15%">비밀번호 확인</td>
    <td><input type="password" name="pwCheck" id="pwCheck" onkeyup="chekpw()">&nbsp;&nbsp;<span id="same"></span></td>
</tr>
</table>
<input type="submit" value="변경">
</form>
</body>
</html>