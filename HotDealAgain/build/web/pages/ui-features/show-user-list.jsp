<%-- 
    Document   : show-user-list
    Created on : Apr 21, 2024, 8:14:41 PM
    Author     : HP
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Page</title>
    <!-- CSS -->
    <link rel="stylesheet" href="../../assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../../assets/vendors/css/vendor.bundle.base.css">
    <!-- End CSS -->
    <!-- Plugin CSS for this page -->
    <link rel="stylesheet" href="assets/vendors/jvectormap/jquery-jvectormap.css">
    <link rel="stylesheet" href="assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.theme.default.min.css">
    <!-- End Plugin CSS for this page -->
    <!-- Custom CSS -->
    <link rel="stylesheet" href="../../assets/css/style.css">
    <!-- End Custom CSS -->
    <link rel="shortcut icon" href="../../assets/images/favicon.png" />
    <style>
        .error-message {
            text-align: center;
            font-size: 1.5em;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.USER_ROLE == 1}">
         <jsp:include page="/partials/_sidebar.jsp" />
                <!-- Include Navbar -->
        <div class="container-fluid page-body-wrapper">
                          

            <div class="row justify-content-center mt-5">
                <!-- Include Sidebar -->
                 <jsp:include page="/partials/_navbar.jsp" />
                <!-- Main Content -->
                <div class="col-md-8">
                    <h2 class="text-center">User List</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Created Date</th>
                                <th>Last Login Date</th>
                                <th>Role ID</th>
                                <th>Is Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iterate over the user list and display data -->
                            <c:forEach var="user" items="${sessionScope.USER_LIST}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.password}</td>
                                    <td>${user.first_name}</td>
                                    <td>${user.last_name}</td>
                                    <td>${user.created_date}</td>
                                    <td>${user.last_loginDate}</td>
                                    <td>${user.role_id}</td>
                                    <td>${user.is_actived ? 'Yes' : 'No'}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="error-message">
            <p>You are not authorized to access this page.</p>
            <a href="${pageContext.request.contextPath}/index.jsp">Go back to Home</a>
        </div>
    </c:otherwise>
</c:choose>


<!-- JavaScript -->
<script src="../../assets/vendors/js/vendor.bundle.base.js"></script>
<!-- Custom JavaScript -->
<script src="../../assets/js/off-canvas.js"></script>
<script src="../../assets/js/hoverable-collapse.js"></script>
<script src="../../assets/js/misc.js"></script>
<script src="../../assets/js/settings.js"></script>
<script src="../../assets/js/todolist.js"></script>
<!-- End Custom JavaScript -->
</body>
</html>
