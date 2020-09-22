<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
	<title>Divisima | eCommerce Template</title>
</head>
<body>
	<!-- Hero section -->
	<section class="hero-section">
		<div class="hero-slider owl-carousel">
			<div class="hs-item set-bg" data-setbg="<c:url value='/template/web/img/bg.jpg'/>">
				<div class="container">
					<div class="row">
						<div class="col-xl-6 col-lg-7 text-white">
							<span>New Arrivals</span>
							<h2>denim jackets</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis ipsum sus-pendisse ultrices gravida. Risus commodo viverra maecenas accumsan lacus vel facilisis. </p>
							
						</div>
					</div>
					<div class="offer-card text-white">
						<span>from</span>
						<h2>$29</h2>
						<p>SHOP NOW</p>
					</div>
				</div>
			</div>
			<div class="hs-item set-bg" data-setbg="<c:url value='/template/web/img/bg-2.jpg'/>" >
				<div class="container">
					<div class="row">
						<div class="col-xl-6 col-lg-7 text-white">
							<span>New Arrivals</span>
							<h2>denim jackets</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis ipsum sus-pendisse ultrices gravida. Risus commodo viverra maecenas accumsan lacus vel facilisis. </p>
							
						</div>
					</div>
					<div class="offer-card text-white">
						<span>from</span>
						<h2>$29</h2>
						<p>SHOP NOW</p>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="slide-num-holder" id="snh-1"></div>
		</div>
	</section>
	<!-- Hero section end -->



	<!-- Features section -->
	<section class="features-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 p-0 feature">
					<div class="feature-inner">
						<div class="feature-icon">
							<img src="<c:url value='/template/web/img/icons/1.png'/>" alt="#">
						</div>
						<h2>Thanh toán đơn giản</h2>
					</div>
				</div>
				<div class="col-md-4 p-0 feature">
					<div class="feature-inner">
						<div class="feature-icon">
							<img src="<c:url value='/template/web/img/icons/2.png'/>" alt="#">
						</div>
						<h2>Kiểm tra hàng trước</h2>
					</div>
				</div>
				<div class="col-md-4 p-0 feature">
					<div class="feature-inner">
						<div class="feature-icon">
							<img src="<c:url value='/template/web/img/icons/3.png'/>" alt="#">
						</div>
						<h2>Vận chuyển nhanh</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Features section end -->


	<!-- letest product section -->
	<section class="top-letest-product-section">
		<div class="container">
			<div class="section-title">
				<h2>Xem nhiều nhất</h2>
			</div>
			<div class="product-slider owl-carousel">
				<c:forEach var="item" items="${modelbyView.listResult}">
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
	<!-- letest product section end -->



	<!-- Product filter section -->
	<section class="product-filter-section">
		<div class="container">
			<div class="section-title">
				<h2>Sản phẩm mới nhất</h2>
			</div>
			<ul class="product-filter-menu">
				<c:forEach var="item" items="${modelCategory.listResult}">
					<li style="width: 169px;"><a href="#">${item.name}</a></li>
				</c:forEach>
			</ul>
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
								<p>${item.createdDate} </p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Product filter section end -->


	<!-- Banner section -->
	<section class="banner-section">
		<div class="container">
			<div class="banner set-bg" data-setbg="<c:url value='/template/web/img/banner-bg.jpg'/>" >
				<div class="tag-new">Mới</div>
				<span>Mẫu mới</span>
				<h2>Áo sơ mi</h2>
				<a href="/danh-muc" class="site-btn">SHOP NOW</a>
			</div>
		</div>
	</section>
	<!-- Banner section end  -->
</body>
</html>
