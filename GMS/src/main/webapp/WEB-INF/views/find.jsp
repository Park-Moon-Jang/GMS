<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID/PW 찾기</title>

<script type="text/javascript">

function radio_onoff(id) 
{
	if(id=="id")
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
	radio_onoff("id");
}
</script>
</head>
<body>
<table>
<tr>
<td>
<label><input type="radio" name ="radio_bt" id="radio_bt" value="1" onclick="radio_onoff('id');" checked="checked"/> 아이디 찾기</label>
<label><input type="radio"  name ="radio_bt" id="radio_bt" value="0" onclick="radio_onoff('pw');" />비밀번호 찾기</label>
</td>
</tr>
<tr id="on" style="display:'';">
<td>
<form name =userfindid action="${pageContext.servletContext.contextPath}/user/findID" method="post">
찾으시는 계정의 이름과 이메일을 적어주세요<br>
이름<input type="text" name="user_name"><br>
이메일<input type="email" name="user_emails">
<input type="submit" value="찾기">
</form>
</td>
</tr>
<tr id="off" style="display:'none';">
<td>
<form name ="userfindpw" action="${pageContext.servletContext.contextPath}/user/findPW" method="post">
찾으시는 계정의 아이디와 이메일을 적어주세요<br>
아이디<input type="text" name="user_id"><br>
이메일<input type="email" name="user_email">
<input type="submit" value="찾기">
</form>
</td>
</tr>
</table>
</body>
</html>