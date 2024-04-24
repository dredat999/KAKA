<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Deal</title>
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
            <div class="container-scroller">
                <!-- Sidebar -->
                <jsp:include page="/partials/_sidebar.jsp" />
                <!-- End Sidebar -->

                <div class="container-fluid page-body-wrapper">
                    <!-- Navbar -->
                    <jsp:include page="/partials/_navbar.jsp" />
                    <!-- End Navbar -->

                    <div class="main-panel">
                        <div class="content-wrapper">
                            <div class="page-title">
                                <h1>Add Deal</h1>
                            </div>

                            <div class="add-product-form">
                                <form action="DispatchServlet" method="post">
                                    <div class="form-group">
                                        <label for="name">Name:</label>
                                        <input type="text" id="name" name="name" class="form-control" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="description">Description:</label>
                                        <textarea id="description" name="description" rows="4" cols="50" class="form-control"></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="price">Price:</label>
                                        <input type="text" id="price" name="price" class="form-control" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="discount">Discount Percent:</label>
                                        <input type="number" id="discount" name="discount" min="0" max="100" class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label for="expiration">Expiration Date:</label>
                                        <input type="date" id="expiration" name="expiration" class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label for="category">Category:</label>
                                        <select id="category" name="category" class="form-control">
                                            <c:forEach var="category" items="${sessionScope.CATEGORY_LIST}">
                                                <option value="${category.id}">${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="inventory">Inventory:</label>
                                        <input type="number" id="inventory" name="inventory" min="0" class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <div class="form-check">
                                            <input type="checkbox" id="isActive" name="isActive" value="true" class="form-check-input">
                                            <label class="form-check-label" for="isActive">Is Active</label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary" name="action" value="Add Product"  onclick="return validateForm()">Add Product</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${sessionScope.USER_ROLE ne 1}">
            <!-- Display error message for unauthorized users -->

            <div class="error-message">
                <p>You are not authorized to access this page.</p>
                <a href="index.jsp">Go back to Home</a>
            </div>

        </c:if>
        <script>
            function validateForm() {
// Retrieve form inputs
                var name = document.getElementById('name').value;
                var price = 
// Perform validation
                if (name.trim() === '') {
                    alert('Name is required.');
                    return false; // Prevent form submission
                }
                if()
// Perform other validation checks for other fields if needed
// Return true if all validation checks pass
                return true;
            }
        </script>
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
