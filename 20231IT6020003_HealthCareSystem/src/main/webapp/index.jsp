<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it6020003.objects.UserObject" %>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="vi">

<head>

	<meta charset="utf-8">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<title>Bệnh Viện Đa Khoa Đan Phượng</title>

	<!-- Favicons -->
	<link type="image/x-icon" href="assets/img/favicon-32x32.png" rel="icon">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">

	<!-- Fontawesome CSS -->
	<link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
	<link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

	<!-- Main CSS -->
	<link rel="stylesheet" href="assets/css/style.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
			<script src="assets/js/html5shiv.min.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		<style>
			body > div.main-wrapper > section.section.section-search > div > div > div > a {
				background-color: #0de0fe;
				border-radius: 4px;
				color: #fff;
				display: inline-block;
				font-size: 16px;
				font-weight: 500;
				margin-top: 30px;
				min-width: 150px;
				padding: 15px 20px;
				text-align: center;
			}
			@media (min-width: 992px){
			.col-lg-8 {
				-ms-flex: 0 0 66.666667%;
				flex: 0 0 66.666667%;
				max-width: 100%;
			}}
		</style>

</head>

<body>

	<!-- Main Wrapper -->
	<div class="main-wrapper">

		<!-- Header -->
		<header class="header">
			<nav class="navbar navbar-expand-lg header-nav">
				<div class="navbar-header">
					<a id="mobile_btn" href="javascript:void(0);">
						<span class="bar-icon">
							<span></span>
							<span></span>
							<span></span>
						</span>
					</a>
					<a href="index.jsp" class="navbar-brand logo">
						<img src="assets/img/logo.png" class="img-fluid" alt="Logo">
					</a>
				</div>
				<div class="main-menu-wrapper">
					<div class="menu-header">
						<a href="index.jsp" class="menu-logo">
							<img src="assets/img/logo.png" class="img-fluid" alt="Logo">
						</a>
						<a id="menu_close" class="menu-close" href="javascript:void(0);">
							<i class="fas fa-times"></i>
						</a>
					</div>
					<ul class="main-nav">
						<li class="active">
							<a href="index.jsp">Trang chủ</a>
                        </li>
                        <li class="has-submenu">
                            <a href="#">Bệnh nhân <i class="fas fa-chevron-down"></i></a>
                            <ul class="submenu">
                                <li><a href="search.jsp">Tìm kiếm bác sĩ</a></li>
                                <li><a href="doctor-profile.html">Hồ sơ bác sĩ</a></li>
                                <li><a href="patient-dashboard.jsp">Bảng điều khiển của bệnh nhân</a></li>
                                <li><a href="favourites.html">Yêu thích</a></li>
                                <li><a href="chat.html">Trò chuyện</a></li>
                                <li><a href="profile-settings.jsp">Cài đặt hồ sơ</a></li>
                                <li><a href="change-password.html">Đổi mật khẩu</a></li>
                            </ul>
                        </li>
                        <li class="login-link">
                            <a href="sign.jsp">Đăng nhập / Đăng ký</a>
						</li>
					</ul>
					</div>
					<ul class="nav header-navbar-rht">
						<li class="nav-item contact-item">
							<div class="header-contact-img">
								<i class="far fa-hospital"></i>
							</div>
							<div class="header-contact-detail">
								<p class="contact-header">Liên hệ</p>
								<p class="contact-info-header"> 0433636050</p>
							</div>
						</li>
						<%
						// Lấy thông tin người dùng từ Session
						HttpSession userSession  = request.getSession();
						UserObject loggedInUser = (UserObject) userSession .getAttribute("loggedInUser");
						
						// Kiểm tra xem người dùng đã đăng nhập hay chưa
						if (loggedInUser == null) {
						    // Người dùng chưa đăng nhập
						%>
						    <li class="nav-item">
						        <a class="nav-link header-login" href="sign.jsp">Đăng nhập / Đăng ký</a>
						    </li>
						<%
						} else {
						    // Người dùng đã đăng nhập
						%>
						    <!-- User Menu -->
						    <li class="nav-item dropdown has-arrow logged-item">
						        <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
						            <span class="user-img">
						                <img class="rounded-circle" src="assets/img/patients/patient.jpg" width="31" alt="<%= loggedInUser.getUser_fullname() %>">
						            </span>
						        </a>
						        <div class="dropdown-menu dropdown-menu-right">
						            <div class="user-header">
						                <div class="avatar avatar-sm">
						                    <img src="assets/img/patients/patient.jpg" alt="User Image" class="avatar-img rounded-circle">
						                </div>
						                <div class="user-text">
						                    <h6><%= loggedInUser.getUser_fullname() %></h6>
						                    <p class="text-muted mb-0">Bệnh nhân</p>
						                </div>
						            </div>
						            <a class="dropdown-item" href="patient-dashboard.jsp">Dashboard</a>
						            <a class="dropdown-item" href="profile-settings.jsp">Cài đặt hồ sơ</a>
						            <a class="dropdown-item" href="LogoutControl">Đăng xuất</a>
						        </div>
						    </li>
						    <!-- /User Menu -->
							<%
							}
							%>
					</ul>
					</nav>
					</header>
					<!-- /Header -->
					
					<!-- Banner Trang chủ -->
					<section class="section section-search">
						<div class="container-fluid">
							<div class="banner-wrapper">
								<div class="banner-header text-center">
									<h1>Bệnh viện đa khoa Đan Phượng</h1>
									<p></p>
									<a href="search.jsp">Đặt lịch ngay</a>
								</div>
					<!-- Tìm kiếm -->

<!-- /Tìm kiếm -->
</div>
</div>
</section>
<!-- /Banner Trang chủ -->

<!-- Phòng khám và Chuyên môn -->
<section class="section section-specialities">
    <div class="container-fluid">
        <div class="section-header text-center">
            <h2>Phòng khám và Chuyên môn</h2>
            <p class="sub-title">Bệnh nhân đăng ký khám bệnh trực tuyến liên hệ theo số điện thoại 02433636050 để được hỗ trợ đăng ký khám</p>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-9">
                <!-- Slider -->
                <div class="specialities-slider slider">

                    <!-- Mục Slider -->
                    <div class="speicality-item text-center">
                        <div class="speicality-img">
                            <img src="assets/img/specialities/specialities-01.png" class="img-fluid"
                                alt="Chuyên môn">
                            <span><i class="fa fa-circle" aria-hidden="true"></i></span>
                        </div>
                        <p>Tiết niệu</p>
                    </div>
                    <!-- /Mục Slider -->


							<!-- Mục Slider -->
<div class="speicality-item text-center">
    <div class="speicality-img">
        <img src="assets/img/specialities/specialities-02.png" class="img-fluid" alt="Chuyên môn">
        <span><i class="fa fa-circle" aria-hidden="true"></i></span>
    </div>
    <p>Thần kinh</p>
</div>
<!-- /Mục Slider -->

<!-- Mục Slider -->
<div class="speicality-item text-center">
    <div class="speicality-img">
        <img src="assets/img/specialities/specialities-03.png" class="img-fluid" alt="Chuyên môn">
        <span><i class="fa fa-circle" aria-hidden="true"></i></span>
    </div>
    <p>Chỉnh hình</p>
</div>
<!-- /Mục Slider -->

<!-- Mục Slider -->
<div class="speicality-item text-center">
    <div class="speicality-img">
        <img src="assets/img/specialities/specialities-04.png" class="img-fluid" alt="Chuyên môn">
        <span><i class="fa fa-circle" aria-hidden="true"></i></span>
    </div>
    <p>Tim mạch</p>
</div>
<!-- /Mục Slider -->

<!-- Mục Slider -->
<div class="speicality-item text-center">
    <div class="speicality-img">
        <img src="assets/img/specialities/specialities-05.png" class="img-fluid" alt="Chuyên môn">
        <span><i class="fa fa-circle" aria-hidden="true"></i></span>
    </div>
    <p>Nha khoa</p>
</div>
<!-- /Mục Slider -->
</div>
<!-- /Slider -->

</div>
</div>
</div>
</section>
<!-- Phòng khám và Chuyên môn -->

<!-- Phần Phổ biến -->
<section class="section section-doctor">
    <div class="container-fluid">
        <div class="row">
            <!-- <div class="col-lg-4">
                <div class="section-header ">
                    <h2>Đặt Lịch Với Bác Sĩ</h2>
                    <p>Danh Sách Các Bác Sĩ Có Thể Đặt Lịch</p>
                </div>
                <div class="about-content">
                    <p>Danh Sách Này Thể Hiện Rằng Những Bác Sĩ Có Khả Năng Nhận Đặt Lịch</p>
                    <p>Tuỳ Vào Khối Lượng Công Việc Nên Danh Sách Này Giúp Người Dùng Dễ Dàng Biết Được Các Bác Sĩ Nào, 
						Có Thời Gian Vào Lúc Nào, Chuyên Môn Là Gì, Và Một Số Thông Tin Quan Trọng Giúp Người Dùng
					Dễ Dàng Sắp Xếp Thời Gian Theo Lịch Trình Cá Nhân</p>
                    <!-- <a href="javascript:;">Hoặc đặt lịch với sự sắp xếp của Bệnh Viện</a> -->
                </div>
            </div> -->
            <div class="col-lg-8">
                <div class="doctor-slider slider">

							<!-- Tiện ích Bác sĩ -->
<div class="profile-widget" style="width: 100%;">
    <div class="doc-img">
        <a href="doctor-profile.html">
            <img class="img-fluid" alt="Hình ảnh Người dùng" src="assets/img/doctors/doctor-01.jpg">
        </a>
        <a href="javascript:void(0)" class="fav-btn">
            <i class="far fa-bookmark"></i>
        </a>
    </div>
    <div class="pro-content">
        <h3 class="title">
            <a href="doctor-profile.html">Ruby Perrin</a>
            <i class="fas fa-check-circle verified"></i>
        </h3>
        <p class="speciality">MDS - Chuyên khoa Nha khoa và Nha trồng răng, BDS</p>
        <div class="rating">
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <span class="d-inline-block average-rating">(17)</span>
        </div>
        <ul class="available-info">
            <li>
                <i class="fas fa-map-marker-alt"></i> Florida, USA
            </li>
            <li>
                <i class="far fa-clock"></i> Có mặt vào Thứ Sáu, 22 Tháng Ba
            </li>
            <li>
                <i class="far fa-money-bill-alt"></i> $300 - $1000
                <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
            </li>
        </ul>
        <div class="row row-sm">
            <div class="col-6">
                <a href="doctor-profile.html" class="btn view-btn">Xem Hồ sơ</a>
            </div>
            <div class="col-6">
                <a href="booking.html" class="btn book-btn">Đặt Ngay</a>
            </div>
        </div>
    </div>
</div>
<!-- /Tiện ích Bác sĩ -->

<!-- Tiện ích Bác sĩ -->
<div class="profile-widget">
    <div class="doc-img">
        <a href="doctor-profile.html">
            <img class="img-fluid" alt="Hình ảnh Người dùng" src="assets/img/doctors/doctor-02.jpg">
        </a>
        <a href="javascript:void(0)" class="fav-btn">
            <i class="far fa-bookmark"></i>
        </a>
    </div>
    <div class="pro-content">
        <h3 class="title">
            <a href="doctor-profile.html">Darren Elder</a>
            <i class="fas fa-check-circle verified"></i>
        </h3>
        <p class="speciality">BDS, MDS - Phẫu thuật Nướu và Hàm mặt</p>
        <div class="rating">
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star"></i>
            <span class="d-inline-block average-rating">(35)</span>
        </div>
        <ul class="available-info">
            <li>
                <i class="fas fa-map-marker-alt"></i> Newyork, USA
            </li>
            <li>
                <i class="far fa-clock"></i> Có mặt vào Thứ Sáu, 22 Tháng Ba
            </li>
            <li>
                <i class="far fa-money-bill-alt"></i> $50 - $300
                <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
            </li>
        </ul>
        <div class="row row-sm">
            <div class="col-6">
                <a href="doctor-profile.html" class="btn view-btn">Xem Hồ sơ</a>
            </div>
            <div class="col-6">
                <a href="booking.html" class="btn book-btn">Đặt Ngay</a>
            </div>
        </div>
    </div>
</div>
<!-- /Tiện ích Bác sĩ -->

							<!-- Tiện ích Bác sĩ -->
<div class="profile-widget">
    <div class="doc-img">
        <a href="doctor-profile.html">
            <img class="img-fluid" alt="Hình ảnh Người dùng" src="assets/img/doctors/doctor-03.jpg">
        </a>
        <a href="javascript:void(0)" class="fav-btn">
            <i class="far fa-bookmark"></i>
        </a>
    </div>
    <div class="pro-content">
        <h3 class="title">
            <a href="doctor-profile.html">Deborah Angel</a>
            <i class="fas fa-check-circle verified"></i>
        </h3>
        <p class="speciality">MBBS, MD - Y học tổng quát, DNB - Tim mạch</p>
        <div class="rating">
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star"></i>
            <span class="d-inline-block average-rating">(27)</span>
        </div>
        <ul class="available-info">
            <li>
                <i class="fas fa-map-marker-alt"></i> Georgia, USA
            </li>
            <li>
                <i class="far fa-clock"></i> Có mặt vào Thứ Sáu, 22 Tháng Ba
            </li>
            <li>
                <i class="far fa-money-bill-alt"></i> $100 - $400
                <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
            </li>
        </ul>
        <div class="row row-sm">
            <div class="col-6">
                <a href="doctor-profile.html" class="btn view-btn">Xem Hồ sơ</a>
            </div>
            <div class="col-6">
                <a href="booking.html" class="btn book-btn">Đặt Ngay</a>
            </div>
        </div>
    </div>
</div>
<!-- /Tiện ích Bác sĩ -->

<!-- Tiện ích Bác sĩ -->
<div class="profile-widget">
    <div class="doc-img">
        <a href="doctor-profile.html">
            <img class="img-fluid" alt="Hình ảnh Người dùng" src="assets/img/doctors/doctor-04.jpg">
        </a>
        <a href="javascript:void(0)" class="fav-btn">
            <i class="far fa-bookmark"></i>
        </a>
    </div>
    <div class="pro-content">
        <h3 class="title">
            <a href="doctor-profile.html">Sofia Brient</a>
            <i class="fas fa-check-circle verified"></i>
        </h3>
        <p class="speciality">MBBS, MS - Phẫu thuật Y học tổng quát, MCh - Ngoại khoa</p>
        <div class="rating">
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star"></i>
            <span class="d-inline-block average-rating">(4)</span>
        </div>
        <ul class="available-info">
            <li>
                <i class="fas fa-map-marker-alt"></i> Louisiana, USA
            </li>
            <li>
                <i class="far fa-clock"></i> Có mặt vào Thứ Sáu, 22 Tháng Ba
            </li>
            <li>
                <i class="far fa-money-bill-alt"></i> $150 - $250
                <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
            </li>
        </ul>
        <div class="row row-sm">
            <div class="col-6">
                <a href="doctor-profile.html" class="btn view-btn">Xem Hồ sơ</a>
            </div>
            <div class "col-6">
                <a href="booking.html" class="btn book-btn">Đặt Ngay</a>
            </div>
        </div>
    </div>
</div>
<!-- /Tiện ích Bác sĩ -->

<!-- Tiện ích Bác sĩ -->
<div class="profile-widget">
    <div class="doc-img">
        <a href="doctor-profile.html">
            <img class="img-fluid" alt="Hình ảnh Người dùng" src="assets/img/doctors/doctor-05.jpg">
        </a>
        <a href="javascript:void(0)" class="fav-btn">
            <i class="far fa-bookmark"></i>
        </a>
    </div>
    <div class="pro-content">
        <h3 class="title">
            <a href="doctor-profile.html">Marvin Campbell</a>
            <i class="fas fa-check-circle verified"></i>
        </h3>
        <p class="speciality">MBBS, MD - Y học mắt, DNB - Y học mắt</p>
        <div class="rating">
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star"></i>
            <span class="d-inline-block average-rating">(66)</span>
        </div>
        <ul class="available-info">
            <li>
                <i class="fas fa-map-marker-alt"></i> Michigan, USA
            </li>
            <li>
                <i class="far fa-clock"></i> Có mặt vào Thứ Sáu, 22 Tháng Ba
            </li>
            <li>
                <i class="far fa-money-bill-alt"></i> $50 - $700
                <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
            </li>
        </ul>
        <div class="row row-sm">
            <div class="col-6">
                <a href="doctor-profile.html" class="btn view-btn">Xem Hồ sơ</a>
            </div>
            <div class "col-6">
                <a href="booking.html" class="btn book-btn">Đặt Ngay</a>
            </div>
        </div>
    </div>
</div>
<!-- /Tiện ích Bác sĩ -->

							<!-- Tiện ích Bác sĩ -->
<div class="profile-widget">
    <div class="doc-img">
        <a href="doctor-profile.html">
            <img class="img-fluid" alt="Hình ảnh Người dùng" src="assets/img/doctors/doctor-06.jpg">
        </a>
        <a href="javascript:void(0)" class="fav-btn">
            <i class="far fa-bookmark"></i>
        </a>
    </div>
    <div class="pro-content">
        <h3 class="title">
            <a href="doctor-profile.html">Katharine Berthold</a>
            <i class="fas fa-check-circle verified"></i>
        </h3>
        <p class="speciality">MS - Orthopaedics, MBBS, M.Ch - Orthopaedics</p>
        <div class="rating">
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star"></i>
            <span class="d-inline-block average-rating">(52)</span>
        </div>
        <ul class="available-info">
            <li>
                <i class="fas fa-map-marker-alt"></i> Texas, USA
            </li>
            <li>
                <i class="far fa-clock"></i> Có mặt vào Thứ Sáu, 22 Tháng Ba
            </li>
            <li>
                <i class="far fa-money-bill-alt"></i> $100 - $500
                <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
            </li>
        </ul>
        <div class="row row-sm">
            <div class="col-6">
                <a href="doctor-profile.html" class="btn view-btn">Xem Hồ sơ</a>
            </div>
            <div class "col-6">
                <a href="booking.html" class="btn book-btn">Đặt Ngay</a>
            </div>
        </div>
    </div>
</div>
<!-- /Tiện ích Bác sĩ -->

<!-- Tiện ích Bác sĩ -->
<div class="profile-widget">
    <div class="doc-img">
        <a href="doctor-profile.html">
            <img class="img-fluid" alt="Hình ảnh Người dùng" src="assets/img/doctors/doctor-07.jpg">
        </a>
        <a href="javascript:void(0)" class="fav-btn">
            <i class="far fa-bookmark"></i>
        </a>
    </div>
    <div class="pro-content">
        <h3 class="title">
            <a href="doctor-profile.html">Linda Tobin</a>
            <i class="fas fa-check-circle verified"></i>
        </h3>
        <p class="speciality">MBBS, MD - Y học tổng quát, DM - Thần kinh học</p>
        <div class="rating">
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star filled"></i>
            <i class="fas fa-star"></i>
            <span class="d-inline-block average-rating">(43)</span>
        </div>
        <ul class="available-info">
            <li>
                <i class="fas fa-map-marker-alt"></i> Kansas, USA
            </li>
            <li>
                <i class="far fa-clock"></i> Có mặt vào Thứ Sáu, 22 Tháng Ba
            </li>
            <li>
                <i class="far fa-money-bill-alt"></i> $100 - $1000
                <i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
            </li>
        </ul>
        <div class="row row-sm">
            <div class="col-6">
                <a href="doctor-profile.html" class="btn view-btn">Xem Hồ sơ</a>
            </div>
            <div class "col-6">
                <a href="booking.html" class="btn book-btn">Đặt Ngay</a>
            </div>
        </div>
    </div>
</div>
<!-- /Tiện ích Bác sĩ -->


							<!-- Doctor Widget -->
							<div class="profile-widget">
								<div class="doc-img">
									<a href="doctor-profile.html">
										<img class="img-fluid" alt="User Image" src="assets/img/doctors/doctor-08.jpg">
									</a>
									<a href="javascript:void(0)" class="fav-btn">
										<i class="far fa-bookmark"></i>
									</a>
								</div>
								<div class="pro-content">
									<h3 class="title">
										<a href="doctor-profile.html">Paul Richard</a>
										<i class="fas fa-check-circle verified"></i>
									</h3>
									<p class="speciality">MBBS, MD - Dermatology , Venereology & Lepros</p>
									<div class="rating">
										<i class="fas fa-star filled"></i>
										<i class="fas fa-star filled"></i>
										<i class="fas fa-star filled"></i>
										<i class="fas fa-star filled"></i>
										<i class="fas fa-star"></i>
										<span class="d-inline-block average-rating">(49)</span>
									</div>
									<ul class="available-info">
										<li>
											<i class="fas fa-map-marker-alt"></i> California, USA
										</li>
										<li>
											<i class="far fa-clock"></i> Available on Fri, 22 Mar
										</li>
										<li>
											<i class="far fa-money-bill-alt"></i> $100 - $400
											<i class="fas fa-info-circle" data-toggle="tooltip" title="Lorem Ipsum"></i>
										</li>
									</ul>
									<div class="row row-sm">
										<div class="col-6">
											<a href="doctor-profile.html" class="btn view-btn">View Profile</a>
										</div>
										<div class="col-6">
											<a href="booking.html" class="btn book-btn">Book Now</a>
										</div>
									</div>
								</div>
							</div>
							<!-- Doctor Widget -->

						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /Popular Section -->

		<!-- Các Tính Năng Có Sẵn -->
<section class="section section-features">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5 features-img">
                <img src="assets/img/features/feature.png" class="img-fluid" alt="Tính Năng">
            </div>
            <div class="col-md-7">
                <div class="section-header">
                    <h2 class="mt-2">Cơ Sở Vật Chất Của Bệnh Viện</h2>
                    <p>Nhằm Đảm Bảo Tiêu Chuẩn Và Chất Lượng, Các Phòng, Ban Ở Bệnh Viện Đã Được Chứng Nhận Đạt Chuẩn</p>
                </div>
                <div class="features-slider slider">
                    <!-- Mục Slider -->
                    <div class="feature-item text-center">
                        <img src="assets/img/features/feature-01.jpg" class="img-fluid" alt="Tính Năng">
                        <p>Phòng Bệnh Nhân</p>
                    </div>
                    <!-- /Mục Slider -->

                    <!-- Mục Slider -->
                    <div class="feature-item text-center">
                        <img src="assets/img/features/feature-02.jpg" class="img-fluid" alt="Tính Năng">
                        <p>Phòng Kiểm Tra</p>
                    </div>
                    <!-- /Mục Slider -->

                    <!-- Mục Slider -->
                    <div class="feature-item text-center">
                        <img src="assets/img/features/feature-03.jpg" class="img-fluid" alt="Tính Năng">
                        <p>Phòng ICU</p>
                    </div>
                    <!-- /Mục Slider -->

                    <!-- Mục Slider -->
                    <div class="feature-item text-center">
                        <img src="assets/img/features/feature-04.jpg" class="img-fluid" alt="Tính Năng">
                        <p>Phòng Thí Nghiệm</p>
                    </div>
                    <!-- /Mục Slider -->

                    <!-- Mục Slider -->
                    <div class="feature-item text-center">
                        <img src="assets/img/features/feature-05.jpg" class="img-fluid" alt="Tính Năng">
                        <p>Phòng Phẫu Thuật</p>
                    </div>
                    <!-- /Mục Slider -->

                    <!-- Mục Slider -->
                    <div class="feature-item text-center">
                        <img src="assets/img/features/feature-06.jpg" class="img-fluid" alt="Tính Năng">
                        <p>Y Tế</p>
                    </div>
                    <!-- /Mục Slider -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Các Tính Năng Có Sẵn -->


		<!-- Footer -->
		<footer class="footer">

			<!-- Footer Top -->
			<div class="footer-top">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-3 col-md-6">

							<!-- Footer Widget -->
							<div class="footer-widget footer-about">
								<div class="footer-logo">
									<img src="assets/img/footer-logo.png" alt="logo">
								</div>
								<div class="footer-about-content">
									<p>Hệ thống web site đặt lịch khám bệnh trực tuyến </p>
									<div class="social-icon">
										<ul>
											<li>
												<a href="#" target="_blank"><i class="fab fa-facebook-f"></i> </a>
											</li>
											<li>
												<a href="#" target="_blank"><i class="fab fa-twitter"></i> </a>
											</li>
											<li>
												<a href="#" target="_blank"><i class="fab fa-linkedin-in"></i></a>
											</li>
											<li>
												<a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
											</li>
											<li>
												<a href="#" target="_blank"><i class="fab fa-dribbble"></i> </a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- /Footer Widget -->

						</div>

						<div class="col-lg-3 col-md-6">

							<!-- Footer Widget -->
							<div class="footer-widget footer-menu">
								<h2 class="footer-title">Dành cho bệnh nhân</h2>
								<ul>
									<li><a href="search.jsp"><i class="fas fa-angle-double-right"></i> Tìm kiếm bác sĩ</a></li>
									<li><a href="sign.jsp"><i class="fas fa-angle-double-right"></i> Đăng nhập</a></li>
									<li><a href="register.html"><i class="fas fa-angle-double-right"></i> Đăng kí</a>
									</li>
									<li><a href="booking.html"><i class="fas fa-angle-double-right"></i> Đặt lịch</a>
									</li>
									<li><a href="patient-dashboard.jsp"><i class="fas fa-angle-double-right"></i>
											Bảng kiểm soát lịch hẹn</a></li>
								</ul>
							</div>
							<!-- /Footer Widget -->

						</div>

						<div class="col-lg-3 col-md-6">

							<!-- Footer Widget -->
							<div class="footer-widget footer-menu">
								<h2 class="footer-title">Bác sĩ</h2>
								<ul>
									<li><a href="appointments.html"><i class="fas fa-angle-double-right"></i>
											Kiểm tra cuộc hẹn</a></li>
									<li><a href="chat.html"><i class="fas fa-angle-double-right"></i> Chat</a></li>
									<li><a href="sign.jsp"><i class="fas fa-angle-double-right"></i> Đăng nhập</a></li>
									<li><a href="doctor-register.html"><i class="fas fa-angle-double-right"></i>
											Đăng kí</a></li>
									<li><a href="doctor-dashboard.html"><i class="fas fa-angle-double-right"></i>Dashboard của bác sĩ</a></li>
								</ul>
							</div>
							<!-- /Footer Widget -->

						</div>

						<div class="col-lg-3 col-md-6">

							<!-- Footer Widget -->
							<div class="footer-widget footer-contact">
								<h2 class="footer-title">Liên hệ</h2>
								<div class="footer-contact-info">
									<div class="footer-address">
										<span><i class="fas fa-map-marker-alt"></i></span>
										<p> Sở y tế Hà Nội - Bệnh viện đa khoa huyện Đan Phượng<br> Thị Trấn Phùng - Đan Phượng - Hà nội </p>
									</div>
									<p>
										<i class="fas fa-phone-alt"></i>
										0433636050
									</p>
									<p class="mb-0">
										<i class="fas fa-envelope"></i>
										benhviendakhoadanphuong@email.com
									</p>
								</div>
							</div>
							<!-- /Footer Widget -->

						</div>

					</div>
				</div>
			</div>
			<!-- /Footer Top -->

			<!-- Footer Bottom -->
			<div class="footer-bottom">
				<div class="container-fluid">

					<!-- Copyright -->
					<div class="copyright">
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="copyright-text">
									<p class="mb-0"><a href="https://datlichjkhambenhviendanphuong.com"></a></p>
								</div>
							</div>
							<div class="col-md-6 col-lg-6">

								<!-- Copyright Menu -->
								<div class="copyright-menu">
									<ul class="policy-menu">
										<li><a href="#">Terms and Conditions</a></li>
										<li><a href="#">Policy</a></li>
									</ul>
								</div>
								<!-- /Copyright Menu -->

							</div>
						</div>
					</div>
					<!-- /Copyright -->

				</div>
			</div>
			<!-- /Footer Bottom -->

		</footer>
		<!-- /Footer -->

	</div>
	<!-- /Main Wrapper -->

	<!-- jQuery -->
	<script src="assets/js/jquery.min.js"></script>

	<!-- Bootstrap Core JS -->
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- Slick JS -->
	<script src="assets/js/slick.js"></script>

	<!-- Custom JS -->
	<script src="assets/js/script.js"></script>

</body>
</html>