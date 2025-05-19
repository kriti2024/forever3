<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Makeup - Forever3</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/makeup.css">
</head>
<body>
  <jsp:include page="header.jsp" />

  <main>
    <section class="product-page">
      <h1>Makeup Products</h1>
      <div class="product-grid">
        <c:forEach var="item" items="${items}">
          <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/system/${item.imageUrl}" alt="${item.itemName}" />
            <h3>${item.itemName}</h3>
            <p>${item.description}</p>
            <p class="product-price">Rs ${item.itemPrice}</p>

            <!-- Form to Add to Cart -->
            <form method="post" action="${pageContext.request.contextPath}/addToCartController">
              <input type="hidden" name="itemId" value="${item.itemId}" />
				<div class="quantity-selector">
				  <button type="button" class="qty-btn minus" aria-label="Decrease quantity">−</button>
				  <input type="number" name="quantity" value="1" min="1" class="qty-input" />
				  <button type="button" class="qty-btn plus" aria-label="Increase quantity">+</button>
				</div>
              <br/>
              <button type="submit" class="add-to-cart-btn">Add to Cart</button>
            </form>
          </div>
        </c:forEach>
      </div>
    </section>
  </main>

  <jsp:include page="footer.jsp" />
</body>
</html>
