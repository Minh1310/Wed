<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    </div>
                    <!-- /page_header -->
                    <div class="row">
                        <div class="col-lg-9">
                            <div class="singlepost">
                                <figure><img alt="" class="img-fluid" src="${blog.main_img}"></figure>
                                <h1>${blog.title}</h1>
                                <div class="postmeta">
                                    <ul>
                                        <li><a href="#"><i class="ti-folder"></i> ${blog.blogCategory.name}</a></li>
                                        <li><i class="ti-calendar"></i> ${blog.modified_at}</li>
                                        <li><a href="#"><i class="ti-user"></i> Admin</a></li>
                                        <li><a href="#"><i class="ti-comment"></i> (14) Comments</a></li>
                                    </ul>
                                </div>
                                <!-- /post meta -->
                                <div class="post-content">
                                    <div class="dropcaps">
                                        <p>${blog.context}</p>
                                    </div>
                                </div>
                                <!-- /post -->
                            </div>
                            <!-- /single-post -->

<!--                            <div id="comments">
                                <h5>Comments</h5>
                                <ul>
                                    <li>
                                        <div class="avatar">
                                            <a href="#"><img src="img/avatar1.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="comment_right clearfix">
                                            <div class="comment_info">
                                                By <a href="#">Anna Smith</a><span>|</span>25/10/2019<span>|</span><a href="#"><i class="icon-reply"></i></a>
                                            </div>
                                            <p>
                                                Nam cursus tellus quis magna porta adipiscing. Donec et eros leo, non pellentesque arcu. Curabitur vitae mi enim, at vestibulum magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed sit amet sem a urna rutrumeger fringilla. Nam vel enim ipsum, et congue ante.
                                            </p>
                                        </div>
                                        <ul class="replied-to">
                                            <li>
                                                <div class="avatar">
                                                    <a href="#"><img src="img/avatar2.jpg" alt="">
                                                    </a>
                                                </div>
                                                <div class="comment_right clearfix">
                                                    <div class="comment_info">
                                                        By <a href="#">Anna Smith</a><span>|</span>25/10/2019<span>|</span><a href="#"><i class="icon-reply"></i></a>
                                                    </div>
                                                    <p>
                                                        Nam cursus tellus quis magna porta adipiscing. Donec et eros leo, non pellentesque arcu. Curabitur vitae mi enim, at vestibulum magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed sit amet sem a urna rutrumeger fringilla. Nam vel enim ipsum, et congue ante.
                                                    </p>
                                                    <p>
                                                        Aenean iaculis sodales dui, non hendrerit lorem rhoncus ut. Pellentesque ullamcorper venenatis elit idaipiscingi Duis tellus neque, tincidunt eget pulvinar sit amet, rutrum nec urna. Suspendisse pretium laoreet elit vel ultricies. Maecenas ullamcorper ultricies rhoncus. Aliquam erat volutpat.
                                                    </p>
                                                </div>
                                                <ul class="replied-to">
                                                    <li>
                                                        <div class="avatar">
                                                            <a href="#"><img src="img/avatar2.jpg" alt="">
                                                            </a>
                                                        </div>
                                                        <div class="comment_right clearfix">
                                                            <div class="comment_info">
                                                                By <a href="#">Anna Smith</a><span>|</span>25/10/2019<span>|</span><a href="#"><i class="icon-reply"></i></a>
                                                            </div>
                                                            <p>
                                                                Nam cursus tellus quis magna porta adipiscing. Donec et eros leo, non pellentesque arcu. Curabitur vitae mi enim, at vestibulum magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed sit amet sem a urna rutrumeger fringilla. Nam vel enim ipsum, et congue ante.
                                                            </p>
                                                            <p>
                                                                Aenean iaculis sodales dui, non hendrerit lorem rhoncus ut. Pellentesque ullamcorper venenatis elit idaipiscingi Duis tellus neque, tincidunt eget pulvinar sit amet, rutrum nec urna. Suspendisse pretium laoreet elit vel ultricies. Maecenas ullamcorper ultricies rhoncus. Aliquam erat volutpat.
                                                            </p>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <div class="avatar">
                                            <a href="#"><img src="img/avatar3.jpg" alt="">
                                            </a>
                                        </div>

                                        <div class="comment_right clearfix">
                                            <div class="comment_info">
                                                By <a href="#">Anna Smith</a><span>|</span>25/10/2019<span>|</span><a href="#"><i class="icon-reply"></i></a>
                                            </div>
                                            <p>
                                                Cursus tellus quis magna porta adipiscin
                                            </p>
                                        </div>
                                    </li>
                                </ul>
                            </div>-->

<!--                            <hr>-->

                            <h5>Leave a comment</h5>
                            <div class="row">
                                <div class="col-md-4 col-sm-6">
                                    <div class="form-group">
                                        <input type="text" name="name" id="name2" class="form-control" placeholder="Name">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-6">
                                    <div class="form-group">
                                        <input type="text" name="email" id="email2" class="form-control" placeholder="Email">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <input type="text" name="email" id="website3" class="form-control" placeholder="Website">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" name="comments" id="comments2" rows="6" placeholder="Comment"></textarea>
                            </div>
                            <div class="form-group">
                                <button type="submit" id="submit2" class="btn_1 add_bottom_15">Submit</button>
                            </div>

                        </div>
                        <!-- /col -->

                        <aside class="col-lg-3 ml-30">
                            <div class="widget search_blog">
                                <div class="form-group">
                                    <form action="blogs">
                                        <input type="text" name="search" id="search" class="form-control" placeholder="Search..">
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
                                    <c:forEach var="entry" items="${listBlogCategoriess}">
                                        <c:set var="category" value="${entry.key}" />
                                        <c:set var="blogs" value="${entry.value}" />
                                        <li><a href="#"><c:out value="${category.name}" /> <span>(<c:out value="${fn:length(blogs)}" />)</span></a></li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-title">
                                    <h4>Contacts</h4>
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
                            <!-- /widget -->
                        </aside>
                        <!-- /aside -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </main>
            <!--/main-->

            <jsp:include page="components/footer.jsp" />

        </div>
        <!-- page -->

        <div id="toTop"></div><!-- Back to top button -->

        <!-- COMMON SCRIPTS -->
        <script src="js/common_scripts.min.js"></script>
        <script src="js/main.js"></script>


    </body>
</html>