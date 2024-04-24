<%-- 
    Document   : list-product
    Created on : Apr 19, 2024, 8:19:53 PM
    Author     : HP
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Deal List</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <link rel="stylesheet" href="assets/vendors/jvectormap/jquery-jvectormap.css">
        <link rel="stylesheet" href="assets/vendors/flag-icon-css/css/flag-icon.min.css">
        <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.theme.default.min.css">
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="assets/css/style.css">
        <style>
            .error-message {
                text-align: center;
                font-size: 1.5em;
                margin-top: 50px;
            }
        </style>
        <!-- End layout styles -->
        <link rel="shortcut icon" href="assets/images/favicon.png" />
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
                            <h2 class="text-center">Show Deal List</h2>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Price</th>
                                        <th>Discount Percent</th>
                                        <th>Expiration</th>
                                        <th>Category ID</th>
                                        <th>Created At</th>
                                        <th>Inventory</th>
                                        <th>Is Active</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${requestScope.PRODUCT_LIST}">
                                        <tr>
                                            <td>${product.id}</td>
                                            <td>${product.name}</td>
                                            <td>${product.description}</td>
                                            <td>${product.price}</td>
                                            <td>${product.discountPercent}</td>
                                            <td>${product.expiration}</td>
                                            <td>${product.categoryId}</td>
                                            <td>${product.createdAt}</td>
                                            <td>${product.inventory}</td>
                                            <td>${product.isActive ? 'Yes' : 'No'}</td>
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

        <script src="assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <script src="assets/vendors/chart.js/Chart.min.js"></script>
        <script src="assets/vendors/progressbar.js/progressbar.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <script src="assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="assets/js/off-canvas.js"></script>
        <script src="assets/js/hoverable-collapse.js"></script>
        <script src="assets/js/misc.js"></script>
        <script src="assets/js/settings.js"></script>
        <script src="assets/js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="assets/js/dashboard.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
