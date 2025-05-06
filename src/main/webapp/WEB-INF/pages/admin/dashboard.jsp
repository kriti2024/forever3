<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Forever3 Admin Dashboard</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashboard.css" />
</head>
<body>

  <div class="container">
    <div class="sidebar">
      <div class="brand">
        <h2>Forever3</h2>
        <p>Beauty Admin</p>
      </div>
      <ul class="nav">
        <li class="active"><a href="${contextPath}/dashboard"><span class="icon">🏠</span>
            Dashboard</a></li>
        <li><a href="${contextPath}/adminOrder"><span class="icon">📊</span>
            Order List</a></li>
        <li><a href="${contextPath}/modifyCustomers"><span class="icon">👤</span>
            Customers</a></li>
        <li><a href="${contextPath}/customerUpdate"><span class="icon">✏️</span>
            Update Customer</a></li>
        <li><a href="${contextPath}/products"><span class="icon">💄</span>
            Products</a></li>
        <li><a href="${contextPath}/analytics"><span class="icon">📈</span>
            Analytics</a></li>
      </ul>
      <div class="logout">
        <form action="${contextPath}/logout" method="post">
          <input type="submit" class="nav-button" value="Logout" />
        </form>
      </div>
    </div>

    <div class="content">
      <div class="header">
        <div class="info-box">
          <h3>Total Customers</h3>
          <p>${empty total ? 0 : total}</p>
          <div class="trend up">+12%</div>
        </div>
        <div class="info-box">
          <h3>Total Orders</h3>
          <p>${empty orders ? 0 : orders}</p>
          <div class="trend up">+8%</div>
        </div>
        <div class="info-box">
          <h3>Revenue</h3>
          <p>${empty revenue ? '$0' : revenue}</p>
          <div class="trend up">+15%</div>
        </div>
        <div class="info-box">
          <h3>Products</h3>
          <p>${empty products ? 0 : products}</p>
          <div class="trend up">+3%</div>
        </div>
      </div>

      <div class="card">
        <div>
          <h2>Welcome, Beauty Admin!</h2>
          <p>Forever3 Beauty Management Dashboard</p>
          <br /> <br />
          <p>We're excited to have you onboard. Manage your beauty brand's data
            efficiently and effortlessly with our user-friendly interface.
            From customer records to product inventory, everything you need is
            just a few clicks away.</p>
        </div>
        <img src="${contextPath}/resources/images/system/beauty-products.jpg" alt="beauty products">
      </div>

      <div class="table-container">
        <h3>Recent Customers</h3>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Number</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <!-- Using JSTL forEach loop to display customer data -->
            <c:forEach var="customer" items="${customerList}">
              <tr>
                <td>${customer.id}</td>
                <td>${customer.firstName} ${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>${customer.number}</td>
                <td><span class="status ${customer.status == 'Active' ? 'active' : customer.status == 'Pending' ? 'pending' : 'inactive'}">${customer.status}</span></td>
              </tr>
            </c:forEach>
            
            <!-- Fallback data if no customers are available -->
            <c:if test="${empty customerList}">
              <tr>
                <td>1001</td>
                <td>Emma Johnson</td>
                <td>emma@example.com</td>
                <td>555-123-4567</td>
                <td><span class="status active">Active</span></td>
              </tr>
              <tr>
                <td>1002</td>
                <td>Michael Smith</td>
                <td>michael@example.com</td>
                <td>555-987-6543</td>
                <td><span class="status pending">Pending</span></td>
              </tr>
              <tr>
                <td>1003</td>
                <td>Sophia Williams</td>
                <td>sophia@example.com</td>
                <td>555-456-7890</td>
                <td><span class="status active">Active</span></td>
              </tr>
            </c:if>
          </tbody>
        </table>
      </div>
      
      <div class="cards-container">
        <div class="small-card">
          <h3>Top Selling Products</h3>
          <ul class="product-list">
            <li>
              <span class="product-name">Glow Serum</span>
              <span class="product-sales">245 units</span>
            </li>
            <li>
              <span class="product-name">Hydrating Cream</span>
              <span class="product-sales">198 units</span>
            </li>
            <li>
              <span class="product-name">Makeup Bundle</span>
              <span class="product-sales">156 units</span>
            </li>
          </ul>
        </div>
        <div class="small-card">
          <h3>Recent Activity</h3>
          <ul class="activity-list">
            <li>
              <span class="activity-icon">📦</span>
              <div class="activity-details">
                <span class="activity-text">New product added: Lip Gloss Collection</span>
                <span class="activity-time">2 hours ago</span>
              </div>
            </li>
            <li>
              <span class="activity-icon">💰</span>
              <div class="activity-details">
                <span class="activity-text">Order #1089 completed - $156.99</span>
                <span class="activity-time">5 hours ago</span>
              </div>
            </li>
            <li>
              <span class="activity-icon">👤</span>
              <div class="activity-details">
                <span class="activity-text">New customer registered: Jessica Lee</span>
                <span class="activity-time">Yesterday</span>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>

</body>
</html>