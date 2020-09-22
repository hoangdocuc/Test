<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
</head>
<body>
	<div class="page-top-info">
		<div class="container">
			<h4>Sản phẩm</h4>
			<div class="site-pagination">
				<a href="/trang-chu">Trang chủ</a> /
				<a href="/danh-muc">Danh mục</a> /
			</div>
		</div>
	</div>
	<!-- Page info end -->
	<!-- product section -->
	<section class="product-section">
		<div class="container">
			<div class="back-link">
				<a href="/danh-muc"> &lt;&lt; Quay về danh mục</a>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="">
						<img class="product-big-img" src="${pageContext.request.contextPath }/image/${model.image}" alt="">
					</div>
				</div>
				<div class="col-lg-6 product-details">
					<h2 class="p-title">${model.name }</h2>
					<h3 class="p-price">${model.price} <u><i>vnđ</i></u></h3>
					
					<div class="fw-size-choose">
						<p>Size</p>
						<c:forTokens items="${model.size }" delims="," var="name">
							<div class="sc-item">
								<input type="radio">
								<label><c:out value = "${name}"/></label>
							</div>
						</c:forTokens>
					</div>
					<div class="quantity">
						<p>Số lượng</p>
                        <div>${model.quantity }</div>
                    </div>
					<a href="#" class="site-btn">Thêm vào giỏ</a>
					<div id="accordion" class="accordion-area">
						<div class="panel">
							<div class="panel-header" id="headingOne">
								<button class="panel-link active" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">Thông tin</button>
							</div>
							<div id="collapse1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
								<div class="panel-body">
									<p>${model.information }</p>
								</div>
							</div>
						</div>
					</div>
					<div class="quantity">
						<p>Lượt xem</p>
                        <div>${model.view }</div>
                    </div>
				</div>
			</div>
		</div>
	</section>
	<!-- product section end -->


	<!-- RELATED PRODUCTS section -->
	<section class="related-product-section">
		<div class="container">
			<div class="section-title">
				<h2>Sản phẩm mới</h2>
			</div>
			<div class="product-slider owl-carousel">
				<c:forEach var="item" items="${modelbyCreatedDate.listResult}">
					<div class="product-item">
						<div class="pi-pic">
							<img src="${pageContext.request.contextPath }/image/${item.image}"/>
							<div class="pi-links">
								<c:url var="productURL" value="/san-pham">
									<c:param name="id" value="${item.id}"/>															
								</c:url>
								<c:url var="addCartURL" value="/gio-hang/them">
									<c:param name="id" value="${item.id}"/>															
								</c:url>
								<a href="${addCartURL }" class="add-card"><i class="flaticon-bag"></i><span>Thêm vào Giỏ</span></a>
								<a href="${productURL }" class="add-card"><i class="flaticon-heart"></i><span>Xem Chi Tiết</span></a>
							</div>
						</div>
						<div class="pi-text">
							<h6>${item.price} <u><i>vnđ</i></u></h6>
							<p>${item.name} </p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- RELATED PRODUCTS section end -->
</body>
</html>