<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>My Orders</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/orderlist.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<div class="page-wrapper">
    <h2 class="orders-title">My Orders</h2>
    <p class="orders-subtitle">View and track your order history</p>

    <div class="table-container">
        <table class="orders-table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                    <th>Payment Status</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty orderList}">
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.orderId}</td>
                                <td><fmt:formatDate value="${order.orderDate}" pattern="MMM dd, yyyy"/></td>
                                <td>$${order.totalAmount}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.paymentStatus == 'Paid'}">
                                            <span class="status-badge paid">Paid</span>
                                        </c:when>
                                        <c:when test="${order.paymentStatus == 'Failed'}">
                                            <span class="status-badge failed">Failed</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status-badge pending">${order.paymentStatus}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="4" style="text-align:center; padding: 20px;">You have no orders yet.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>
