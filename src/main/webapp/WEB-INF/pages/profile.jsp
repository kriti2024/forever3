<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    boolean isEditMode = "true".equals(request.getParameter("edit"));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>User Profile - Forever3</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<div class="profile-container">
    <div class="profile-image">
        <img src="${pageContext.request.contextPath}/resources/imagescustomer/${user.imageUrl}" alt="Profile Picture" id="profilePic" />
    </div>
    <div class="profile-details">
       <form action="${pageContext.request.contextPath}/updateProfile" method="post" enctype="multipart/form-data">

  <input type="hidden" name="oldUsername" value="${user.userName}">

    <input type="text" name="userName" value="${user.userName}" <%= isEditMode ? "" : "readonly" %> />
    <input type="text" name="firstName" value="${user.firstName}" <%= isEditMode ? "" : "readonly" %> />
    <input type="text" name="lastName" value="${user.lastName}" <%= isEditMode ? "" : "readonly" %> />
    <input type="email" name="email" value="${user.email}" <%= isEditMode ? "" : "readonly" %> />
    <input type="text" name="number" value="${user.number}" <%= isEditMode ? "" : "readonly" %> />

    <!-- Password input -->
    <input type="password" name="password" placeholder="Enter new password" <%= isEditMode ? "" : "readonly" %> />

    <!-- Profile picture -->
    <input type="file" name="profileImage" <%= isEditMode ? "" : "disabled" %> />

    <div class="button-group">
        <c:choose>
            <c:when test="<%= !isEditMode %>">
                <!-- Clicking this link reloads the same page in edit mode -->
                <a href="${pageContext.request.contextPath}/profile?edit=true" class="btn-edit">Edit Profile</a>
            </c:when>
            <c:otherwise>
                <!-- Submit button appears only in edit mode -->
                <button type="submit">Save Changes</button>
            </c:otherwise>
        </c:choose>
    </div>
</form>
        <c:if test="${not empty successMessage}">
            <div class="success-message">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
