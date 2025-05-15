<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
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

    <!-- Logo -->
    <div class="logo">
      <a href="${contextPath}/">forever3</a>
    </div>

    <!-- Navigation -->
    <nav class="main-nav">
      <ul>
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/skincare">SkinCare</a></li>
        <li><a href="${pageContext.request.contextPath}/makeup">MakeUp</a></li>
        <li><a href="${pageContext.request.contextPath}/aboutus">AboutUs</a></li>
        <c:if test="${not empty currentUser}">
          <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
        </c:if>
      </ul>
    </nav>

    <!-- Right Section -->
    <div class="header-right">

      <!-- Search -->
      <div class="search-container">
        <input type="text" class="search-input" placeholder="search" />
        <button class="search-button">
          <img src="${contextPath}/resources/images/system/search.jpg" class="icon-img" alt="Search" />
        </button>
      </div>

      <!-- Auth Buttons -->
      <div class="auth-button-container">
        <c:choose>
          <c:when test="${not empty currentUser}">
            <form action="${contextPath}/logout" method="post">
              <button type="submit" class="auth-button">Logout</button>
            </form>
          </c:when>
          <c:otherwise>
            <form action="${contextPath}/login" method="get">
              <button type="submit" class="auth-button">Login</button>
            </form>
          </c:otherwise>
        </c:choose>
      </div>

      <c:if test="${not empty currentUser}">
        <!-- Cart -->
        <div class="icon-container">
          <a href="${contextPath}/cart">
            <img src="${contextPath}/resources/images/system/cart.jpg" class="icon-img" alt="Cart" />
          </a>
        </div>

        <!-- Orderlist -->
        <div class="icon-container">
          <a href="${contextPath}/orderlist">Orderlist</a>
        </div>
      </c:if>

    </div>
  </div>
</header>
</body>
</html>
