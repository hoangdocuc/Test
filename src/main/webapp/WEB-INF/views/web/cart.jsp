<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="com.hoangdocuc.util.SecurityUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
</head>
<body>
	<!-- Page info -->
	<div class="page-top-info">
		<div class="container">
			<h4>Your cart</h4>
			<div class="site-pagination">
				<a href="/trang-chu">Trang chủ</a> /
				<a href="/gio-hang">Giỏ Hàng</a>
			</div>
		</div>
	</div>
	<!-- Page info end -->
	
	<!-- cart section end -->
	<section class="cart-section spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-9">
					<div class="cart-table">
						<h3>Giỏ hàng</h3>
						<div class="cart-table-warp">
							<table>
							<thead>
								<tr>
									<th class="product-th">Tên sản phẩm</th>
									<th class="quy-th">Số lượng</th>
									<th class="size-th">Số lượng còn</th>
									<th class="total-th">Giá</th>
									<th> Hành động </th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${model }">
								<form action="/gio-hang/sua" method="get">
									<tr>
										<td class="product-col">
											<img width="200px" height="100px" src="${pageContext.request.contextPath }/image/${item.productImage }"/>
											<div class="pc-title">
												<h4>${item.productName }</h4>
												<p>${item.productPrice }<u><i>vnđ</i></u></p>
											</div>
										</td>
										<td class="quy-col">
											<div class="quantity">
						                        <div class="pro-qty">
													<input type="text" name="quantity" value="${item.quantity }">
												</div>
	                    					</div>
										</td>
										<td class="size-col" id="productQuantity"><h4>${item.productQuantity }</h4></td>
										<td class="total-col"><h4>${item.productPrice }<u><i>vnđ</i></u></h4></td>
										<td>
											<c:url var="updateCartURL" value="/gio-hang/sua">
												<c:param name="productId" value="${item.productId}"/>
											</c:url>
											<input type="hidden" name="productId" value="${item.productId }">
											<button class="btn btn-info" data-toggle="tooltip"
												title="Cập nhật giỏ hàng" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>Cập nhật
											</button>
											<c:url var="deleteCartURL" value="/gio-hang/xoa">
												<c:param name="productId" value="${item.productId}"/>									
											</c:url>
											<a class="btn btn-danger" data-toggle="tooltip"
												title="Xóa" href='${deleteCartURL}'><i class="ace-icon fa fa-check bigger-110"></i>Xóa
											</a>
										</td>
									</tr>
								</form>
								</c:forEach>
								
							</tbody>
						</table>
						</div>
						<div class="total-cost">
							<h6>Tổng tiền <span>${total } </span><u><i">vnđ</i></u></h6>
						</div>
					</div>
				</div>
				<div class="col-lg-3 card-right">
					<security:authorize access = "isAuthenticated()">
						<c:url var="checkoutURL" value="/dat-hang">
							<c:param name="username" value="<%=SecurityUtils.getPrincipal().getUsername()%>"/>															
						</c:url>
					</security:authorize>
					<security:authorize access = "isAnonymous()">
						<c:url var="checkoutURL" value="/dang-nhap">
							<c:param name="cart" value="no username"/>															
						</c:url>
					</security:authorize>
					<a href="${checkoutURL }" class="site-btn">Đặt hàng</a>
					<a href="/danh-muc" class="site-btn sb-dark">>>>Về Danh sách sản phẩm</a>
				</div>
			</div>
		</div>
	</section>
	<!-- cart section end -->
	
	<!-- Related product section -->
	<section class="related-product-section">
		<div class="container">
			<div class="section-title text-uppercase">
				<h2>Tiếp tục mua sắm</h2>
			</div>
			<div class="row">
				<c:forEach var="item" items="${modelbyCreatedDate.listResult}">
					<div class="col-lg-3 col-sm-6">
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
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Related product section end -->
</body>
</html>