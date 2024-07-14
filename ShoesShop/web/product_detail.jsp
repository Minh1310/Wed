<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*, dao.*, util.* " %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <div id="page" class="theia-exception">	
            <jsp:include page="header.jsp"/>
            <main class="bg_gray">
                <div class="top_banner">
                    <div class="opacity-mask d-flex align-items-center" data-opacity-mask="rgba(0, 0, 0, 0.3)">
                        <div class="container">
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

                <!-- /toolbox -->
                <div class="container margin_30">
                    <div class="row">
                        <!-- Sider -->
                        <div class="col-lg-3"> 
                            <jsp:include page="sider.jsp"/> 
                        </div>

                        <!-- Body content -->
                        <div class="col-lg-9 bg_gray">
                            <div class="container margin_30">
                                <div class="page_header">
                                    <h1>${product.name}</h1>
                                </div>
                                <!-- /page_header -->
                                <div class="row justify-content-center">
                                    <div class="col-lg-8">
                                        <div class="owl-carousel owl-theme prod_pics magnific-gallery">
                                            <div class="item">
                                                <a href="${product.img1}" title="Photo title" data-effect="mfp-zoom-in"><img src="${product.img1}" alt=""></a>
                                            </div>
                                            <!-- /item -->
                                            <div class="item">
                                                <a href="${product.img2}" title="Photo title" data-effect="mfp-zoom-in"><img src="${product.img2}" data-src="${product.img2}" alt="" class="owl-lazy"></a>
                                            </div>
                                            <!-- /item -->
                                        </div>
                                        <!-- /carousel -->
                                    </div>
                                </div>
                                <!-- /row -->
                            </div>
                            <!-- /container -->
                            <form id="detailFormAddToCart">
                                <div class="bg_white">
                                    <div class="container margin_60_35">
                                        <div class="row justify-content-between">
                                            <div class="col-lg-5">
                                                <div class="prod_info version_2">
                                                    <span class="rating">
                                                        <%
                                                        // Access the 'element' object from the pageContext
                                                            Object elementObj1 = pageContext.findAttribute("product");
                                                            if (elementObj1 != null) {
                                                                // Cast it to the expected type
                                                                SubProducts element = (SubProducts) elementObj1;

                                                                // Initialize variables for calculating the average rating
                                                                int[] ratings = element.getRating();
                                                                int sum = 0;
                                                                for (int rating : ratings) {
                                                                    sum += rating;
                                                                }

                                                                // Calculate the average rating
                                                                int rate = (ratings.length > 0) ? (sum / ratings.length) : 0;
                                                                request.setAttribute("rate", rate);
                                                            }
                                                        %>
                                                        <c:set var="rate" value="${requestScope.rate}" />
                                                        <c:forEach begin="1" end="${rate}" step="1">
                                                            <i class="icon-star voted"></i>
                                                        </c:forEach>
                                                        <c:forEach begin="${rate + 1}" end="5" step="1">
                                                            <i class="icon-star"></i>
                                                        </c:forEach>
                                                    </span>
                                                    <small>Brand: ${product.brand_name}</small>
                                                        <p>
                                                            <small >
                                                               Categories: ${category} 
                                                            </small>
                                                        </p>

                                                    <c:forEach var="element" items="${product.description}">
                                                        <p>${element}</p>
                                                    </c:forEach> 

                                                </div>
                                            </div>
                                            <div class="col-lg-7">
                                                <div class="prod_options version_2">
                                                    <div class="row">
                                                        <label class="col-xl-7 col-lg-5 col-md-6 col-6"><strong>color</strong> </label>
                                                        <div class="col-xl-5 col-lg-5 col-md-6 col-6">
                                                            <div class="custom-select-form">
                                                                <select class="wide" name="color">
                                                                    <option  selected="">Color</option>
                                                                    <c:forEach var="element" items="${product.color}">
                                                                        <option  value="${element}">${element}</option>
                                                                    </c:forEach> 
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <label class="col-xl-7 col-lg-5 col-md-6 col-6"><strong>Size</strong> - Size Guide <a href="#0" data-bs-toggle="modal" data-bs-target="#size-modal"><i class="ti-help-alt"></i></a></label>
                                                        <div class="col-xl-5 col-lg-5 col-md-6 col-6">
                                                            <div class="custom-select-form">
                                                                <select class="wide" name="size">
                                                                    <option  selected="">Size</option>
                                                                    <c:forEach var="element" items="${product.size}">
                                                                        <option  value="${element}">${element}</option>
                                                                    </c:forEach> 
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <label class="col-xl-7 col-lg-5  col-md-6 col-6"><strong>Quantity</strong></label>
                                                        <div class="col-xl-5 col-lg-5 col-md-6 col-6">
                                                            <div class="numbers-row">
                                                                <input type="text" value="1" id="quantity_1" class="qty2" name="quantity">
                                                                <div class="inc button_inc">+</div>
                                                                <div class="dec button_inc">-</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-3">
                                                        <div class="col-lg-7 col-md-6">
                                                            <c:if test="${product.discount_status == 1}">
                                                                <div class="price_main">
                                                                    <span class="new_price">$${product.price*(100-product.discount)/100}</span>
                                                                    <span class="percentage">-${product.discount}%</span> 
                                                                    <span class="old_price">$${product.price}</span>
                                                                </div>
                                                            </c:if>                                 
                                                            <c:if test="${product.discount_status == 0}">
                                                                <div class="price_main">
                                                                    <span class="new_price">$${product.price}</span>>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                        <div class="col-lg-5 col-md-6">
                                                            <input type="hidden" name="name" value="${product.name}">
                                                            <input type="hidden" name="service" value="addCart">
                                                            <div class="btn_add_to_cart">
                                                                <input type="button" value="Add to cart"class="btn_1" id="addFromDetail">                        
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /row -->
                                    </div>
                                </div>
                                <!-- /bg_white -->
                            </form>
                        </div>
                    </div>
                </div>
            </main>
            <!-- /main -->            
            <jsp:include page="footer.jsp"/>
            <!--/footer-->
        </div>
        <!-- COMMON SCRIPTS -->
        <jsp:include page="modal.jsp"/>
        <!-- SPECIFIC SCRIPTS -->
        <script src="js/product_detail.js"></script>
        <script src="js/sticky_sidebar.min.js"></script>
        <script src="js/specific_listing.js"></script>
    </body>
</html>