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


function selBtn(curPage){
	
	
	var company_No = $("#company").val();
	var category_No = $("#category").val();
	var from_Date = $("#from_date").val();
	var to_Date = $("#to_date").val();
	
	
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
	
	var source = {"company_No":company_No, "category_No":category_No, "from_Date":from_Date, "to_Date":to_Date, "curPage":curPage};

	
	var values = []; //ArrayList 값을 받을 변수를 선언

	$.ajax({
		
		type:"post",
		url:"/app/manager/itemlist",
		dataType:"json",
		data: source,
		
		success: function(data){
			alert("success");
			var str = '<table id="itemTab"><tr><td>상품명</td><td>생산업체 번호</td><td>카테고리 번호</td><td>수량</td><td>입고일</td></tr>';
			
			values = data.list;
			

			$.each(values, function(i){
				
				str += "<tr id='"+i+"'>"
				str += "<td>"+values[i].item_Name+"</td>";
				str += "<td>"+values[i].company_No+"</td>";
				str += "<td>"+values[i].category_No+"</td>";
				str += "<td>"+values[i].amount+"</td>";				
				str += "<td>"+moment(values[i].carry_Date).format('YYYY-MM-DD')+"</td>";

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
<p>item list page</p>

	<div id="content">
		<table>
			<thead>
				<tr>
					<th corspan="4" ">물품현황</th>
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
						<input type="date" id="from_date" >
					</td>
					<td>	
						<input type="date" id="to_date" >
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
								<td>생산업체 번호</td>
								<td>카테고리 번호</td>
								<td>수량</td>
								<td>입고일</td>
					</tr>
				</table>
			</tbody>
			
		</table>
	</div>

</body>
</html>