<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Ansonika">
        <title>Allaia | Bootstrap eCommerce Template - ThemeForest</title>

        <!-- Favicons-->
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" type="image/x-icon" href="img/apple-touch-icon-57x57-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="img/apple-touch-icon-72x72-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="img/apple-touch-icon-114x114-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="img/apple-touch-icon-144x144-precomposed.png">

        <!-- GOOGLE WEB FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

        <!-- BASE CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <!-- SPECIFIC CSS -->
        <link href="css/blog.css" rel="stylesheet">

        <!-- YOUR CUSTOM CSS -->
        <link href="css/custom.css" rel="stylesheet">
        <jsp:include page="head.jsp"/>
    </head>

    <body>

        <div id="page">
            <jsp:include page="header.jsp" />


            <h1 class="text-center mb-4">My Order</h1>
            <div class="container mt-6">
                <div class="input-group">
                    <form action="myOrder">
                        <input type="text" id="searchTable" onkeyup="searchTable()" class="form-control" placeholder="Search...">
                    </form>
                </div>
            </div>
            <div class="container">

                <table id="tableFilter" class="table table-bordered">
                    <thead class="thead-dark bg-primary text-white">
                        <tr>
                            <th data-sort="text">ID</th>
                            <th data-sort="text">Customer</th>
                            <th data-sort="text">Email</th>
                            <th data-sort="number">Total Price</th>
                            <th data-sort="text">Date</th>
                            <th data-sort="text">Status</th>    
                            <th>Action</th>    
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>
                                    <a href="orderDetail?id=${order.id}">
                                        ${order.id}
                                    </a>
                                </td>
                                <td>${order.getUser().first_name} ${order.getUser().last_name}</td>
                                <td>${order.email}</td>
                                <td>${order.orderTotal}</td>
                                <td>${order.modifiedAt}</td>
                                <td>${order.getOrderStatus().name}</td>
                                <td>
                                    <form action="cancelOrder" method="POST">
                                        <a href="orderDetail?id=${order.id}" class="btn btn-success">
                                            Detail
                                        </a>
                                        <input type="text" id="id" name="id" value="${order.id}" hidden="">
                                        <c:if test="${order.getOrderStatus().id == 1}">
                                            <input type="submit" value="Cancel" class="btn btn-warning" />
                                        </c:if>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="components/notification.jsp"/>

                <jsp:include page="components/pagination.jsp"/>
            </div>
            <div class="ml-5">
                <jsp:include page="components/footer.jsp" />
            </div>

        </div>
        <!-- page -->

        <div id="toTop"></div><!-- Back to top button -->

        <!-- COMMON SCRIPTS -->
        <script src="js/common_scripts.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/dataTable.js"></script>


    </body>
</html>