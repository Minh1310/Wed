<%-- 
    Document   : product
    Created on : Jan 30, 2024, 1:49:22 PM
    Author     : Nhat Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, entity.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title><%=(String)request.getAttribute("title")%></title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="product/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="product/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="product/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="product/css/style.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="account/css/util.css">
        <link rel="stylesheet" type="text/css" href="account/css/main.css">
        <style>
            .content-items{
                /*                visibility:  hidden;*/
                display: none;

            }

            .pagination1{
                /*                visibility: visible;*/
                display: table-row;

            }
        </style>

    </head>

    <body>
        <!--        <form action="PurchaseOrderControl" method="post">
                    <input id="loadButton" style="display: none;" type="submit" value="getAll">
                    <input type="hidden" name="service" value="getAll">
                    <input type="hidden" name="pagination" value="1">
                </form>-->

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
        <div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a
                                href="#" class="text-white">FPT University</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a
                                href="#" class="text-white">minhbqhe171754@fpt.edu.vn</a></small>
                    </div>
                    <div class="top-link pe-2">
                        <a href="#" class="text-white"><small class="text-white mx-2">Privacy
                                Policy</small>/</a>
                        <a href="#" class="text-white"><small class="text-white mx-2">Terms of
                                Use</small>/</a>
                        <a href="#" class="text-white"><small class="text-white ms-2">Sales and
                                Refunds</small></a>
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="home.jsp" class="navbar-brand">
                        <h1 class="text-primary display-6">Eletrolic</h1>
                    </a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="home.jsp" class="nav-item nav-link">Home</a>
                            <a href="ProductsControl?service=getAll&pagination=1" 
                               class="nav-item nav-link">Product</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle  active"
                                   data-bs-toggle="dropdown">Orders</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="bill.jsp"
                                       class="dropdown-item active">Bill</a>
                                    <a href="PurchaseOrderControl?service=getAll&pagination=1" 
                                       class="dropdown-item">Purchase Order</a>
                                    <a href="PurchaseOrderLineControl?service=getAll&pagination=1"
                                       class="dropdown-item">Purchase Order Line</a>

                                </div>
                            </div>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle"
                                   data-bs-toggle="dropdown">Account</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="SupplierControl?service=getAll&pagination=1" class="dropdown-item">Manage account</a>
                                    <a href="SupplierCategoriesControl?service=getAll&pagination=1"
                                       class="dropdown-item">Categories</a>

                                </div>
                            </div>
                            <a href="SupplierTransactionsControl?service=getAll&pagination=1" class="nav-item nav-link">Transaction</a>
                        </div>
                        <div class="d-flex m-3 me-0">
                            <button
                                class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4"
                                data-bs-toggle="modal" data-bs-target="#searchModal"><i
                                    class="fas fa-search text-primary"></i></button>

                            <a href="CartControl?service=getAll"
                               class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>
                                <span
                                    class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                    style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                            </a>
                            <div class="nav-item dropdown"style="top:5px;">
                                <a href="#" class="my-auto">
                                    <i class="fas fa-user fa-2x" ></i>
                                </a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0"style="right:1px;">
                                    <%
                                        if(session.getAttribute("login")==null){
                                    %>
                                    <a href="index.html" 
                                       class="dropdown-item">Log in</a>
                                    <%
                                        }
                                    %>
                                    <a href="index.html" 
                                       class="dropdown-item">Log out</a>
                                    <%
                                        if(session.getAttribute("login")!=null){
                                    %>
                                    <a href="SupplierControl?service=update"
                                       class="dropdown-item">Update</a>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->


        <!-- Modal Search Start -->
        <form action="PurchaseOrderControl" method="get">
            <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content rounded-0">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body d-flex align-items-center">
                            <div class="input-group w-75 mx-auto d-flex">
                                <input type="search" name="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                                <button type="submit" id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>                                                                             
                                <input type="hidden" name="service" value="search">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <!-- Modal Search End -->


        <!-- Single Page Header start -->
        <div class="container-fluid page-header py-5">
            <h1 class="text-center text-white display-6">Order</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item"><a href="#">Pages</a></li>
                <li class="breadcrumb-item active text-white">Order</li>
            </ol>
        </div>
        <!-- Single Page Header End -->
        <%
            Bill ls = (Bill)request.getAttribute("data");
        %>
        <!-- Checkout Page Start -->
        <div class="container-fluid py-5">
            <div class="container py-5">
                <h1 class="mb-4">Billing details</h1>
                <form action="#">
                    <div class="row g-5">
                        <div class="col-md-12 col-lg-6 col-xl-7">
                            <div class="row">
                                <div class="col-md-12 col-lg-6">
                                    <div class="form-item w-100">
                                        <label class="form-label my-3">First Name<sup>*</sup></label>
                                        <input type="text" class="form-control" 
                                               placeholder="<%=ls.getFirstName()%>">
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-6">
                                    <div class="form-item w-100">
                                        <label class="form-label my-3">Last Name<sup>*</sup></label>
                                        <input type="text" class="form-control"
                                               placeholder="<%=ls.getLastName()%>">
                                    </div>
                                </div>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Phone number<sup>*</sup></label>
                                <input type="text" class="form-control"
                                       placeholder="<%=ls.getPhone()%>">
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Address <sup>*</sup></label>
                                <input type="text" class="form-control" 
                                       placeholder="<%=ls.getAddress()%>">
                            </div>

                        </div>
                        <div class="col-md-12 col-lg-6 col-xl-5">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Products</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            double total = 0;
                                            for(Cart list : ls.getList()){
                                                total = list.getUnitPrice()*list.getUnitPrice();
                                        %>
                                        <tr>
                                            <th scope="row">
                                                <div class="d-flex align-items-center mt-2">
                                                    <!--                                                    <img src="img/vegetable-item-2.jpg" class="img-fluid rounded-circle" style="width: 90px; height: 90px;" alt="">-->
                                                    <p class="mb-0 mt-4"><%=list.getProductID()%></p>
                                                </div>
                                            </th>
                                            <td class="py-5"><%=list.getProductName()%></td>
                                            <td class="py-5"><%=list.getQuantity()%></td>
                                            <td class="py-5"><%=list.getUnitPrice()%></td>
                                            <td class="py-5"><%=total%></td>
                                        </tr>
                                        <%  total=0;
                                            }%>
                                        <tr>
                                            <th scope="row">
                                            </th>
                                            <td class="py-5">
                                                <p class="mb-0 text-dark py-4">Shipping</p>
                                            </td>
                                            <td colspan="3" class="py-5">  
                                                <div class="form-check text-start">
                                                    <!-- <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-2" name="Shipping-1" value="Shipping">
                                                    <label class="form-check-label" for="Shipping-2">Flat rate: $15.00</label> -->
                                                </div>
                                                <div class="form-check text-start">
                                                    <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-1" name="Shipping-1" value="Shipping">
                                                    <label class="form-check-label" for="Shipping-1">Free Shipping</label>
                                                </div>
                                                <div class="form-check text-start">
                                                    <!-- <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-3" name="Shipping-1" value="Shipping">
                                                    <label class="form-check-label" for="Shipping-3">Local Pickup: $8.00</label> -->
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">
                                            </th>
                                            <td class="py-5">
                                                <p class="mb-0 text-dark text-uppercase py-3">TOTAL</p>
                                            </td>
                                            <td class="py-5"></td>
                                            <!-- <td class="py-5"></td> -->
                                            <td class="py-5">
                                                <div class="py-3 border-bottom border-top">
                                                    <p class="mb-0 text-dark"><%=ls.getTotal()%></p>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <%if(ls.getStatus()==0){%>
                            <div class="row g-4 text-center align-items-center justify-content-center pt-4">
                                <a href="BillControl?service=update&id=<%=ls.getBillID()%>&index=<%=request.getAttribute("index")%>">
                                    <button type="button" 
                                            class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary">
                                        Place Order
                                    </button>
                                </a>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Checkout Page End -->



        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
            <div class="container py-5">
                <div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5) ;">
                    <div class="row g-4">
                        <div class="col-lg-3">
                            <a href="#">
                                <h1 class="text-primary mb-0">Fruitables</h1>
                                <p class="text-secondary mb-0">Fresh products</p>
                            </a>
                        </div>
                        <div class="col-lg-6">
                            <div class="position-relative mx-auto">
                                <input class="form-control border-0 w-100 py-3 px-4 rounded-pill" type="number" placeholder="Your Email">
                                <button type="submit" class="btn btn-primary border-0 border-secondary py-3 px-4 position-absolute rounded-pill text-white" style="top: 0; right: 0;">Subscribe Now</button>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="d-flex justify-content-end pt-3">
                                <a class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-youtube"></i></a>
                                <a class="btn btn-outline-secondary btn-md-square rounded-circle" href=""><i class="fab fa-linkedin-in"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-item">
                            <h4 class="text-light mb-3">Why People Like us!</h4>
                            <p class="mb-4">typesetting, remaining essentially unchanged. It was 
                                popularised in the 1960s with the like Aldus PageMaker including of Lorem Ipsum.</p>
                            <a href="" class="btn border-secondary py-2 px-4 rounded-pill text-primary">Read More</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="d-flex flex-column text-start footer-item">
                            <h4 class="text-light mb-3">Shop Info</h4>
                            <a class="btn-link" href="">About Us</a>
                            <a class="btn-link" href="">Contact Us</a>
                            <a class="btn-link" href="">Privacy Policy</a>
                            <a class="btn-link" href="">Terms & Condition</a>
                            <a class="btn-link" href="">Return Policy</a>
                            <a class="btn-link" href="">FAQs & Help</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="d-flex flex-column text-start footer-item">
                            <h4 class="text-light mb-3">Account</h4>
                            <a class="btn-link" href="">My Account</a>
                            <a class="btn-link" href="">Shop details</a>
                            <a class="btn-link" href="">Shopping Cart</a>
                            <a class="btn-link" href="">Wishlist</a>
                            <a class="btn-link" href="">Order History</a>
                            <a class="btn-link" href="">International Orders</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-item">
                            <h4 class="text-light mb-3">Contact</h4>
                            <p>Address: 1429 Netus Rd, NY 48247</p>
                            <p>Email: Example@gmail.com</p>
                            <p>Phone: +0123 4567 8910</p>
                            <p>Payment Accepted</p>
                            <img src="img/payment.png" class="img-fluid" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->

        <!-- Copyright Start -->
        <div class="container-fluid copyright bg-dark py-4">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                        <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
                    </div>
                    <div class="col-md-6 my-auto text-center text-md-end text-white">
                        <!--/*** This template is free as long as you keep the below authorâs credit link/attribution link/backlink. ***/-->
                        <!--/*** If you'd like to use the template without the below authorâs credit link/attribution link/backlink, ***/-->
                        <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                        Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a> Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->



        <!-- Back to Top -->
        <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   


        <!-- JavaScript Libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="product/lib/easing/easing.min.js"></script>
        <script src="product/lib/waypoints/waypoints.min.js"></script>
        <script src="product/lib/lightbox/js/lightbox.min.js"></script>
        <script src="product/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="product/js/main.js"></script>
        <script>
            function update(ProductID, quantity) {
                window.location.href =
                        "CartControl?service=update&ProductID=" + ProductID + "&quantity=" + quantity;
            }
        </script>
        <script>

            // Retrieve the message from the server-side using 
            var myString = <%=(String)request.getAttribute("mess")%>;

            // Check if the message is defined
            if (myString !== null) {
                window.alert(myString);

            }
        </script>
        <script>
            // Check if it's the first time accessing the page or a page reload
            if (!sessionStorage.getItem('pageLoaded')) {
                // Set a flag in sessionStorage to indicate that the page has been loaded
                sessionStorage.setItem('pageLoaded', true);
                // Programmatically click the button
                document.getElementById('loadButton').click();
            } else {
                // Code to execute if it's not the first time accessing the page
                console.log('Page already loaded');
            }

            // Attach a click event listener to the button
            document.getElementById('loadButton').addEventListener('click', function () {
                // Additional code to execute when the button is clicked
                console.log('Button clicked');
                // You can add more actions or redirect to another page
            });
        </script>
        <script>
            function showContent(tagclassName) {
                // Hide all content
                var allContent = document.getElementsByClassName('content-items');
                for (var i = 0; i < allContent.length; i++) {
                    allContent[i].style.display = 'none';
                }

                // Show the selected content

                var selectedContent = document.getElementsByClassName(tagclassName);
                for (var i = 0; i < selectedContent.length; i++) {
                    selectedContent[i].style.display = 'table-row';
                }
            }
            document.addEventListener("DOMContentLoaded", function () {
                var paginationLinks = document.querySelectorAll('.pagination a');

                var itemsPerPage = 5;
                var currentPage = 1;

                function updatePagination() {
                    // Update 'active' class for the current page
                    paginationLinks.forEach(function (innerLink) {
                        innerLink.classList.remove('active');
                    });
                    paginationLinks[currentPage].classList.add('active');

                    // Calculate the subset of pagination links to display
                    var startIndex = Math.max(0, currentPage - 1);
                    var endIndex = Math.min(startIndex + itemsPerPage, );

                    // Adjust scroll position
                    var scrollPosition = startIndex * (paginationLinks[0].offsetWidth + 10);

                    // Display the subset of pagination links
                    paginationLinks.forEach(function (link, index) {

                        if (index === 0 || index === paginationLinks.length - 1 || (index >= startIndex && index <= endIndex)) {
                            link.style.display = 'inline-block';
                        } else {
                            link.style.display = 'none';
                        }

                    });
                }


                paginationLinks.forEach(function (link, index) {
                    link.addEventListener('click', function (event) {
                        //  event.preventDefault();
                        // Clicked on a specific page
                        if (link.id === 'prevPage') {
                            // Previous page
                            currentPage = Math.max(1, currentPage - 1);
                            //        paginationLinks[currentPage].classList.add('active');
                            //        paginationLinks[currentPage].click();
                        } else if (link.id === 'nextPage') {
                            // Next page
                            currentPage = Math.min(paginationLinks.length - 2, currentPage + 1);
                            //  paginationLinks[currentPage].classList.add('active');
                            //   paginationLinks[currentPage].click();
                        } else {
                            currentPage = index;
                        }
                        // Update pagination
                        updatePagination();
                    });
                });


                // Initial display
                updatePagination();
            });
        </script>
    </body>

</html>