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
        <title>Dream</title>

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
<!--                                            <div class="sort_select">
                                                <select name="sort" id="sort">
                                                    <option value="popularity" selected="selected">Sort by popularity</option>
                                                    <option value="rating">Sort by average rating</option>
                                                    <option value="date">Sort by newness</option>
                                                    <option value="price">Sort by price: low to high</option>
                                                    <option value="price-desc">Sort by price: high to
                                                </select>
                                            </div>-->
                                        </li>
                                        <li>
                                            <a href="#0"><i class="ti-view-grid"></i></a>
                                            <a href="listing-row-1-sidebar-left.html"><i class="ti-view-list"></i></a>
                                        </li>
                                        <li>
<!--                                            <a href="#0" class="open_filters">
                                                <i class="ti-filter"></i><span>Filters</span>
                                            </a>-->
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div id="containerProduct">
                                <!-- /toolbox -->
                                <c:forEach var="element" items="${products}">
                                    <div class="row row_item">
                                        <div class="col-sm-4">
                                            <figure>
                                                <c:if test="${element.productStatus.name eq 'new'}">
                                                    <span class="ribbon new">${element.productStatus.name}</span>

                                                </c:if>

                                                <c:if test="${element.productStatus.name eq 'hot'}">
                                                    <span class="ribbon hot">${element.productStatus.name}</span>

                                                </c:if> 

                                                <c:if test="${element.productStatus.name eq 'common'}">
                                                    <c:if test="${element.discount.active}">
                                                        <span class="ribbon off">-${element.discount.discountPercent}%</span>

                                                    </c:if> 
                                                </c:if>
                                                <a href="product?service=detail&name=${element.name}">
                                                    <img class="img-fluid lazy" src="${element.img1}" data-src="${element.img1}" alt="">
                                                </a>
                                            </figure>
                                        </div>
                                        <div class="col-sm-8">
                                            <div class="rating">
                                                <c:forEach begin="1" end="${element.ratting.ratting}" step="1">
                                                    <i class="icon-star voted"></i>
                                                </c:forEach>
                                                <c:forEach begin="${element.ratting.ratting + 1}" end="5" step="1">
                                                    <i class="icon-star"></i>
                                                </c:forEach>
                                            </div>
                                            <a href="product?service=detail&name=${element.name}">
                                                <h3>${element.name}</h3>
                                            </a>
                                            <p>Description for ${element.name}</p>
                                            <div class="price_box">
                                                <c:if test="${element.discount.active}">                                              
                                                    <span class="new_price">$${String.format("%.2f", element.price*(100-element.discount.discountPercent)/100)}</span>
                                                    <span class="old_price">$${element.price}</span>
                                                </c:if>
                                                <c:if test="${!element.discount.active}">                                              
                                                    <span class="new_price">$${element.price}</span>                                                
                                                </c:if>  
                                            </div>
                                            <ul>
                                                <li><a class="btn_1" type="button" data-toggle="modal" data-target="#exampleModal" 
                                                       data-name="${element.name}">
                                                        Add to cart</a>
                                                </li>
                                                <li><a class="btn_1 gray tooltip-1" href="feedback?name=${element.name}">
                                                        <i class="ti-comment-alt"></i>
                                                        <span>Feedback</span>
                                                    </a>
                                                </li>
                                            </ul>                
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>


                            <div class="items" id="items"></div>
                            <!-- /row_item -->
                            <div class="pagination__wrapper d-flex justify-content-center my-2">
                                <ul class="pagination"  id="pagination">

                                </ul>
                            </div>                         
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

        <!-- SPECIFIC SCRIPTS -->
        <script src="js/carousel-home.js"></script>
        <script src="js/sticky_sidebar.min.js"></script>
        <script src="js/specific_listing.js"></script>
        <script src="js/jquery.cookiebar.js"></script>
        <script src="js/validate.js"></script>   

        <script src="js/minh.js"></script>   


    </body>
</html>
