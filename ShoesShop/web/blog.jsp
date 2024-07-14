<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!--        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Ansonika">
        <title>Allaia | Bootstrap eCommerce Template - ThemeForest</title>

         Favicons
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" type="image/x-icon" href="img/apple-touch-icon-57x57-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="img/apple-touch-icon-72x72-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="img/apple-touch-icon-114x114-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="img/apple-touch-icon-144x144-precomposed.png">

         GOOGLE WEB FONT 
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

         BASE CSS 
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

         SPECIFIC CSS 
        <link href="css/blog.css" rel="stylesheet">

         YOUR CUSTOM CSS -->
        <link href="css/custom.css" rel="stylesheet">
        <jsp:include page="head.jsp" />
    </head>

    <body>

        <div id="page">

            <jsp:include page="header.jsp" />

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
                        <h1>Allaia Blog &amp; News</h1>
                    </div>
                    <!-- /page_header -->
                    <div class="row">
                        <div class="col-lg-9">
                            <div class="widget search_blog d-block d-sm-block d-md-block d-lg-none">
                                <div class="form-group">
                                    <form action="blogs">
                                        <input type="text" name="search" id="search" class="form-control"
                                               placeholder="Search..">
                                        <button type="submit"><i class="ti-search"></i></button>
                                    </form>
                                </div>
                            </div>
                            <!-- /widget -->
                            <div class="row">
                                <c:forEach var="blog" items="${listBlog}">
                                    <div class="col-md-6">
                                        <article class="blog">
                                            <figure>
                                                <a href="blogDetail?id=${blog.id}"><img src="${blog.main_img}"
                                                                                        alt="">
                                                    <div class="preview"><span>Read more</span></div>
                                                </a>
                                            </figure>
                                            <div class="post_info">
                                                <small>${blog.modified_at}</small>
                                                <h2><a href="blogDetail?id=${blog.id}">${blog.title}</a></h2>
                                                <p>${blog.getShort()}</p>
                                                <ul>
                                                    <li>
                                                        <div class="thumb"><img src="img/avatar.jpg" alt="">
                                                        </div> User
                                                    </li>
                                                    <li><i class="ti-comment"></i>20</li>
                                                </ul>
                                            </div>
                                        </article>
                                        <!-- /article -->
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- /row -->

                            <div class="pagination__wrapper no_border add_bottom_30">
                                <jsp:include page="components/pagination.jsp" />

                            </div>
                            <!-- /pagination -->

                        </div>
                        <!-- /col -->

                        <aside class="col-lg-3">
                            <div class="widget search_blog d-none d-sm-none d-md-none d-lg-block">
                                <div class="form-group">
                                    <form action="blogs">
                                        <input type="text" name="search" id="search" class="form-control"
                                               placeholder="Search..">
                                        <button type="submit"><i class="ti-search"></i></button>
                                    </form>
                                </div>
                            </div>
                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-title">
                                    <h4>Latest Post</h4>
                                </div>
                                <ul class="comments-list">
                                    <c:forEach var="last" items="${listLastBlog}">
                                        <li>
                                            <div class="alignleft">
                                                <a href="#0"><img src="${last.main_img}" alt=""></a>
                                            </div>
                                            <small>${last.blogCategory.name} - ${last.modified_at}</small>
                                            <h3><a href="#" title="">${last.title}</a></h3>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-title">
                                    <h4>Categories</h4>
                                </div>
                                <ul class="cats">
                                    <ul class="cats">
                                        <li><a href="blogs?cate=">All<span></span></a></li>
                                            <c:forEach var="entry" items="${listBlogCategoriess}">
                                                <c:set var="category" value="${entry.key}" />
                                                <c:set var="blogs" value="${entry.value}" />
                                            <li><a href="blogs?cate=${category.id}">
                                                    <c:out value="${category.name}" /> <span>(
                                                        <c:out value="${fn:length(blogs)}" />)
                                                    </span>
                                                </a></li>
                                            </c:forEach>
                                    </ul>

                                </ul>

                            </div>

                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-title">
                                    <h4>Contacts</h4>
                                </div>
                            </div>
                            <h3 data-bs-target="#collapse_3"></h3>
                            <div class="collapse dont-collapse-sm contacts" id="collapse_3">
                                <ul>
                                    <li><i class="ti-home"></i> 97845 Baker st. 567<br>Los Angeles - US</li>
                                    <li><i class="ti-headphone-alt"></i> +94 423-23-221</li>
                                    <li><i class="ti-email"></i><a href="#0"> info@allaia.com</a></li>
                                </ul>
                            </div>

                    </div>
                </div>
                <!-- /container -->
            </main>
            <!--/main-->

            <jsp:include page="components/footer.jsp" />

            <jsp:include page="modal.jsp" />
            <jsp:include page="footer.jsp" />
            <!-- /row -->
        </div>
        <!-- /container -->

        <!--/main-->
    </body>

</html>