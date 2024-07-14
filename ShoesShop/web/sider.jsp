<%-- 
    Document   : sider
    Created on : May 15, 2024, 6:28:37 PM
    Author     : Nhat Anh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*, dao.*, util.*" %>


<form action="product" method="post">
    <div class="input-group">
        <input type="text" class="form-control" name="name" placeholder="Search products">
        <input type="hidden" name="service" value="search">
        <div class="input-group-append">
            <button class="btn btn-secondary" type="submit">
                <i class="ti-search"></i>
            </button>
        </div>        
    </div>
</form>

<div class="filter_col">
    <div class="inner_bt"><a href="#" class="open_filters"><i class="ti-close"></i></a></div>
    <!-- /filter_type -->
    <div class="filter_type version_2">
        <h4><a href="#filter_1" data-bs-toggle="collapse" class="closed">Categories</a></h4>
        <div class="collapse" id="filter_1">
            <ul>
                <c:forEach var="element" items="${categorySider}">
                    <li>
                        <div class="filter_type version_2">
                            <h4><a href="#${element.key.name}" data-bs-toggle="collapse" class="arrowRight">${element.key.name}</a></h4>
                            <div class="collapse" id="${element.key.name}">
                                <ul>
                                    <li><a href="product?service=viewByCategory&category=${element.key.name}"> All</a></li>
                                        <c:forEach var="elementValue" items="${element.value.nameSubCategories}">
                                        <li><a href="product?service=viewByCategory&category=${element.key.name}&subCategory=${elementValue}"> 
                                                ${elementValue}
                                            </a>
                                        </li>
                                    </c:forEach>  
                                </ul>
                            </div>
                        </div>
                    </li>
                </c:forEach>               
            </ul>
        </div>
        <!-- /filter_type -->
    </div>
    <form action="product" method="post">
        <!-- /filter_type -->
        <div class="filter_type version_2">
            <h4><a href="#filter_2" data-bs-toggle="collapse" class="closed">Color</a></h4>
            <div class="collapse" id="filter_2">
                <ul>
                    <c:forEach var="element" items="${colorSider}">
                        <li>
                            <label class="container_check">${element}
                                <input type="checkbox" name="color" value="${element}">
                                <span class="checkmark"></span>
                            </label>
                        </li>
                    </c:forEach>                                   
                </ul>
            </div>
        </div>
        <!-- /filter_type -->
        <div class="filter_type version_2">
            <h4><a href="#filter_3" data-bs-toggle="collapse" class="closed">Brands</a></h4>
            <div class="collapse" id="filter_3">
                <ul>
                    <c:forEach var="element" items="${brandSider}">
                        <li>
                            <label class="container_check">${element.name}
                                <input type="checkbox" name="brand" value="${element.name}">
                                <span class="checkmark"></span>
                            </label>
                        </li> 
                    </c:forEach>                                   
                </ul>
            </div>
        </div>
        <!-- /filter_type -->
        <div class="filter_type version_2">
            <h4><a href="#filter_4" data-bs-toggle="collapse" class="closed">Price</a></h4>
            <div class="collapse" id="filter_4">
                <ul>
                    <c:forEach var="element" items="${category}">

                    </c:forEach>
                    <li>
                        <label class="container_check">$0 — $50
                            <input type="checkbox" name="price" value="1">
                            <span class="checkmark"></span>
                        </label>
                    </li>
                    <li>
                        <label class="container_check">$50 — $100
                            <input type="checkbox" name="price" value="2">
                            <span class="checkmark"></span>
                        </label>
                    </li>
                    <li>
                        <label class="container_check">$100 — $150
                            <input type="checkbox"name="price" value="3">
                            <span class="checkmark"></span>
                        </label>
                    </li>
                    <li>
                        <label class="container_check">$150>
                            <input type="checkbox" name="price" value="4">
                            <span class="checkmark"></span>
                        </label>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /filter_type -->
        <div class="buttons d-flex justify-content-center my-4">
            <button type="submit" class="btn_1 border border-secondary px-3 py-2 rounded-pill">
                Filter
            </button>
            <input type="hidden"  name="service" value="viewByFilter">
            <a href="product" class="btn_1 gray border border-secondary px-3 py-2 rounded-pill">Reset</a>
        </div>
    </form>
</div>
<div class="col-lg-12">
    <h4 class="mb-3">Latest Products</h4>
    <c:forEach var="element" items="${latestProduct}"> 

        <div class="d-flex align-items-center justify-content-start p-lg-1" style="display:block;">
            <div class="rounded me-4">
                <a href="product?service=detail&name=${element.name}">
                    <img src="${element.img1}" class="img-fluid rounded" alt="">
                </a>
            </div>
            <div>
                <h6 class="mb-2">
                    <a href="product?service=detail&name=${element.name}"">
                        ${element.name}
                    </a>
                </h6>
                <div class="rating" style="display:inline-flex;">
                    <c:forEach begin="1" end="${element.ratting.ratting}" step="1">
                        <i class="icon-star voted"></i>
                    </c:forEach>
                    <c:forEach begin="${element.ratting.ratting + 1}" end="5" step="1">
                        <i class="icon-star"></i>
                    </c:forEach>
                </div>
                <div class="price_box" style="display:inline-flex;">
                    <c:if test="${element.discount.active}">                                              
                        <span class="new_price">$${String.format("%.2f", element.price*(100-element.discount.discountPercent)/100)}</span>
                        <span class="old_price">$${element.price}</span>
                    </c:if>
                    <c:if test="${!element.discount.active}">                                              
                        <span class="new_price">$${element.price}</span>                                                
                    </c:if> 
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="d-flex justify-content-center my-4">
        <a href="#"
           class="btn border border-secondary px-3 py-2 rounded-pill text-primary w-100">Contact</a>
    </div>
</div>
<!-- /col -->
