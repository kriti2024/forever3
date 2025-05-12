<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${contextPath}/css/dashboard.css">
</head>
<body>
<div class="container">
  <div class="sidebar">
    <h2>Admin Dashboard</h2>
    <ul>
      <li><a href="${contextPath}/admin/products">Products</a></li>
      <li><a href="${contextPath}/admin/orders">Orders</a></li>
      <li><a href="${contextPath}/admin/customers">Customers</a></li>
      <li><a href="${contextPath}/admin/reports">Reports</a></li>
      <li><a href="${contextPath}/admin/users">Admin Users</a></li>
    </ul>

    <div class="logout">
      <form action="${contextPath}/logout" method="post">
        <input type="submit" value="Logout" />
      </form>
    </div>
  </div>

  <div class="main-content">
    <div class="dashboard-header">
      <h1>Welcome to the Admin Dashboard</h1>
      <p>Manage your skincare products, orders, customers, and more.</p>
    </div>

    <div class="dashboard-section">
      <h2>Product Management</h2>
      <button class="add-btn">Add New Product</button>
      <table>
        <thead>
        <tr>
          <th>Product ID</th>
          <th>Name</th>
          <th>Category</th>
          <th>Price</th>
          <th>Stock</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productList}">
          <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.price}</td>
            <td>${product.stock}</td>
            <td>
              <a href="${contextPath}/admin/products/edit/${product.id}">Edit</a> |
              <a href="${contextPath}/admin/products/delete/${product.id}">Delete</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>
