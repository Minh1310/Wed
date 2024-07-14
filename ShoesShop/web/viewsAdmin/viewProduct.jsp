<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="scroll-smooth group" data-sidebar="brand" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <title>Robotech - Admin & Dashboard Template</title>
        <meta  name="viewport"  content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta  content="Tailwind Multipurpose Admin & Dashboard Template"  name="description"/>
        <meta content="" name="Mannatthemes" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="viewsAdmin/assets/images/favicon.ico" />

        <!-- Css -->
        <!-- Main Css -->
        <link rel="stylesheet" href="viewsAdmin/assets/libs/icofont/icofont.min.css">
        <link href="viewsAdmin/assets/libs/flatpickr/flatpickr.min.css" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="viewsAdmin/assets/css/tailwind.min.css">


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
        <link href="css/home_1.css" rel="stylesheet">

        <!-- YOUR CUSTOM CSS -->
        <link href="css/custom.css" rel="stylesheet">

        <script type="text/javascript">

        </script>

    </head>

    <body data-layout-mode="light"  data-sidebar-size="default" data-theme-layout="vertical" class="bg-[#EEF0FC] dark:bg-gray-900">

        <jsp:include page="components/leftBar.jsp" />

        <jsp:include page="components/topBar.jsp" />

        <jsp:include page="components/notification.jsp" />


        <div class="ltr:flex flex-1 rtl:flex-row-reverse">
            <div class="page-wrapper relative ltr:ms-auto rtl:me-auto rtl:ms-0 w-[calc(100%-260px)] px-4 pt-[64px] duration-300">
                <div class="xl:w-full">        
                    <div class="flex flex-wrap">
                        <div class="flex items-center py-4 w-full">
                            <div class="w-full">                    
                                <div class="">
                                    <div class="flex flex-wrap justify-between">
                                        <div class="items-center ">
                                            <h1 class="font-medium text-3xl block dark:text-slate-100">Products</h1>
                                            <ol class="list-reset flex text-sm">
                                                <li><a href="#" class="text-gray-500 dark:text-slate-400">Robotech</a></li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-gray-500 dark:text-slate-400">Admin</li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-primary-500 hover:text-primary-600 dark:text-primary-400">Products</li>
                                            </ol>
                                        </div>
                                        <div class="flex items-center">
                                            <div class="today-date leading-5 mt-2 lg:mt-0 form-input w-auto rounded-md border inline-block border-primary-500/60 dark:border-primary-500/60 text-primary-500 bg-transparent px-3 py-1 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-primary-400 focus:border-primary-500 dark:focus:border-primary-500  dark:hover:border-slate-700">
                                                <input type="text" class="dash_date border-0 focus:border-0 focus:outline-none" readonly  required="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--end container-->

                <div class="xl:w-full  min-h-[calc(100vh-138px)] relative pb-14"> 
                    <div class="grid grid-cols-12 sm:grid-cols-12 md:grid-cols-12 lg:grid-cols-12 xl:grid-cols-12 gap-4">
                        <div class="col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-12 xl:col-span-12">
                            <div class="w-full relative mb-4">  
                                <div class="flex-auto p-0 md:p-4">
                                    <div class="mb-4 border-b border-gray-200 dark:border-slate-700" data-fc-type="tab">
                                        <ul class="flex flex-wrap -mb-px text-sm font-medium text-center" aria-label="Tabs">

                                        </ul>
                                    </div>
                                    <div>
                                        <button data-toggle="modal" data-target="#addProductModal" data-backdrop="false" class="inline-block focus:outline-none bg-brand-500 mt-1 text-white hover:bg-brand-600 hover:text-white  text-md font-medium py-2 px-4 rounded">
                                            Add product
                                        </button>
                                    </div>
                                    <span>
                                        <tr>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </span>

                                    <div class="flex flex-wrap gap-4 mb-3">

                                        <nav class="categories">
                                            <ul class="clearfix">
                                                <li><div>
                                                        <button style="width: 140px; height: 40px" type="button"  data-fc-autoclose="both" data-fc-type="dropdown" class="px-2 py-1 lg:px-4 bg-transparent text-gray-900 text-sm font-medium mb-2  focus:outline-none  rounded transition border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10  dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"><c:if test="${category == '' || category == null}">All Category</c:if><c:if test="${category != null}">${category}</c:if><i class="fas fa-chevron-down ms-1 text-xs self-center"></i></button>
                                                        </div>
                                                        <div id="menu">
                                                            <ul>
                                                                    <li><span><a href="productList?category=&subcategory=&search=${search}&status=${status}">All Category</a></span></li>
                                                                        <c:forEach var="c" items="${categories}">
                                                                <li><span><a href="productList?category=${c.name}&subcategory=${sc.name}&search=${search}&status=${status}">${c.name}</a></span>
                                                                    <ul>
                                                                        <c:forEach var="sc" items="${subCategories}">
                                                                            <c:if test="${c.id == sc.category_id}">
                                                                                <li><a href="productList?category=${c.name}&subcategory=${sc.name}&search=${search}&status=${status}">${sc.name}</a></li>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                    </ul>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </div>
                                                </li>
                                            </ul>
                                        </nav>

                                        <nav class="categories">
                                            <ul class="clearfix">
                                                <li><div>
                                                        <button style="width: 140px; height: 40px" type="button"  data-fc-autoclose="both" data-fc-type="dropdown" class="px-2 py-1 lg:px-4 bg-transparent text-gray-900 text-sm font-medium mb-2  focus:outline-none  rounded transition border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10  dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"><c:if test="${status == 0}">All Status</c:if><c:if test="${status != 0}">${statusName}</c:if><i class="fas fa-chevron-down ms-1 text-xs self-center"></i></button>
                                                        </div>
                                                        <div id="menu">
                                                            <ul>
                                                                    <li><a href="productList?category=${category}&subcategory=${subcategory}&search=${search}&status=0">All Status</a></li>
                                                                <c:forEach var="ps" items="${listProductStatus}">
                                                                <li><a href="productList?category=${category}&subcategory=${subcategory}&search=${search}&status=${ps.id}">${ps.name}</a></li>
                                                                </c:forEach>
                                                        </ul>
                                                    </div>
                                                </li>
                                            </ul>
                                        </nav>

                                        <div class="ms-auto">
                                            <form action="productList" method="get">
                                                <div class="relative">
                                                    <input type="hidden" name="category" value="${category}">
                                                    <input type="hidden" name="subcategory" value="${subcategory}">
                                                    <input type="search" value="${search}" name="search" id="searchTable" onkeyup="searchTable()" class="form-input w-52 rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700 pl-10 p-2.5" placeholder="search">
                                                    <input type="hidden" name="status" value="${status}">
                                                </div>
                                            </form>                                               
                                        </div>
                                    </div>

                                    <div id="myTabContent">
                                        <div class="active  p-4 bg-gray-50 rounded-lg dark:bg-gray-900" id="all" role="tabpanel" aria-labelledby="all-tab">
                                            <div class="grid grid-cols-1 p-0 md:p-4">
                                                <div class="sm:-mx-6 lg:-mx-8">
                                                    <div class="relative overflow-x-auto block w-full sm:px-6 lg:px-8">
                                                        <table id="userTable" class="w-full">
                                                            <thead class="bg-gray-50 dark:bg-slate-700/20">
                                                                <tr>

                                                                    <th scope="col" data-sort="number" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        ID
                                                                    </th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Thumbnail
                                                                    </th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Title
                                                                    </th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Category
                                                                    </th>
                                                                    <th scope="col" data-sort="number" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Price
                                                                    </th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Feature
                                                                    </th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Brand
                                                                    </th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Status
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Action
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <!-- 1 -->
                                                                <c:set value="0" var="id"/> 
                                                                <c:forEach var="p" items="${listProduct}">
                                                                    <c:set var="id" value="${id + 1}" />
                                                                    <tr class="bg-white border-b border-dashed dark:bg-gray-900 dark:border-gray-700/40">
                                                                        <!--ID-->
                                                                        <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                            ${id}
                                                                        </td>
                                                                        <!--Thumbnail-->
                                                                        <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">    
                                                                            <img src="${p.img1}" alt="" class="me-2 h-8 inline-block"><div hidden="">${p.img1}</div>
                                                                        </td>
                                                                        <!--Title-->
                                                                        <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                            ${p.name}
                                                                        </td>
                                                                        <!--Category-->
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            <c:forEach var="c" items="${categories}">
                                                                                <c:if test="${c.id == p.categoryId}">
                                                                                    <a href="#" class="text-brand-500 underline">${c.name}</a>: 
                                                                                </c:if>
                                                                            </c:forEach>
                                                                            <c:forEach var="sc" items="${subCategories}">
                                                                                <c:forEach var="scid" items="${p.subCategoryId}">
                                                                                    <c:if test="${sc.id == scid}">
                                                                                        <a href="#" class="text-brand-500 underline">${sc.name}</a>&nbsp;
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </c:forEach>
                                                                        </td>

                                                                        <td class="p-3 font-semibold text-lg text-gray-800 whitespace-nowrap dark:text-gray-400">
                                                                            <c:forEach var="d" items="${listDiscount}">
                                                                                <c:if test="${d.id == p.discountId}">
                                                                                    <span><fmt:formatNumber value="${p.price * (1-d.discountPercent/100)}" maxIntegerDigits="3" maxFractionDigits="2"/> &nbsp;<del class="text-slate-500 font-normal">${p.price}</del></span>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                        </td>

                                                                        <!--Feature-->

                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            ${p.description}
                                                                        </td>

                                                                        <!--Brand-->

                                                                        <td class="p-3 font-semibold text-lg text-gray-800 whitespace-nowrap dark:text-gray-400">
                                                                            <c:forEach var="b" items="${listBrand}">
                                                                                <c:if test="${b.id == p.brandId}">
                                                                                    <span>${b.name}</span>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </td>
                                                                        <!--Status-->
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            <c:forEach var="ps" items="${listProductStatus}">
                                                                                <c:if test="${ps.id == p.statusId}">
                                                                                    <button type="button"  data-fc-autoclose="both" data-fc-type="dropdown" class="px-2 py-1 lg:px-4 bg-transparent  text-primary text-sm  rounded transition hover:bg-primary-500 hover:text-white border border-primary font-medium mb-2">${ps.name} <i class="fas fa-chevron-down ms-1 text-xs self-center"></i></button>
                                                                                    <div id="dropdown-target-7" class=" hidden z-10 w-44 bg-white rounded divide-y divide-gray-100 shadow dark:bg-gray-800 border border-slate-700/10">
                                                                                        <ul class="py-1 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefault">
                                                                                            <c:forEach var="ps2" items="${listProductStatus}">
                                                                                                <li>
                                                                                                    <a href="updateProduct?status=${ps2.id}&productName=${p.name}" class="block py-2 px-4 text-gray-600 dark:text-gray-300 hover:text-gray-800 hover:bg-gray-50 dark:hover:bg-gray-700/40 dark:hover:text-white">${ps2.name}</a>
                                                                                                </li>
                                                                                            </c:forEach>
                                                                                        </ul>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </td>

                                                                        <!--Action-->
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            <a href="productDetail?productName=${p.name}"><i class="icofont-ui-edit text-lg text-gray-500 dark:text-gray-400"></i></a>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div><!--end div-->
                                                </div><!--end div-->
                                            </div><!--end grid-->
                                            <div class="flex justify-between mt-4">
                                                <jsp:include page="components/pagination2.jsp"/>
                                            </div>
                                        </div>

                                    </div>
                                </div><!--end card-->                                  
                            </div><!--end col-->                        
                        </div> <!--end grid-->                                        
                        <!-- footer -->
                        <div class="absolute bottom-0 -left-4 -right-4 block print:hidden border-t p-4 h-[52px] dark:border-slate-700/40">
                            <div class="container">
                                <!-- Footer Start -->
                                <footer
                                    class="footer bg-transparent  text-center  font-medium text-slate-600 dark:text-slate-400 md:text-left "
                                    >
                                    &copy;
                                    <script>
                                        var year = new Date();
                                        document.write(year.getFullYear());
                                    </script>
                                    Robotech
                                    <span class="float-right hidden text-slate-600 dark:text-slate-400 md:inline-block"
                                          >Crafted with <i class="ti ti-heart text-red-500"></i> by
                                        Mannatthemes</span
                                    >
                                </footer>
                                <!-- end Footer -->
                            </div>
                        </div>
                    </div><!--end container-->
                </div><!--end page-wrapper-->
            </div><!--end /div-->

            <!--Add product-->
            <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="editProductModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <!--Modal Header--> 
                        <div class="modal-header" style="background-color: #007bff">
                            <h5 class="modal-title" id="editProductModalLabel">Add Product</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeAddModal()">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <!--Modal Body--> 
                        <form action="productList" method="post">
                            <div class="modal-body">
                                <div class="form-group input-group">
                                    <input style="border: 1px solid #ced4da; height: 38px; border-radius: 5px" class="input-group" type="hidden" id="img1" name="img1" value="">
                                </div>
                                <div class="form-group">
                                    <input style="border: 1px solid #ced4da; height: 38px; border-radius: 5px" class="input-group" type="hidden" id="img2" name="img2" value="">
                                </div>
                                <div class="form-group input-group">
                                    <label for="title">Title:</label>
                                    <input style="border: 1px solid #ced4da; height: 38px; border-radius: 5px" class="input-group" type="text" id="title" name="title" required>
                                </div>
                                <div class="form-group input-group">
                                    <label for="description">Description:</label>
                                    <input style="border: 1px solid #ced4da; height: 38px; border-radius: 5px" class="input-group" type="text" id="description" name="description" required>
                                </div>
                                <div class="form-group input-group">
                                    <label for="price">Price:</label>
                                    <input style="border: 1px solid #ced4da; height: 38px; border-radius: 5px" class="input-group" type="number" step="0.01" id="price" name="price" required>
                                </div>
                                <div class="form-group">
                                    <label for="discount">Discount:</label>
                                    <select class="form-select" id="discount" name="discount" required>
                                        <c:forEach var="d" items="${listDiscount}">
                                            <option value="${d.id}">${d.discountPercent}%</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="brand">Brand:</label>
                                    <select class="form-select" id="brand" name="brand" required>
                                        <c:forEach var="b" items="${listBrand}">
                                            <option value="${b.id}">${b.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="status">Status:</label>
                                    <select class="form-select" id="status" name="status" required>
                                        <c:forEach var="s" items="${listProductStatus}">
                                            <option value="${s.id}">${s.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label>Color:</label><br>
                                    <c:forEach var="c" items="${listColor}">
                                        <input <c:if test="${c == 'White'}">checked</c:if> type="checkbox" id="color" name="color" value="${c}">
                                        <label for="color">${c}</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:forEach>
                                </div>
                            </div>
                            <!--Modal Footer--> 
                            <div class="modal-footer" style="background-color: #007bff">
                                <button type="submit" class="btn btn-primary" >Save Product</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeAddModal()"">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- JAVASCRIPTS -->
            <!-- <div class="menu-overlay"></div> -->
            <script src="viewsAdmin/assets/libs/lucide/umd/lucide.min.js"></script>
            <script src="viewsAdmin/assets/libs/simplebar/simplebar.min.js"></script>
            <script src="viewsAdmin/assets/libs/flatpickr/flatpickr.min.js"></script>
            <script src="viewsAdmin/assets/libs/@frostui/tailwindcss/frostui.js"></script>

            <script src="viewsAdmin/assets/libs/apexcharts/apexcharts.min.js"></script>
            <script src="viewsAdmin/assets/js/pages/analytics-index.init.js"></script>
            <script src="viewsAdmin/assets/js/app.js"></script>
            <!-- JAVASCRIPTS -->
            <script src="${pageContext.request.contextPath}/viewsAdmin/assets/js/dataTable.js"></script>
            <script src="${pageContext.request.contextPath}/viewsAdmin/assets/js/product.js"></script>

            <!-- COMMON SCRIPTS -->
            <script src="js/common_scripts.min.js"></script>
            <script src="js/main.js"></script>

            <!-- SPECIFIC SCRIPTS -->
            <script src="js/carousel-home.js"></script>

            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>