<%-- 
    Document   : home1
    Created on : Jun 1, 2024, 12:22:01 AM
    Author     : Nhat Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*, model.*, mdao.*, util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp"/>
        <style>
            .h-111 {
                height: 66.67vh !important; /* Two-thirds of the viewport height */
            }

        </style>
        
    </head>
    <body>
        <div id="page">
            <jsp:include page="header.jsp"/>          
            <main class="bg_gray">
                <div id="carousel-home" class= h-111">
                    <div id="carouselExampleIndicators" class="carousel slide custom-carousel h-111" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner h-111">
                            <div class="carousel-item h-111 active">
                                <a href="${slider.get(0).backLink}">
                                    <img class="d-block w-100 h-111" src="${slider.get(0).image}" alt="First slide">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h2 class="owl-slide-animated owl-slide-title">${slider.get(0).title}</h2>
                                    </div>
                                </a>					
                            </div> 
                            <c:forEach var="element" items="${slider.subList(1,3)}">                          
                                <div class="carousel-item h-111 ">
                                    <a href="${element.backLink}">
                                        <img class="d-block w-100 h-111" src="${element.image}" alt="First slide">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h2 class="owl-slide-animated owl-slide-title">${element.title}</h2>
                                        </div>
                                    </a>					
                                </div>                     
                            </c:forEach>

                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                    <div id="icon_drag_mobile"></div>

                </div>
                <!--/carousel-->


                <ul id="banners_grid" class="clearfix">
                    <c:forEach var="element" items="${slider.subList(3,6)}">
                        <li>
                            <a href="${element.backLink}" class="img_container">
                                <img src="${element.image}" data-src="${element.image}" alt="" class="lazy">
                                <div class="short_info opacity-mask" data-opacity-mask="rgba(0, 0, 0, 0.5)">
                                    <h3>${element.title}</h3>                       
                                    <div><span class="btn_1">Shop Now</span></div>
                                </div>
                            </a>
                        </li> 
                    </c:forEach>
                </ul>
                <!--/banners_grid -->
                <div class="container margin_60_35">
                    <div class="row small-gutters">
                        <div class="col-lg-9">
                            <div class="main_title">
                                <h2>HOT BLOGS</h2>
                                <span>BLOG</span>
                            </div>
                            <!-- /widget -->
                            <div class="row">

                                <c:forEach var="element" items="${hotBlog}">

                                    <div class="col-md-6">
                                        <article class="blog">
                                            <figure>
                                                <a href="blogDetail?id=${element.id}"><img src="${element.cover_img}" alt="">
                                                    <div class="preview"><span>Read more</span></div>
                                                </a>
                                            </figure>
                                            <div class="post_info">
                                                <small><fmt:formatDate value="${element.created_at}" pattern="dd.MM.yyyy"/></small>
                                                <h2><a href="blogDetail?id=${element.id}">${element.title}</a></h2>
                                                <p>${element.context.substring(0,70)}...</p>
                                            </div>
                                        </article>
                                        <!-- /article -->
                                    </div>
                                    <!-- /col -->
                                </c:forEach>
                            </div>
                            <!-- /row -->
                        </div>
                        <!-- /col -->

                        <aside class="col-lg-3">
                            <div class="main_title">
                                <h2>LATEST BLOGS</h2>
                                <span>BLOG</span>
                            </div>
                            <!-- /widget -->
                            <div class="widget">
                                <!-- <div class="widget-title">
                                        <h4>Latest Post</h4>
                                </div> -->
                                <ul class="comments-list">
                                    <c:forEach var="element" items="${latestBlog}">
                                        <li>
                                            <div class="alignleft">
                                                <a href="blogDetail?id=${element.id}"><img src="${element.cover_img}" alt=""></a>
                                            </div>
                                            <small><fmt:formatDate value="${element.created_at}" pattern="dd.MM.yyyy"/></small>      
                                            <h3><a href="blogDetail?id=${element.id}" title="">${element.title}</a></h3>
                                            <small>${element.context.substring(0,50)}...</small>
                                        </li>

                                    </c:forEach>
                                </ul>
                                <div class="d-flex justify-content-center my-4">
                                    <a href="#"
                                       class="btn border border-secondary px-3 py-2 rounded-pill text-primary w-100">Contact</a>
                                </div>
                            </div>
                            <!-- /widget -->
                        </aside>
                        <!-- /aside -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->


                <!--Slider-->
                <a>
                    <div class="featured lazy" data-bg="url(${slider.get(6).image}">
                    <div class="opacity-mask d-flex align-items-center" data-opacity-mask="rgba(0, 0, 0, 0.5)">
                        <div class="container margin_60">
                            <div class="row justify-content-center justify-content-md-start">
                                <div class="col-lg-6 wow" data-wow-offset="150">
                                    <h3>${slider.get(6).title}</h3>
                                    <p>${slider.get(6).content}</p>
                                    <div class="feat_text_block">
                                        <a class="btn_1" href="product" role="button">Shop Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                </a>
                
                <!-- /featured -->

                <div class="container margin_60_35">
                    <div class="main_title">
                        <h2>Featured</h2>
                        <span>Products</span>

                    </div>
                    <div class="owl-carousel owl-theme products_carousel">
                        <c:forEach var="element" items="${featured}">
                            <div class="item">
                                <div class="grid_item">
                                    
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
                  
                                    <figure>
                                        <a href="product?service=detail&name=${element.name}">
                                            <img class="owl-lazy" src="${element.img1}" data-src="${element.img1}" alt="">
                                        </a>
                                    </figure>
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
                                        <c:if test="${element.productStatus.name ne 'off'}">
                                            <div class="price_box">
                                                <c:if test="${element.discount.active}">                                              
                                                    <span class="new_price">$${String.format("%.2f", element.price*(100-element.discount.discountPercent)/100)}</span>
                                                    <span class="old_price">$${element.price}</span>
                                                </c:if>
                                                <c:if test="${!element.discount.active}">                                              
                                                    <span class="new_price">$${element.price}</span>                                                
                                                </c:if>     
                                            </div>
                                        </c:if>
                                        <c:if test="${element.productStatus.name eq 'off'}">
                                            <p>Cant buy this product </p>
                                        </c:if>
                                    
                                    <ul>
                                        <li>                               
                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" 
                                                    data-name="${element.name}" 
                                                    >                  
                                                <i class="ti-shopping-cart"></i>
                                            </button>                                               
                                        </li>
                                    </ul>
                                </div>
                                <!-- /grid_item -->
                            </div>
                            <!-- /item -->
                        </c:forEach>

                    </div>
                    <!-- /products_carousel -->
                </div>
                <!-- /container -->

                <div class="bg_gray">
                    <div class="container margin_30">
                        <div id="brands" class="owl-carousel owl-theme">
                            <div class="item">
                                <a href="#0"><img src="img/brands/adidas.png" data-src="img/brands/adidas.png" alt="" class="owl-lazy"></a>
                            </div><!-- /item -->
                            <div class="item">
                                <a href="#0"><img src="img/brands/crocs.png" data-src="img/brands/crocs.png" alt="" class="owl-lazy"></a>
                            </div><!-- /item -->
                            <div class="item">
                                <a href="#0"><img src="img/brands/dd.png" data-src="img/brands/dd.png" alt="" class="owl-lazy"></a>
                            </div><!-- /item -->
                            <div class="item">
                                <a href="#0"><img src="img/brands/jaxson.png" data-src="img/brands/jaxson.png" alt="" class="owl-lazy"></a>
                            </div><!-- /item -->
                            <div class="item">
                                <a href="#0"><img src="img/brands/jordan.png" data-src="img/brands/jordan.png" alt="" class="owl-lazy"></a>
                            </div><!-- /item -->
                            <div class="item">
                                <a href="#0"><img src="img/brands/nike.png" data-src="img/brands/nike.png" alt="" class="owl-lazy"></a>
                            </div><!-- /item -->
                            <div class="item">
                                <a href="#0"><img src="img/brands/puma.png" data-src="img/brands/puma.png" alt="" class="owl-lazy"></a>
                            </div><!-- /item -->
                        </div><!-- /carousel -->
                    </div><!-- /container -->
                </div>
                <!-- /bg_gray -->    
            </main>
            <!-- /main -->
            <jsp:include page="footer.jsp"/>   
        </div>
        <div id="toTop"></div><!-- Back to top button -->

        <jsp:include page="modal.jsp"/>



        <!-- SPECIFIC SCRIPTS -->
        <script src="js/carousel-home.js"></script>
        <script src="js/sticky_sidebar.min.js"></script>
        <script src="js/jquery.cookiebar.js"></script>
        <script src="js/validate.js"></script>
        <script>
            $(document).ready(function () {
                'use strict';
                $.cookieBar({
                    fixed: true
                });
            });
            $('.carousel').carousel({
                interval: 2000
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    </body>
</html>
