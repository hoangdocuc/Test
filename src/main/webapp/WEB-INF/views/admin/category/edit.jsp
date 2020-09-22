<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="categoryURL" value="/quan-tri/danh-muc" />
<c:url var="editcategoryURL" value="/quan-tri/danh-muc/chinh-sua" />
<c:url var="categoryAPI" value="/api/danh-muc" />
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

				<li><a href="<c:url value='/quan-tri/danh-muc?page=1&limit=2'/>">Danh mục</a></li>
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
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tên danh mục </label>
							<div class="col-sm-9">
								<form:input path="name" cssClass="col-xs-10 col-sm-5" id="name"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Code danh mục(dịnh dạng: quan-ao-nam) </label>
							<div class="col-sm-9">
								<form:input path="code" cssClass="col-xs-10 col-sm-5" id="code" />
							</div>
						</div>
						<form:hidden path="id" id="categoryId"/>
						
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${not empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateCategory">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật Danh mục
									</button>
								</c:if>
								<c:if test="${empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateCategory">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm Danh mục
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
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$('#btnAddOrUpdateCategory').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
		var id = $('#categoryId').val();
		$.each(formData, function (i, v) {
			data[""+v.name+""] = v.value;
		});
		if(id == "")
		{
			addCategory(data);
		} else {
			updateCategory(data);
		}
	    console.log(formData);
	});
	function addCategory(data){
		$.ajax({
            url: '${categoryAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editcategoryURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${categoryURL}?page=1&limit=2&message=error_system";
            }
        });
	}
	
	function updateCategory(data){
		$.ajax({
            url: '${categoryAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editcategoryURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${categoryURL}?page=1&limit=2&message=error_system";
            }
        });
	}

</script>
</body>
</html>
