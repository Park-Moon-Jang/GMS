<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
$(document).ready(function(){
	SPostList(1);
	
	function SPostList(curPage){
		$.ajax({
			type:"POST",
			url:"/app/manager/SPostList",
			data:{"curPage":curPage},
			success: function(data){
				var str = '<table id ="SelTab"><tr><th>글번호</th><th>상품코드</th><th width="400">제목</th><th>작성자</th><th>조회수</th></tr>';
				$.each(data.SList, function(i){
					str += "<tr id='"+i+"'>"
					str += "<td>"+data.SList[i].spost_No+"</td>";
					str += '<td>'+data.SList[i].category_No+'</td>';
					str += '<td><a href="javascript:;" id="detail"><input type="text" value="'+data.SList[i].spost_No+'" hidden="true" class="detailText">'+data.SList[i].title+'</a></td>';
					str += "<td>"+data.SList[i].user_Id+"</td>";
					str += "<td>"+data.SList[i].hits+"</td>";
					str += "</tr>"
				})
				str += paging(data.sp)
				str += "</table>";
				$("#selTab").html(str);
			},
			error: function (jqXHR, Status, error){
				console.log("brand Error!");
			}
		}); 
	}
	
	//페이징 함수
	function paging(sp){
		var str = '<tr><td colspan="5" align="center">';
		
		if(sp.curPage > 1){
			str += '<a href="javascript:;" class="paging">'
			str += '<input type="text" class="page" value="1" hidden="true">[처음]</a>';
		}
		
		if(sp.curPage > 1){
			str += '<a href="javascript:;" class="paging">'
			str += '<input type="text" class="page" value="'+sp.prevPage+'" hidden="true">[이전]</a>';
		}
		
		for(var i=sp.blockBegin ; i<=sp.blockEnd ; i++ ){
			if(i == sp.curPage){
				str += '<span style="color: red">'+i+'</span>&nbsp;'
			}else{
				
				str += '<a href="javascript:;" class="paging">'
    			str += '<input type="text" class="page" value="'+i+'" hidden="true" target="">'+i+'</a>&nbsp;';
    			
			}
		}
		
		if(sp.curBlock <= sp.totalBlock){
			str += '<a href="javascript:;" class="paging">'
			str += '<input type="text" class="page" value="'+sp.nextPage+'" hidden="true">[다음]</a>';
		}
		
		if(sp.curPage <= sp.totalPage){
			str += '<a href="javascript:;" class="paging">'
			str += '<input type="text" class="page" value="'+sp.totalPage+'" hidden="true">[끝]</a>';
	
		}
		str += '</td></tr>';
		return str;
	}

$(document).on("click",".paging",function(){
		
		page = $(this).find(".page").val();
		console.log(page)
		SPostList(page);
		
	})
});
$(document).on("click","#detail",function(){
	
	spost_No = ($(this).find(".detailText").val());
	
	location.href="managerSPostDetail?spost_No="+spost_No;
		
})
</script>
<%@include file="managerHeader.jsp"%>
<div id="contnet" align="center">
	<table id ="selTab">
		<tr>
			<th>글번호</th>
			<th>상품유형</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
	</table>
</div>
</body>
</html>