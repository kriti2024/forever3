<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartlist.css" />
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container">
    <div class="page-header">
        <h1>Your Shopping Cart</h1>
        <p>Review your items and proceed to checkout</p>
    </div>

    <c:choose>
        <c:when test="${not empty sessionScope.cart}">
            <div class="cart-container">
                <div class="cart-items">
                    <div class="cart-columns">
                        <div class="column-product">Product</div>
                        <div class="column-price">Price</div>
                        <div class="column-quantity">Quantity</div>
                        <div class="column-total">Total</div>
                        <div class="column-action">Action</div>
                    </div>

                    <c:set var="cartTotal" value="0" />
                    <c:forEach var="cartItem" items="${sessionScope.cart}">
    <c:set var="itemTotal" value="${cartItem.item.itemPrice * cartItem.quantity}" />
    <c:set var="cartTotal" value="${cartTotal + itemTotal}" />
    <div class="cart-item">
        <div class="item-product">
            <div class="product-image">
                <img src="${pageContext.request.contextPath}/resources/images/system/${cartItem.item.imageUrl}" 
                     alt="${cartItem.item.itemName}" />
            </div>
            <div class="product-details">
                <h3>${cartItem.item.itemName}</h3>
                <p class="product-id">Item ID: ${cartItem.item.itemId}</p>
            </div>
        </div>
        <div class="item-price">Rs ${cartItem.item.itemPrice}</div>
        <div class="item-quantity">
            <form method="post" action="UpdateCartServlet">
                <input type="hidden" name="itemId" value="${cartItem.item.itemId}" />
                <input type="number" name="quantity" value="${cartItem.quantity}" min="1" class="quantity-input" />
                <button type="submit" class="update-btn">Update</button>
            </form>
        </div>
        <div class="item-total">Rs ${itemTotal}</div>
        <div class="item-action">
            <form method="post" action="RemoveFromCartServlet">
                <input type="hidden" name="itemId" value="${cartItem.item.itemId}" />
                <button type="submit" class="remove-btn">✕</button>
            </form>
        </div>
    </div>
</c:forEach>

                </div>

                <div class="cart-summary">
                    <h3>Total: Rs ${cartTotal}</h3>
                    <form action="CheckoutServlet" method="post">
                        <button type="submit" class="checkout-btn">Proceed to Checkout</button>
                    </form>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <div class="empty-cart">
                <h2>Your cart is empty!</h2>
                <a href="makeup.jsp" class="shop-now-btn">Shop Now</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
