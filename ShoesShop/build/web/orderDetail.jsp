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
        <style>
            .thumb_cart img {
                width: 150px;
                height: 150px;
                object-fit: cover;
            }
            .cart-list th, .cart-list td {
                vertical-align: middle;
            }
        </style>
        <jsp:include page="head.jsp"/>
    </head>

    <body>

        <div id="page">

            <jsp:include page="header.jsp" />

            <main class="bg_gray">
               	<main class="bg_gray">
                    <div class="container margin_30">
                        <div class="page_header">
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="#">Home</a></li>
                                    <li><a href="#">Category</a></li>
                                    <li>Page active</li>
                                </ul>
                            </div>
                            <h1>Order Detail</h1>
                        </div>
                        <div class="container my-5">
                            <div class="row mb-4">
                                <div class="col-md-4">
                                    <input type="text" id="searchTable" onkeyup="searchTable()" class="form-control" placeholder="Search...">
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table id="tableFilter" class="table table-striped table-hover cart-list">
                                    <thead class="table-dark">
                                        <tr>
                                            <th scope="col" data-sort="text">Product</th>
                                            <th scope="col" data-sort="number">Price</th>
                                            <th scope="col" data-sort="number">Quantity</th>
                                            <th scope="col" data-sort="number">Total</th>
                                            <th scope="col" data-sort="text">Modified At</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="o" items="${list}">
                                            <tr>
                                                <td>
                                                    <div class="d-flex align-items-center">
                                                        <div class="thumb_cart me-3">
                                                            <a href="product?service=detail&name=${o.product.name}&brand=${o.product.brand.name}">
                                                                <img src="${o.getImgView()}" alt="Image">
                                                                </div>
                                                            </a>
                                                            <span class="item_cart">${o.getNameView()}</span>
                                                        </div>
                                                </td>
                                                <td><strong>${o.getPriceName()}</strong></td>
                                                <td>${o.quantity}</td>
                                                <td><strong>${o.price}</strong></td>
                                                <td><strong>${o.modifiedAt}</strong></td>
                                                <td class="text-center"><a href="#" class="text-danger"><i class="bi bi-trash"></i></a></td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                    </tbody>
                                </table>
                                <table id="tableFilter" class="table table-striped table-hover cart-list table-bordered mt-4">
                                    <tr class="table-light">
                                        <td colspan="1" class="fw-bold">Summary</td>
                                        <td colspan="1" class="fw-bold"></td>
                                        <td colspan="1" class="fw-bold text-center"></td>
                                        <td colspan="1" class="fw-bold text-center">Quantity</td>
                                        <td colspan="1" class="fw-bold">${totalUnit}</td>
                                        <td colspan="1" class="fw-bold text-center">Total</td>
                                        <td class="fw-bold">${totalPrice}</td>
                                        <td></td>
                                    </tr>
                                    <tr class="table-secondary">
                                        <td colspan="7" class="fw-bold">Order Information</td>
                                        <td></td>
                                    </tr>
                                    <tr class="table-secondary">
                                        <td colspan="7">Order ID: <span class="fw-bold">${order.id}</span></td>
                                        <td></td>
                                    </tr>
                                    <tr class="table-secondary">
                                        <td colspan="7">Order Status: <span class="fw-bold">${order.getOrderStatus().name}</span></td>
                                        <td></td>
                                    </tr>
                                </table>
                            </div>
                            <h2 class="mb-4">Recipient Information</h2>
                            <table class="table table-bordered">
                                <tbody>
                                    <tr>
                                        <th scope="row" style="width: 200px;">Full Name</th>
                                        <td>${order.getUser().first_name} ${order.getUser().last_name}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Gender</th>
                                        <td>${order.getUser().gender?"Male":"Female"}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Email</th>
                                        <td>${order.getUser().email}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Mobile</th>
                                        <td>${order.getUser().telephone}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Address</th>
                                        <td>${order.getUser().getUserAddress().addressLine},${order.getUser().getUserAddress().city}, ${order.getUser().getUserAddress().country}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </main>
                <!--/main-->

            </main>
            <!--/main-->
            <jsp:include page="components/footer.jsp" />

        </div>
        <!-- page -->

        <div id="toTop"></div><!-- Back to top button -->

        <!-- COMMON SCRIPTS -->
        <script src="js/common_scripts.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/dataTable.js"></script>



    </body>
</html>