<%@ page language= "java" contentType= "text/html; charset=utf-8" pageEncoding= "utf-8" %>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script>
$(document).ready(function(){
	selCategory();
selDetailSPost();
	
	function selDetailSPost(){
		$.ajax({
			type:"POST",
			url:"/app/user/SelDetailSPost",
			success: function(data){
			$("#spost_No").val(data[0].spost_No)
			$("#title").val(data[0].title)
			$("#content textarea").text(data[0].content)
			if(data[0].secret == 1){
				$("#secret").prop('checked', true);
				$("#nosecret").prop('checked', false);
			}else{
				$("#secret").prop('checked', false);
				$("#nosecret").prop('checked', true);
			}
			},
			error: function (jqXHR, Status, error){
				console.log("SelDetailSPost Error!");
			}
		})
	}
	
	
	function selCategory(){
		$.ajax({
			type:"POST",
			url:"/app/user/categorySel",
			success: function(data){
				$("#category").find("option").remove().end().append('<option value="">상품유형</option>');
				$.each(data, function(i){
// 					console.log(data[i].category_No)
					$("#category").append('<option value="'+data[i].category_No+'">'+data[i].category_Name+'</option>')
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
		}); 
	}
})
function check(){
	
	if($("[name=category_No]").val() == "") {alert("상퓸유형을 선택하세요.");	 return;}
	if($("[name=title]").val() == "") {alert("제목을 입력하세요.");	 return; }
	if($("[name=content]").val() == "") {alert("본문을 입력하세요.");	 return; }
	
	
	submit()
	
	
}	

function checkbox(){
	if($("#secret").prop("checked")){
		$("#nosecret").prop('checked', false);
	}else{
		$("#nosecret").prop('checked', true);
	}
}

function submit(){
	 document.updateSPOST.submit(); return;
}
</script>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content" align="center">
	<form name ="updateSPOST" action="${pageContext.servletContext.contextPath}/user/updateSuggestionsPost" method="post">
	<table>
		<tr>
			<td><input type="text" id="spost_No" value="" name="spost_No", hidden="true">상품유형</td>
			<td><select id="category" name="category_No"  >
				<option value="">상품유형</option>
			</select></td>
				<td>비밀글</td>
				<td><input type="checkbox" name="secret" value="1" onclick="checkbox()" id="secret"><input type="checkbox" name="secret" value="0" checked="checked" hidden="true" id="nosecret"> </td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" size="50" name="title" id="title" value=""></td>
		</tr>
		<table>
		<tr><td>글쓰기</td></tr>
		<tr>
		<td><textarea rows="30" cols="70" name="content" value=""></textarea></td>
		</tr>
		</table>
	</table>
	<div id="contentBtn"><input type="button" value="수정하기" onclick="check()"></div>
	</form>
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>