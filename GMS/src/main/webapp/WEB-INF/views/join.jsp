<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
	
<title>회원가입</title>

<script>
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
<script type="text/javascript">
var chek= 0;
$(function(){
	$("#checkid").click(function(){
		var userid= $("#user_id").val();
		$.ajax({
			async:true,
			type:'POST',
			data:userid,
			url:"checkid",
			dataType:"json",
			contentType:"application/json;charset=UTF-8",
			success:function(data){
				if (data.cnt>0){
					alert("사용중인 ID 입니다.다른아이디를 입력하세요");
					document.getElementById("user_id").style.backgroundColor = "red";
					
				}
				else {
					alert("사용가능한 아이디입니다.");
				
					document.getElementById("user_id").style.backgroundColor = "green";
					chek=1;
				}
			},
			error :function(error){
				alert("error : " + error);
			}
		
		
		});
		
	});
});

</script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/user/joinPost" name="joinform" method="post" onsubmit="return chekpw()">
<table>
<tr>
<td>아이디</td><td><input type="text" id= "user_id" name="user_id"><input type="button" id="checkid" value="중복확인"></td>
</tr><tr>
<td>  비밀번호  </td><td><input type="password" id="user_pw" name="user_pw"></td>
</tr><tr>
<td>비밀번호 확인</td><td><input type="password" name="pwCheck" id="pwCheck" onkeyup="chekpw()">&nbsp;&nbsp;<span id="same"></span></td>
</tr><tr>
<td>닉네임</td><td><input type="text" name="user_nickname"></td>
</tr><tr>
<td>이름</td><td><input type="text" name="user_name"></td>
</tr><tr>
<td>핸드폰</td><td><input type="number" name="user_phon"></td>
</tr><tr>
<td>이메일</td><td><input type="email" name="user_email"></td>
</tr><tr>
<td>나이</td>
<td><input type="number" name="user_age"></td>
</tr>
</table>
<button onclick="location='${pageContext.servletContext.contextPath}/'">뒤로가기</button>
<input type="submit" value="가입하기">
</form>
</body>
</html>