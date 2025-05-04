<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Forever3 Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
  <div class="container">
    <aside class="sidebar">
        <div class="logo-text">
            <a>forever3</a>
          </div>

        <nav>
            <ul>
              <li><a href="#">Dashboard</a></li>
              <li><a href="#">Order List</a></li>
              <li><a href="#">Customer List</a></li>
              <li><a href="#">Add Product</a></li>
              <li><a href="#">View Product</a></li>
              <li><a href="#">Order Status</a></li>
            </ul> 
      </nav>
    </aside>
 
    <main class="main-content">
      <div class="top-bar">
        <h1>Dashboard</h1>
        <input type="text" class="search-bar" placeholder="Search...">
      </div>
 
      <section class="cards">
        <div class="card">Total Sales<br><strong>$12,300</strong></div>
        <div class="card">Orders<br><strong>130</strong></div>
        <div class="card">New Customers<br><strong>48</strong></div>
      </section>
 
      <section class="reports">
        <h2>Recent Orders</h2>
        <table>
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Customer</th>
              <th>Item</th>
              <th>Status</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>#00123</td>
              <td>Arya S.</td>
              <td>Glow Serum</td>
              <td>Delivered</td>
              <td>$49.99</td>
            </tr>
            <tr>
              <td>#00124</td>
              <td>Meera K.</td>
              <td>Lip & Cheek Tint</td>
              <td>Shipped</td>
              <td>$29.99</td>
            </tr>
            <tr>
              <td>#00125</td>
              <td>Priya L.</td>
              <td>Hydrating Face Mist</td>
              <td>Processing</td>
              <td>$34.00</td>
            </tr>
          </tbody>
        </table>
      </section>
    </main>
  </div>
</body>
</html>