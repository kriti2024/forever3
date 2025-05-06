<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<%
    // Get the current session and username if available
    HttpSession userSession = request.getSession(false);
    String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
    pageContext.setAttribute("currentUser", currentUser);
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Beauty Store</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/header.css" />
</head>
<body>
  <header class="header">
    <div class="header-content">
      <!-- Left: Logo + Nav -->
      <div class="header-left" style="display: flex; align-items: center;">
        <div class="logo">
          <a href="${contextPath}/">forever3</a>
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
            <img src="${contextPath}/resources/images/system/search.png" class="magnifier-icon" alt="Search" />
          </button>
        </div>

		<div class="auth-buttons">
		  <form action="${contextPath}/logout" method="post" style="display:inline;">
		    <input type="submit" class="btn" value="Logout" />
		  </form>
		</div>

        <!-- Cart -->
        <div class="cart">
          <a href="${contextPath}/cart" class="cart-icon">
            <img src="${contextPath}/resources/images/system/cart.png" class="cart-icon-img" alt="Cart" />
          </a>
        </div>

        <!-- Profile -->
        <div class="profile">
          <a href="${contextPath}/profile" class="profile-link">
            <img src="${contextPath}/resources/images/system/profile.png" class="profile-icon-img" alt="Profile" />
          </a>
        </div>
      </div>
    </div>
  </header>
</body>
</html>
