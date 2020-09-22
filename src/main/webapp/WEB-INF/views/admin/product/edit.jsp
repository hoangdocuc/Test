<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="productURL" value="/quan-tri/san-pham" />
<c:url var="editproductURL" value="/quan-tri/san-pham/chinh-sua" />
<c:url var="productAPI" value="/api/san-pham" />
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

				<li><a href="<c:url value='/quan-tri/san-pham?page=1&limit=2'/>">Sản phẩm</a></li>
				<li class="active">Elements</li>
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
							<label for="categoryCode" class="col-sm-3 control-label no-padding-right">Thể loại:</label>
							<div class="col-sm-9">
								<form:select path="categoryCode" id="categoryCode">
									<form:option value="" label="-- Chọn thể loại --" />
									<form:options items="${categories }" />
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tên sản phẩm </label>
							<div class="col-sm-9">
								<form:input path="name" cssClass="col-xs-10 col-sm-5" id="name"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Hình ảnh </label>
							<div class="col-sm-9">
								<input type="file" path="image" accept="image/*" cssClass="col-xs-10 col-sm-5" id="image" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Kích thước(định dạng: "11,22,33") </label>
							<div class="col-sm-9">
								<form:input path="size" cssClass="col-xs-10 col-sm-5" id="size" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1" > Số lượng </label>
							<div class="col-sm-9">
								<form:input type="number" path="quantity" cssClass="col-xs-10 col-sm-5" id="quantity" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Thông tin </label>
							<div class="col-sm-9">
								<form:input path="information" cssClass="col-xs-10 col-sm-5" id="information" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Giá </label>
							<div class="col-sm-9">
								<form:input type="number" path="price" cssClass="col-xs-10 col-sm-5" id="price" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Lượt xem </label>
							<div class="col-sm-9">
								<form:input type="number" path="view" cssClass="col-xs-10 col-sm-5" id="view" />
							</div>
						</div>
						<form:hidden path="id" id="productId"/>
						
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${not empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateProduct">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật Sản phẩm
									</button>
								</c:if>
								<c:if test="${empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateProduct">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm Sản phẩm
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
	$('#btnAddOrUpdateProduct').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
		var files = $('#image')[0].files[0];
		var id = $('#productId').val();
		$.each(formData, function (i, v) {
			data[""+v.name+""] = v.value;
		});
		if(files != undefined){
			var reader = new FileReader();
			reader.onload = function(e) {
				data["base64"]=e.target.result;
				data["image"]=files.name;	
				if(id == "")
				{
					addProduct(data);
				} else {
					updateProduct(data);
				}
			};
			reader.readAsDataURL(files);
		}
	    console.log(formData);
	});
	function addProduct(data){
		$.ajax({
            url: '${productAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editproductURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${productURL}?page=1&limit=2&message=insert_success";
            }
        });
	}
	
	function updateProduct(data){
		$.ajax({
            url: '${productAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editproductURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${productURL}?page=1&limit=2&message=insert_success";
            }
        });
	}

</script>
</body>
</html>
