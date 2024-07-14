<%-- 
    Document   : product_list
    Created on : Jun 4, 2024, 2:49:59 PM
    Author     : Nhat Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*, model.*,util.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Ansonika">
        <title>Cart</title>

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
        <link href="css/listing.css" rel="stylesheet">
        <link href="css/cart.css" rel="stylesheet">

        <!-- YOUR CUSTOM CSS -->
        <link href="css/custom.css" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <!-- Include SweetAlert CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="css/minh.css">
    </head>

    <body>

        <div id="page" class="theia-exception">

            <jsp:include page="header.jsp"/>

            <main>
                <div class="container margin_30">
                    <div class="row">
                        <aside class="col-lg-3" id="sidebar_fixed">
                            <jsp:include page="sider.jsp"/>
                        </aside>
                        <!-- /col -->
                        <div class="col-lg-9">                         
                            <div class="top_banner">                              
                                <div class="opacity-mask d-flex align-items-center" data-opacity-mask="rgba(0, 0, 0, 0.3)">
                                    <div class="container pl-lg-5">
                                        <h1>
                                            <a href="${slider.backLink}" style="color:#ffffff;">
                                                ${slider.title}
                                            </a>
                                        </h1>
                                    </div>
                                </div>
                                <a href="${slider.backLink}">
                                    <img src="${slider.image}" class="img-fluid" alt="">
                                </a>

                            </div>
                            <!-- /top_banner -->
                            <div id="stick_here"></div>
                            <div class="toolbox elemento_stick add_bottom_30">
                                <div class="container">
                                    <ul class="clearfix">
                                        <li>
                                        </li>
                                        <li>
                                            <!--                                            <a href="#0"><i class="ti-view-grid"></i></a>
                                                                                        <a href="listing-row-1-sidebar-left.html"><i class="ti-view-list"></i></a>-->
                                        </li>
                                        <li>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="container margin_30">
                                <div class="page_header">
                                    <div class="breadcrumbs">
                                        <!--                                        <ul>
                                                                                    <li><a href="#">Home</a></li>
                                                                                    <li><a href="#">Category</a></li>
                                                                                    <li>Page active</li>
                                                                                </ul>-->
                                    </div>
                                    <h1>Cart page</h1>
                                </div>
                                <!-- /page_header -->
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th scope="col">Buy</th>
                                            <th scope="col">ID</th>
                                            <th scope="col" class="wider-col">Title</th>
                                            <th scope="col">Color</th>
                                            <th scope="col">Size</th>
                                            <th scope="col">Price</th>
                                            <th scope="col" class="wider-col">Quantity</th>
                                            <th scope="col">Subtotal</th>
                                            <th scope="col">Remove</th>
                                        </tr>
                                    </thead>
                                    <tbody id="container_cart">                     
                                        <tr>
                                            <th><input type="checkbox" name="product" value="1"></th>
                                            <th scope="row">2</th>
                                            <td class="wider-col">Data 2</td>
                                            <td>Data 3</td>
                                            <td>Data 4</td>
                                            <td>Data 5</td>
                                            <td class="wider-col">
                                                <div class="qty-input">
                                                    <button class="qty-count qty-count_minus" data-action="minus" type="button" onclick="update('minus', '1')">-</button>
                                                    <input class="product-qty" type="number" id="quantity_1" min="1" value="1" onchange="update('minus', '1')">
                                                    <button class="qty-count qty-count_add" data-action="add" type="button" onclick="update('add', '#quantity_1')">+</button>
                                                </div>
                                            </td>
                                            <td>Data 7</td>
                                            <td>
                                                <i class="ti-trash" onclick="remove(3)"></i>
                                            </td>
                                        </tr>                        
                                        <!-- Add more rows as needed -->
                                    </tbody>
                                </table>

                                <div class="row add_top_30 flex-sm-row-reverse cart_actions">
                                    <div class="col-sm-4 text-end">
                                        <button type="button" class="btn_1 border border-secondary px-4 py-2 rounded-pill" 
                                                onclick="getAllProduct()">Select All</button>
                                        <button type="button" class="btn_1 border border-secondary px-4 py-2 rounded-pill" 
                                                onclick="reDirect('product')">Buy More</button>
                                    </div>
                                </div>
                            </div> 
                            <div class="box_cart">
                                <div class="container">
                                    <div class="row justify-content-end">
                                        <div class="col-xl-4 col-lg-4 col-md-6">
                                            <ul>
                                                <li>
                                                    <span>Subtotal</span> <p id="total_product1">$0.00</p>
                                                </li>
                                                <li>
                                                    <span>Shipping</span> $0.00
                                                </li>
                                                <li>
                                                    <span>Total</span> <p id="total_product2">$0.00</p>
                                                </li>
                                            </ul>
                                            <button onclick="checkout()" class="btn_1 full-width cart">Checkout</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /box_cart -->
                            <form action="contact" method="get" style="display: none;">
                                <input type="hidden" id="checkout" name="checkout" value="2">
                                <button type="hidden" id="checkoutSubmit"></button>
                            </form>
                        </div>
                        <!-- /col --> 
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </main>
            <!-- /main -->

            <jsp:include page="footer.jsp"/>
        </div>
        <!-- page -->

        <div id="toTop"></div><!-- Back to top button -->
        <jsp:include page="modal.jsp"/>
        <script src="js/minh_js.js"></script>
</html>
