<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="billURL" value="/quan-tri/hoa-don" />
<c:url var="editbillURL" value="/quan-tri/hoa-don/chinh-sua" />
<c:url var="billAPI" value="/api/hoa-don" />
<c:url var="billDetailAPI" value="/api/chi-tiet-hoa-don" />
<html>
<head>
<title>Chỉnh sửa sản phẩm</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/quan-tri/trang-chu'/>">Trang chủ</a>
				</li>

				<li><a href="<c:url value='/quan-tri/hoa-don?page=1&limit=2'/>">Hóa Đơn</a></li>
				<li class="active">Element</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<c:if test="${not empty message}">
						<div class="alert alert-${alert }">
						  	${message }
						</div>
					</c:if>
					<form:form id="formSubmit" class="form-horizontal" role="form" modelAttribute="model" >
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Ghi chú </label>
							<div class="col-sm-9">
								<form:input path="note" cssClass="col-xs-10 col-sm-5" id="note"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Trạng Thái </label>
							<div class="col-sm-9">
								<form:input path="status" cssClass="col-xs-10 col-sm-5" id="status" />
							</div>
						</div>
						</div>
						<form:hidden path="userId" id="userId"/>
						<form:hidden path="id" id="billId"/>
						
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${not empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateBill">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật Hóa Đơn
									</button>
								</c:if>
								<c:if test="${empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateBill">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm Hóa Đơn
									</button>
								</c:if>
								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i>
									Hủy
								</button>
							</div>
						</div>
					</form:form>
					
					<!-- Hien thi san pham -->
					
					<h3><i style="color:blue;">Các Sản Phẩm trong Hóa Đơn </i></h3>
					<form action="" id="formSubmit2" method="get">
						<div class="main-content-inner">
							<div class="page-content">
								<div class="row">
									<div class="col-xs-12">
										<c:if test="${not empty message}">
											<div class="alert alert-${alert}">
					  							${message}
											</div>
										</c:if>
										<div class="widget-box table-filter">
											<div class="table-btn-controls">
												<div class="pull-right tableTools-container">
													<div class="dt-buttons btn-overlap btn-group">
														
														<button id="btnDelete" type="button" onclick="warningBeforeDelete()"
																class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																		<span>
																			<i class="fa fa-trash-o bigger-110 pink"></i>
																		</span>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12">
												<div class="table-responsive">
													<table class="table table-bordered">
														<thead>
															<tr>
																<th><input type="checkbox" id="checkAll"></th>
																<th>Số lượng</th>
																<th>Kích thước (size)</th>
																<th>Mã Sản Phẩm</th>
																<th>Tên Sản Phẩm</th>
																<th>Hình ảnh</th>
																<th>Người tạo</th>
																<th>Ngày tạo</th>
																<th>Ngày sửa</th>
																<th>Người sửa</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="item" items="${modelBillDetail.listResult}">
																<tr>
																	<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
																	<td>${item.quantity}</td>
																	<td>${item.size}</td>
																	<td>${item.productId}</td>
																	<td>${item.productName}</td>
																	<td><img width="200px" height="100px" src="${pageContext.request.contextPath }/image/${item.productImage}"/>
															${item.productImage}</td>
																	<td>${item.createdDate}</td>
																	<td>${item.createdBy}</td>
																	<td>${item.modifiedDate}</td>
																	<td>${item.modifiedBy}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>								
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
				   </form>					
			</div>
		</div>
	</div>
</div>
<script>
	$('#btnAddOrUpdateBill').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
		var id = $('#billId').val();
		$.each(formData, function (i, v) {
			data[""+v.name+""] = v.value;
		});
		if(id == "")
		{
			addBill(data);
		} else {
			updateBill(data);
		}
	    console.log(formData);
	});
	function addBill(data){
		$.ajax({
            url: '${billAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editbillURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${billURL}?page=1&limit=2&message=error_system";
            }
        });
	}
	
	function updateBill(data){
		$.ajax({
            url: '${billAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editbillURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${billURL}?page=1&limit=2&message=error_system";
            }
        });
	}
	function warningBeforeDelete() {
		 swal({
			  title: "Xác nhận xóa",
			  text: "Bạn có chắc chắn muốn xóa hay không",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {
			  if (isConfirm) {
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
			            return $(this).val();
			        }).get();
					deleteBillDetail(ids);
			  }
			});
	 }
	 function deleteBillDetail(data) {
		 var id2 = $('#billId').val();
	        $.ajax({
	            url: '${billDetailAPI}',
	            type: 'DELETE',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            success: function (result) {
	                window.location.href = "${editbillURL}?id="+id2+"&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href ="${editbillURL}?page=1&limit=2&message=error_system";
	            }
	        });
	 }
</script>
</body>
</html>
