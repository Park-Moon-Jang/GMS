<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
	
<title>item list page</title>
<script>

$(document).ready(function(){
	
	companySel();
	categorySel();
	
	function companySel(){
		
		$.ajax({
			type:"POST",
			url:"/app/manager/companySel",
			success:function(data){
				$("#company").find("option").remove().end().append("<option value=''>생산업체</option>");
				$.each(data, function(i){
					console.log(data[i].company_No)
					
					$("#company").append("<option value='"+data[i].company_No+"'>"+data[i].company_Name+"</option>")
					
				})
			},
			error: function (jqXHR, Status, error){
				console.log("company Error!");
			}
		});
	}
	
	function categorySel(){
		
		$.ajax({
			
			type:"post",
			url:"/app/manager/categorySel",
			success:function(data){
				
				$("#category").find("option").remove().end().append("<option value=''>상품유형</option>");
				$.each(data, function(i){
					console.log(data[i].category_no)
					$("#category").append("<option value='"+data[i].category_No+"'>"+data[i].category_Name+"</option>")
				})
			},
			error: function (jqXHR, Status, error){
				console.log("category Error!");
			}
			
		});
	}

})
$(document).on("click","#register",function(url){
	
	window.open("${pageContext.servletContext.contextPath}/manager/viewitemregister","", "width=600, height=400");
})

$(document).on("click",".itemUpdate", function(){
	var item_No = $(this).find(".item_No").val();
	window.open("${pageContext.servletContext.contextPath}/manager/viewitemupdate?item_No="+item_No,"", "width=600, height=400");
})

$(document).on("click",".itemDelete", function(){
	var item_No = $(this).find(".item_No").val();
	var item = {"item_No": item_No};
	
	var answer = confirm("정말로 삭제하시겠습니까?");
	if(answer == true){
		
		$.ajax({
			
			type:"post",
			url:"/app/manager/itemdelete",
			dataType:"json",
			data:item,
			success: function(data){
				
				alert("입고정보가 삭제되었습니다");
				selBtn(1);
			},
			error: function (jqXHR, Status, error){
				console.log("delete Error!");
			}

		});
		
	}else{
		return;
	}
})
function selBtn(curPage){
	
	var company_No = $("#company").val();
	var category_No = $("#category").val();
	var from_Date = $("#from_Date").val();
	var to_Date = $("#to_Date").val();
	var item_Name = $("#item_Name").val();
	
	function my_curr_date() {      
	    var currentDate = new Date()
	    var day = currentDate.getDate();
	    var month = currentDate.getMonth() + 1;
	    var year = currentDate.getFullYear();
	    var my_date = year+"-"+ month +"-"+day;
	    
	    return my_date;
	}
	
	if(!company_No){

		company_No = 0;
		}
	if(!category_No){

		category_No = 0;
	}
	if(!from_Date){

		from_Date = "2017-01-01";
	}
	if(!to_Date){

		to_Date = my_curr_date();
	}
	if(item_Name =="" || item_Name == null || item_Name == '' ){
		item_Name = null;
		
	}
	
	var source = {"item_Name":item_Name, "company_No":company_No, "category_No":category_No, "from_Date":from_Date, "to_Date":to_Date, "curPage":curPage};

	
	var values = []; //ArrayList 값을 받을 변수를 선언

	$.ajax({
		
		type:"post",
		url:"/app/manager/itemlist",
		dataType:"json",
		data: source,
		
		success: function(data){
			alert("success");
			var str = '<table id="itemTab"><tr><td>상품명</td><td>생산업체 명</td><td>카테고리 명</td><td>수량</td><td>입고일</td><td>비고</td></tr>';
			
			values = data.list;
			
			$.each(values, function(i){
				
				str += "<tr id='"+i+"'>"
				str += "<td>"+values[i].item_Name+"</td>";
				str += "<td>"+values[i].company_Name+"</td>";
				str += "<td>"+values[i].category_Name+"</td>";
				str += "<td>"+values[i].amount+"</td>";				
				str += "<td>"+moment(values[i].carry_Date).format('YYYY-MM-DD')+"</td>";
				str += "<td>" + "<td><a href='javscript:;' class= 'itemUpdate'><input type='button' class='item_No' hidden='hidden' value= '"+values[i].item_No+"'>" +"변경" + "</td>";
				str += "<td>" + "<td><a href='javscript:;' class= 'itemDelete'><input type='button' class='item_No' hidden='hidden' value= '"+values[i].item_No+"'>" +"삭제" + "</td>";
				str += "</tr>"
									
			})
			str += paging(data.sp);
			
			str += "</table>";
			
			$("#itemTab").html(str);
		},
		error: function (jqXHR, Status, error){
			console.log("list Error!");
		}
		
		
	});
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
}

$(document).on("click",".paging",function(){
	
	page = $(this).find(".page").val();
	console.log(page)
	selBtn(page)
	
})

</script>

</head>

<%@include file="managerHeader.jsp"%>


<body>
<p>item stored page</p>

	<div id="content">
		<table>
			<thead>
				<tr>
					<th corspan="5">입고관리</th>
				</tr>
				
				<tr>
					<td>
						<input type="button" id="register" value = "입고등록" >
					</td>
				</tr>
				<tr>
		
					<td>
						<select id="company">
							<option>생산업체</option>
						</select>
					</td>
					<td>
						<select id="category">
							<option>상품유형</option>
						</select>
					</td>
					<td>	
						<input type="date" id="from_Date" >
					</td>
					<td>	
						<input type="date" id="to_Date" >
					</td>
					<td>	
						<input type="text" id="item_Name">
					</td>
					<td>	
						<button id="selBtn" onclick="selBtn()">검색</button>
					</td>
				</tr>
			</thead>
			<tbody>
				<table id="itemTab">
					<tr>
								<td>상품명</td>
								<td>생산업체 명</td>
								<td>카테고리 명</td>
								<td>수량</td>
								<td>입고일</td>
								<td>비고</td>
					</tr>
				</table>
			</tbody>
			
		</table>
	</div>

</body>
</html>