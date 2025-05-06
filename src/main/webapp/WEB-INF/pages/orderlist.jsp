<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Your Orders - Forever3</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/orders.css" />
</head>
<body>
  <div class="page-container">
    <header>
      <div class="header-container">
        <div class="logo">
          <h1>Forever3</h1>
        </div>
        <nav>
          <ul>
            <li><a href="${contextPath}/home">Home</a></li>
            <li><a href="${contextPath}/products">Products</a></li>
            <li><a href="${contextPath}/cartlist">Cart</a></li>
            <li><a href="${contextPath}/orderlist" class="active">Orders</a></li>
            <li><a href="${contextPath}/profile">Profile</a></li>
          </ul>
        </nav>
      </div>
    </header>

    <main>
      <div class="orders-container">
        <h2>Your Order History</h2>
        
        <div class="order-filters">
          <div class="search-box">
            <input type="text" id="orderSearch" placeholder="Search orders...">
            <button class="search-btn">Search</button>
          </div>
          <div class="filter-options">
            <label for="statusFilter">Status:</label>
            <select id="statusFilter">
              <option value="all">All</option>
              <option value="delivered">Delivered</option>
              <option value="processing">Processing</option>
              <option value="shipped">Shipped</option>
              <option value="cancelled">Cancelled</option>
            </select>
            
            <label for="dateFilter">Date:</label>
            <select id="dateFilter">
              <option value="all">All Time</option>
              <option value="30">Last 30 Days</option>
              <option value="90">Last 90 Days</option>
              <option value="365">Last Year</option>
            </select>
          </div>
        </div>
        
        <c:choose>
          <c:when test="${empty orders}">
            <div class="empty-orders">
              <div class="empty-orders-icon">📦</div>
              <p>You haven't placed any orders yet</p>
              <a href="${contextPath}/products" class="shop-now-btn">Shop Now</a>
            </div>
          </c:when>
          <c:otherwise>
            <div class="orders-list">
              <c:forEach var="order" items="${orders}">
                <div class="order-card">
                  <div class="order-header">
                    <div class="order-info">
                      <div class="order-id">
                        <span>Order #${order.id}</span>
                      </div>
                      <div class="order-date">
                        <span>Placed on: <fmt:formatDate value="${order.orderDate}" pattern="MMMM dd, yyyy"/></span>
                      </div>
                    </div>
                    <div class="order-status ${order.status.toLowerCase()}">
                      <span>${order.status}</span>
                    </div>
                  </div>
                  
                  <div class="order-items">
                    <c:forEach var="item" items="${order.items}">
                      <div class="order-item">
                        <div class="item-image">
                          <img src="${contextPath}/resources/images/products/${item.image}" alt="${item.name}">
                        </div>
                        <div class="item-details">
                          <h3>${item.name}</h3>
                          <p class="item-category">${item.category}</p>
                          <p class="item-price">$${item.price} × ${item.quantity}</p>
                        </div>
                        <div class="item-actions">
                          <form action="${contextPath}/reorder" method="post">
                            <input type="hidden" name="itemId" value="${item.id}">
                            <button type="submit" class="reorder-btn">Add to Cart</button>
                          </form>
                        </div>
                      </div>
                    </c:forEach>
                  </div>
                  
                  <div class="order-footer">
                    <div class="order-total">
                      <span>Total:</span>
                      <span>$<fmt:formatNumber value="${order.total}" pattern="#,##0.00"/></span>
                    </div>
                    <div class="order-actions">
                      <a href="${contextPath}/orderDetails?id=${order.id}" class="view-details-btn">View Details</a>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </main>

    <footer>
      <div class="footer-container">
        <p>&copy; 2023 Forever3 Beauty. All rights reserved.</p>
      </div>
    </footer>
  </div>
</body>
</html>