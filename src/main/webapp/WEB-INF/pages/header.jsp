<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Beauty Store</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>
  <header class="header">
    <div class="header-content">
      <!-- Left: Logo + Nav -->
      <div class="header-left" style="display: flex; align-items: center;">
        <div class="logo">
          <a href="/">forever3</a>
        </div>
        <nav class="main-nav">
          <ul>
            <li><a href="${contextPath}/home">Home</a></li>
            <li><a href="${contextPath}/skincare">SkinCare</a></li>
            <li><a href="${contextPath}/makeup">MakeUp</a></li>
            <li><a href="${contextPath}/aboutus">AboutUs</a></li>
            <li><a href="${contextPath}/contactus">ContactUs</a></li>
          </ul>
        </nav>
      </div>

      <!-- Right: Search, Auth, Cart, Profile -->
      <div class="header-right">
        <!-- Search -->
        <div class="search-container">
          <input type="text" class="search-input" placeholder="search" />
          <button class="search-button">
            <img src="${pageContext.request.contextPath}/resources/images/system/search.png" class="magnifier-icon" alt="Search" />
          </button>
        </div>

        <!-- Auth Buttons -->
        <div class="auth-buttons">
          <a href="/logout" class="btn">Logout</a>
        </div>

        <!-- Cart -->
        <div class="cart">
          <a href="/cart" class="cart-icon">
            <img src="${pageContext.request.contextPath}/resources/images/system/cart.png" class="cart-icon-img" alt="Cart" />
          </a>
        </div>

        <!-- Profile -->
        <div class="profile">
          <a href="/profile" class="profile-link">
            <img src="${pageContext.request.contextPath}/resources/images/system/profile.png" class="profile-icon-img" alt="Profile" />
          </a>
        </div>
      </div>

    </div>
  </header>
</body>
</html>
