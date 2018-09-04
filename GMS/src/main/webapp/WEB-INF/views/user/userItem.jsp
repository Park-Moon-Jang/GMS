<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	brandSel();
	categorySel();
	
	function brandSel(){
		$.ajax({
			type:"POST",
			url:"/app/user/brandSel",
			success: function(data){
				$("#brand").find("option").remove().end().append("<option value=''>�귣��</option>");
				$.each(data, function(i){
					$("#brand").append("<option value='"+data[i].comPany_Name+"'>"+data[i].comPany_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("brand Error!");
			}
		}); 
	}
	
	function categorySel(){
		$.ajax({
			type:"POST",
			url:"/app/user/categorySel",
			success: function(data){
				$("#category").find("option").remove().end().append("<option value=''>��ǰ����</option>");
				$.each(data, function(i){
					console.log(data[i].category_Name)
					$("#category").append("<option value='"+data[i].category_Name+"'>"+data[i].category_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
		}); 
	}
	})
</script>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content">
		<table>
			<tr><td colspan="4">��ǰ��Ȳ</td></tr>
			<tr>
			<td colspan="4">
			<select id="brand">
				<option>�귣��</option>
			</select>
			<select id="category">
				<option>��ǰ����</option>
			</select>
			<select>
				<option>����</option>
			</select>
			<input type="button" value="�˻�">
			</td>
			</tr>
			<tr>
				<td>��ǰ����</td>
				<td>��ǰ��</td>
				<td>����</td>
				<td>����</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>