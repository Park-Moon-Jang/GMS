<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>item list page</title>
<script>
$(document).ready(function(){
	companySel();
	categorySel();
	
	function companySel(){
		
		$ajax({
			type:"POST",
			url:"/app/manager/companySel",
			syccess:function(data){
				
				$("#brand").find("option").remove().end().append("<option value=''>브랜드</option>");
				$.each(data, function(i){
					console.log(data[i].company_no)
					$("#brand").append("")
					
				})
			}
		})
	}
})




</script>






</head>
<%@include file="managerHeader.jsp"%>
<body>
<p>item list page</p>


</body>
</html>