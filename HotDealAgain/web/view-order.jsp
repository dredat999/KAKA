<%-- 
    Document   : view-order
    Created on : Apr 21, 2024, 8:14:41 PM
    Author     : HP
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Orders</title>
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
    <!-- Add your CSS stylesheets and JavaScript libraries here -->
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
                        <h1 class="text-center">View Orders</h1>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Create Date</th>
                                    <th>Amount</th>
                                    <th>Address</th>
                                    <th>Note</th>
                                    <th>User ID</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%-- Retrieve order information from the database based on the user ID --%>
                                <%-- Replace this section with your actual Java code to fetch orders from the database --%>
                                <%-- For demonstration purposes, I'll use a sample list of orders --%>
                                <c:forEach var="order" items="${requestScope.ORDER_LIST}">
                                    <tr>
                                        <td>${order.id}</td>
                                        <td>${order.createdAt}</td>
                                        <td>${order.amount}</td>
                                        <td>${order.address}</td>
                                        <td>${order.note}</td>
                                        <td>${order.userId}</td>
                                        <td>${order.status}</td>
                                        <td>
                                            <button type="button" onclick="toggleDetails(${order.id})">View Details</button>
                                        </td>
                                    </tr>
                                    <tr id="detailsRow${order.id}" style="display: none;">
                                        <td colspan="8">
                                            <table border="1">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Quantity</th>
                                                        <th>Price</th>
                                                        <th>Product ID</th>
                                                        <th>Order ID</th>
                                                        <th>Created At</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="orderDetail" items="${requestScope.ORDER_DETAIL_BY_ID}">
                                                        <tr>
                                                            <td>${orderDetail.id}</td>
                                                            <td>${orderDetail.quantity}</td>
                                                            <td>${orderDetail.price}</td>
                                                            <td>${orderDetail.productId}</td>
                                                            <td>${orderDetail.orderId}</td>
                                                            <td>${orderDetail.createdAt}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </td>
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

    <!-- container-scroller -->
    <!-- plugins:js -->
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
    <script>
        function toggleDetails(orderId) {
            var detailsRow = document.getElementById("detailsRow" + orderId);
            if (detailsRow.style.display === "none") {
                detailsRow.style.display = "table-row";
            } else {
                detailsRow.style.display = "none";
            }
        }
    </script>
    <!-- End custom js for this page -->

</body>
</html>
