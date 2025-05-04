<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Beauty Store</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/makeup.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
  <header class="header">
    <div class="header-content">
 
      <div class="header-left" style="display: flex; align-items: center;">
        <div class="logo">
          <a href="<%= request.getContextPath() %>/">forever3</a>
        </div>
        <nav class="main-nav">
          <ul>
            <li><a href="${pageContext.request.contextPath}/home.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/skin.jsp">SkinCare</a></li>
            <li><a href="${pageContext.request.contextPath}/makeup.jsp">Makeup</a></li>
            <li><a href="${pageContext.request.contextPath}/aboutus.jsp">AboutUs</a></li>
            <li><a href="${pageContext.request.contextPath}/contactus.jsp">ContactUs</a></li>
          </ul>
        </nav>
      </div>
 
      <div class="header-right">
        <div class="search-container">
          <input type="text" class="search-input" placeholder="search" />
          <button class="search-button">
            <img src="search.png" class="magnifier-icon" alt="Search" />
          </button>
        </div>
        <div class="auth-buttons">
          <a href="<%= request.getContextPath() %>/login.jsp" class="btn">Login</a>
          <a href="<%= request.getContextPath() %>/signup.jsp" class="btn">Sign Up</a>
        </div>
        <div class="cart">
          <a href="<%= request.getContextPath() %>/cart.jsp" class="cart-icon">
            <img src="Cart.png" class="cart-icon-img" alt="Cart" />
          </a>
        </div>
      </div>
 
    </div>
  </header>
</body>
</html>