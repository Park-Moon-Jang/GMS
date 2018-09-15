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
	scrapSel(1);
	
	function scrapSel(curPage){
		$.ajax({
			type:"POST",
			url:"/app/user/myScrapSel",
			dataType:"json",
			data:{"curPage":curPage},
			success: function(data){
				var str = '<table id="scrapPaging"><tr>';
				str += '<th><input type="checkbox" id="checkAll" name="checkAll" onclick="checkAll()"></th>';
				str += '<th>이미지</th><th>상품이름</th><th>브랜드</th><th>수량</th><th>가격</th><th>비고</th></tr>';
				
				$.each(data.IList, function(i){
					str += "<tr id='"+i+"'>"
					str += '<td><input type="checkbox" name="mycheck" value="'+data.IList[i].item_No+'"></td>'
					str += '<td id="img"></td>'
					str += "<td>"+data.IList[i].item_Name+"</td>";
					str += '<td>'+data.IList[i].company_Name+'</td>';
					str += "<td>"+data.IList[i].amount+"</td>";
					str += "<td>"+data.IList[i].price+"</td>";
					str += '<td><button onclick="deleteScrap('+data.IList[i].item_No+')">삭제</button></td>';
					str += "</tr>"
					
				})
				
				str += paging(data.sp)
				str += "</table>";
				$("#scrapPaging").html(str);
			},
			error: function (jqXHR, Status, error){
				console.log("myScrap Error!");
			}
		}); 
	}
	
	//페이징 함수
	function paging(sp){
		var str = '<tr><td colspan="4">';
		
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
		scrapSel(page)
		
		
	})
})


function checkAll(){
	if($("input[name=checkAll]").prop("checked")){
		$("input[name=mycheck]").prop('checked', true);
	}else{
		$("input[name=mycheck]").prop('checked', false);
	}
}

function deleteScrap(item_No){
	var source = {"item_No":item_No};
	$.ajax({
		type:"POST",
		url:"/app/user/deleteMyScrap",
		dataType:"json",
		data:source,
		success: function(data){
			alert(item_No+"스크랩 삭제!")
			location.reload();
		},
		error: function (jqXHR, Status, error){
			console.log("deleteMyScrap Error!");
		}
	}); 
}
</script>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content" align="center">
		<table id="scrapPaging">
			<tr>
				<th><input type="checkbox" id="checkAll" name="checkAll" onclick="checkAll()"></th>
				<th>이미지</th>
				<th>상품이름</th>
				<th>브랜드</th>
				<th>수량</th>
				<th>가격</th>
				<th>비고</th>
			</tr>
		</table>	
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>