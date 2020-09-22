<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="userURL" value="/quan-tri/tai-khoan" />
<c:url var="edituserURL" value="/quan-tri/tai-khoan/chinh-sua" />
<c:url var="userAPI" value="/api/tai-khoan" />
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

				<li><a href="<c:url value='/quan-tri/tai-khoan?page=1&limit=2'/>">Tài Khoản</a></li>
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
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Quyền </label>
							<div class="col-sm-9">
								<form:select path="roleCode" id="roleCode">
									<form:options items="${roles }" />
								</form:select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tên Tài Khoản </label>
							<div class="col-sm-9">
								<form:input path="username" cssClass="col-xs-10 col-sm-5" id="username"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Mật Khẩu(định dạng MD5) </label>
							<div class="col-sm-9">
								<form:input path="password" cssClass="col-xs-10 col-sm-5" id="password" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Email </label>
							<div class="col-sm-9">
								<form:input path="email" cssClass="col-xs-10 col-sm-5" id="email" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tên đầy đủ </label>
							<div class="col-sm-9">
								<form:input path="fullname" cssClass="col-xs-10 col-sm-5" id="fullname" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Giới tính </label>
							<div class="col-sm-9">
								<form:input path="gender" cssClass="col-xs-10 col-sm-5" id="gender" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Địa chỉ </label>
							<div class="col-sm-9">
								<form:input path="address" cssClass="col-xs-10 col-sm-5" id="address" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Số điện thoại </label>
							<div class="col-sm-9">
								<form:input path="phone" type="number" cssClass="col-xs-10 col-sm-5" id="phone" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Ghi chú </label>
							<div class="col-sm-9">
								<form:input path="note" cssClass="col-xs-10 col-sm-5" id="note" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Trạng thái(0 hoặc 1)</label>
							<div class="col-sm-9">
								<form:input path="status" type="number" cssClass="col-xs-10 col-sm-5" id="status" />
							</div>
						</div>
						<form:hidden path="id" id="userId"/>
						
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<c:if test="${not empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateUser">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Cập nhật Tài Khoản
									</button>
								</c:if>
								<c:if test="${empty model.id }">
									<button class="btn btn-info" type="button" id="btnAddOrUpdateUser">
										<i class="ace-icon fa fa-check bigger-110"></i>
										Thêm Tài Khoản
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
	$('#btnAddOrUpdateUser').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
		var id = $('#userId').val();
		$.each(formData, function (i, v) {
			data[""+v.name+""] = v.value;
		});
		if(id == "")
		{
			addUser(data);
		} else {
			updateUser(data);
		}
	    console.log(formData);
	});
	function addUser(data){
		$.ajax({
            url: '${userAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${edituserURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${userURL}?page=1&limit=2&message=error_system";
            }
        });
	}
	
	function updateUser(data){
		$.ajax({
            url: '${userAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${edituserURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${userURL}?page=1&limit=2&message=error_system";
            }
        });
	}

</script>
</body>
</html>
