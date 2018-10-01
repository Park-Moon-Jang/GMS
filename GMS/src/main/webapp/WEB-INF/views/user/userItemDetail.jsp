<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<style>
#comentSubmit {
	width: 100;
	height: 70px;
}
</style>
	<script>
		$(document)
				.ready(
						function() {
							itemDetailSel();
							selectComent(1);

							function photoSel() {

							}

							function selectComent(curPage) {
								$
										.ajax({
											type : "POST",
											url : "/app/user/comentSel",
											data : {
												"curPage" : curPage
											},
											success : function(data) {
												var str = '<table id="comentTable"><tr><th colspan="3" align="left">���'
														+ data.totalCount
														+ '��</th></tr>';
												$
														.each(
																data.CList,
																function(i) {
																	str += "<tr id='"+i+"'>"
																	str += "<td>"
																			+ data.CList[i].user_NickName
																			+ "</td>";
																	str += '<td>'
																			+ moment(
																					data.CList[i].write_Date)
																					.format(
																							"YYYY-MM-DD. hh:mm")
																			+ '</td>'
																	str += '<td>';
																	if ('${session_user}' == data.CList[i].user_Id)
																		str += '<input type="text" hidden ="true" value="'+data.CList[i].coment_No+'" id="coment_No"><button id="deleteComent">����</button>';

																	str += '</td></tr><tr>'
																	str += '<td colspan="3"  height="70" width="500">'
																			+ data.CList[i].content
																			+ '</td></tr>';

																})
												str += paging(data.sp)
												str += '</table>';
												$("#comentTable").html(str);
											},
											error : function(jqXHR, Status,
													error) {
												console
														.log("selectComent Error!");
											}
										});
							}

							function itemDetailSel() {
								$.ajax({
									type : "POST",
									url : "/app/user/itemDetalSel",
									success : function(data) {

										$.each(data, function(i) {
											str = '<td id="brand">'
													+ data[i].company_Name
													+ '</td>';
											$("#brand").html(str);
											str = '<td id="category">'
													+ data[i].category_Name
													+ '</td>';
											$("#category").html(str);
											str = '<td id="item_Name">'
													+ data[i].item_Name
													+ '</td>';
											$("#item_Name").html(str);
											str = '<td id="amount">'
													+ data[i].amount + '</td>';
											$("#amount").html(str);
											str = '<td id="price">'
													+ data[i].price + '</td>';
											$("#price").html(str);
										})
									},
									error : function(jqXHR, Status, error) {
										console.log("itemDetailSel Error!");
									}
								});
							}

							function paging(sp) {
								var str = '<tr><td colspan="3" align="center">';

								if (sp.curPage > 1) {
									str += '<a href="javascript:;" class="paging">'
									str += '<input type="text" class="page" value="1" hidden="true">[ó��]</a>';
								}

								if (sp.curPage > 1) {
									str += '<a href="javascript:;" class="paging">'
									str += '<input type="text" class="page" value="'+sp.prevPage+'" hidden="true">[����]</a>';
								}

								for (var i = sp.blockBegin; i <= sp.blockEnd; i++) {
									if (i == sp.curPage) {
										str += '<span style="color: red">' + i
												+ '</span>&nbsp;'
									} else {

										str += '<a href="javascript:;" class="paging">'
										str += '<input type="text" class="page" value="'+i+'" hidden="true" target="">'
												+ i + '</a>&nbsp;';

									}
								}

								if (sp.curBlock <= sp.totalBlock) {
									str += '<a href="javascript:;" class="paging">'
									str += '<input type="text" class="page" value="'+sp.nextPage+'" hidden="true">[����]</a>';
								}

								if (sp.curPage <= sp.totalPage) {
									str += '<a href="javascript:;" class="paging">'
									str += '<input type="text" class="page" value="'+sp.totalPage+'" hidden="true">[��]</a>';

								}
								str += '</td></tr>';
								return str;
							}
							$(document).on("click", ".paging", function() {

								page = $(this).find(".page").val();
								console.log(page)
								selectComent(page);

							})

							$(document)
									.on(
											"click",
											"#comentSubmit",
											function() {
												var coment = $("#coment").val()

												$
														.ajax({
															type : "POST",
															url : "/app/user/insertComent",
															data : {
																"coment" : coment
															},
															success : function(
																	data) {
																$("#coment")
																		.val("");
																selectComent(1);
															},
															error : function(
																	jqXHR,
																	Status,
																	error) {
																console
																		.log("insertScrap Error!");
															}
														});
											});

							$(document)
									.on(
											"click",
											"#deleteComent",
											function() {
												var coment_No = $("#coment_No")
														.val()
												$
														.ajax({
															type : "POST",
															url : "/app/user/deleteComent",
															data : {
																"coment_No" : coment_No
															},
															success : function(
																	data) {
																alert("��� ����");
																selectComent(1);
															},
															error : function(
																	jqXHR,
																	Status,
																	error) {
																console
																		.log("deleteComent Error!");
															}
														});
											});
						})

		function insertScrap() {
			$.ajax({
				type : "POST",
				url : "/app/user/insertScrap",
				success : function(data) {
					alert("��ũ�� �߰�!")
					location.reload();
				},
				error : function(jqXHR, Status, error) {
					console.log("insertScrap Error!");
				}
			});
		}

		function deleteScrap() {
			$.ajax({
				type : "POST",
				url : "/app/user/deleteScrap",
				success : function(data) {
					alert("��ũ�� ����!")
					location.reload();
				},
				error : function(jqXHR, Status, error) {
					console.log("deleteScrap Error!");
				}
			});

		}
	</script>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<div id="content" align="center">

		<table>
			<tr>
				<th colspan="3">detail</th>
				<th><c:choose>
						<c:when test="${session_scrape == 'false' }">
							<a href="javascript:;" onclick="insertScrap()">��</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" onclick="deleteScrap()">��</a>
						</c:otherwise>
					</c:choose> &nbsp;
					<button onclick="window.close()">�ݱ�</button></th>
			</tr>
			<tr>
				<td colspan="2" rowspan="6" id="itemImg">
				<c:if test="${!empty list}">
				<c:forEach var="vo" items="${list}">
				<c:choose>
					<c:when test="${!empty vo}">
						<img src="data:image/png;base64,${vo.photo_Data}" width="200" height="200">
					</c:when>
					<c:otherwise>
					<img src="" width="200" height="200">
					</c:otherwise>	
				</c:choose>
					</c:forEach>
				</c:if>	
				<c:if test="${empty list}">
					<img src="" width="200" height="200">
				</c:if>
					</td>
				<td colspan="2">��ǰ����</td>
			</tr>
			<tr>
				<td>�귣��</td>
				<td id="brand"></td>
			</tr>
			<tr>
				<td>��ǰ����</td>
				<td id="category"></td>
			</tr>
			<tr>
				<td>��ǰ��</td>
				<td id="item_Name"></td>
			</tr>
			<tr>
				<td>����</td>
				<td id="amount"></td>
			</tr>
			<tr>
				<td>����</td>
				<td id="price"></td>
			</tr>
		</table>
		<table id="comentTable">
			<tr>
				<th colspan="3" align="left">���</th>
			</tr>
		</table>
		<table>
			<tr>
				<td><textarea rows="4" cols="100" id="coment"></textarea></td>
				<td><button id="comentSubmit">���</button></td>
			</tr>
		</table>
	</div>
	<jsp:include page="userFooter.jsp"></jsp:include>
</body>
</html>