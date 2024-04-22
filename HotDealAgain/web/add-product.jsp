<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
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
         <c:if test="${sessionScope.USER_ROLE eq 1}">
        <h1>Add Product</h1>
        <form action="AddProductServlet" method="post">
            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name" required><br><br>

            <label for="description">Description:</label><br>
            <textarea id="description" name="description" rows="4" cols="50"></textarea><br><br>

            <label for="price">Price:</label><br>
            <input type="number" id="price" name="price" required><br><br>

            <label for="discount">Discount Percent:</label><br>
            <input type="number" id="discount" name="discount" min="0" max="100"><br><br>

            <label for="expiration">Expiration Date:</label><br>
            <input type="date" id="expiration" name="expiration"><br><br>

            <label for="category">Category:</label><br>
            <select id="category" name="category">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select><br><br>

            <label for="inventory">Inventory:</label><br>
            <input type="number" id="inventory" name="inventory" min="0"><br><br>

            <label for="isActive">Is Active:</label><br>
            <input type="checkbox" id="isActive" name="isActive" value="true"><br><br>

            <input type="submit" value="Add Product">
        </form>
         </c:if>

        <c:if test="${sessionScope.USER_ROLE ne 1}">
            <!-- Display error message for unauthorized users -->

            <div class="error-message">
                <p>You are not authorized to access this page.</p>
                <a href="index.jsp">Go back to Home</a>
            </div>

        </c:if>
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
