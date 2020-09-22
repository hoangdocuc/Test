<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="com.hoangdocuc.util.SecurityUtils" %>
<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
<!-- Header section -->
	<header class="header-section">
		<div class="header-top">
			<div class="container">
				<div class="row">
					<div class="col-lg-2 text-center text-lg-left">
						<!-- logo -->
						<a href="/trang-chu" class="site-logo">
							<img src="<c:url value='/template/web/img/logo.png'/>" alt="">
						</a>
					</div>
					<div class="col-xl-6 col-lg-5">
						<form class="header-search-form" action="/danh-muc">
							<input id="name" name="name" type="text" placeholder="Search on divisima ....">
							<button type="submit"><i class="flaticon-search"></i></button>
						</form>
					</div>
					<div class="col-xl-4 col-lg-5">
						<div class="user-panel">
							<security:authorize access = "isAnonymous()">
								<div class="up-item">
									<i class="flaticon-profile"></i>
									<a style="color: blue" href="/dang-nhap">Đăng nhập </a>  hoặc <a style="color: blue" href="/dang-nhap">Đăng ký</a>
								</div>
							</security:authorize>
							<security:authorize access = "isAuthenticated()">
								<div class="up-item">
									<i class="flaticon-profile"></i>
									<a style="color: blue" href="/dang-nhap">Chào <%=SecurityUtils.getPrincipal().getFullname()%></a>  <a style="color: red" href="<c:url value='/thoat'/>">Thoát</a>
								</div>
							</security:authorize>
							<div class="up-item">
								<div class="shopping-card">
									<i class="flaticon-bag"></i>
								</div>
								<a style="color: blue" href="/gio-hang">Giỏ hàng</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<nav class="main-navbar">
			<div class="container">
				<!-- menu -->
				<ul class="main-menu">
					<li><a href="/trang-chu">Trang chủ</a></li>
					<c:forEach var="item" items="${modelCategory4.listResult}">
						<c:url var="categoryURLs" value="/danh-muc">
							<c:param name="id" value="${item.id}"/>															
						</c:url>
					<li><a href="${categoryURLs}">${item.name}</a></li>
					</c:forEach>
					<li><a href="/danh-muc">Danh mục</a>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- Header section end -->
