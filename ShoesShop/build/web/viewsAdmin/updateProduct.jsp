<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="scroll-smooth group" data-sidebar="brand" dir="ltr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Robotech - Admin & Dashboard Template</title>
        <meta  name="viewport"  content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta  content="Tailwind Multipurpose Admin & Dashboard Template"  name="description"/>
        <meta content="" name="Mannatthemes" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="viewsAdmin/assets/images/favicon.ico" />

        <!-- Css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/viewsAdmin/assets/libs/filepond/filepond.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/viewsAdmin/assets/libs/filepond-plugin-image-preview/filepond-plugin-image-preview.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/viewsAdmin/assets/libs/vanillajs-datepicker/css/datepicker.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/viewsAdmin/assets/libs/mobius1-selectr/selectr.min.css">
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
                                            <h1 class="font-medium text-3xl block dark:text-slate-100">Product Detail</h1>
                                            <ol class="list-reset flex text-sm">
                                                <li><a href="#" class="text-gray-500 dark:text-slate-400">Robotech</a></li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-gray-500 dark:text-slate-400"><a href="productList">Admin</a></li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-primary-500 hover:text-primary-600 dark:text-primary-400">Add Product</li>
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
                    <div class="grid grid-cols-12 sm:grid-cols-12 md:grid-cols-12 lg:grid-cols-12 xl:grid-cols-12 gap-4 justify-between">
                        <c:set var="p" value="${product}"/>
                        <div class="col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-9 xl:col-span-9">
                            <div class="w-full relative mb-4">  
                                <div class="flex-auto p-0 md:p-4"> 
                                    <form action="updateProduct" method="post">
                                        <div class="mb-2">
                                            <label for="thumbnail" class="font-medium text-sm text-slate-600 dark:text-slate-400">Thumbnail</label>
                                            <input type="text" id="thumbnail" name="thumbnail" value="${p.img1}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-brand-500 dark:focus:border-brand-500  dark:hover:border-slate-700" placeholder="Thumbnail" readonly>
                                        </div>
                                        <div class="mb-2">
                                            <label for="img2" class="font-medium text-sm text-slate-600 dark:text-slate-400">Image 2</label>
                                            <input type="text" id="img2" name="img2" value="${p.img2}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-brand-500 dark:focus:border-brand-500  dark:hover:border-slate-700" placeholder="Image 2" required readonly>
                                        </div>
                                        <div class="mb-2">
                                            <label for="title" class="font-medium text-sm text-slate-600 dark:text-slate-400">Title</label>
                                            <input type="text" id="title" name="title" value="${p.name}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-brand-500 dark:focus:border-brand-500  dark:hover:border-slate-700" placeholder="Title" required>
                                        </div>
                                        <input type="hidden" name="oldName" value="${p.name}">
                                        <div class="mb-2">
                                            <label class="font-medium text-sm text-slate-600 dark:text-slate-400">Category</label><br>
                                            <div class="grid sm:grid-cols-12 md:grid-cols-12 lg:grid-cols-12 xl:grid-cols-12 gap-4">
                                                <c:forEach var="c" items="${categories}">
                                                    <div class="sm:col-span-12 md:col-span-6 lg:col-span-4 xl:col-span-3 bg-slate-50 dark:bg-slate-700/25 text-center p-4 rounded-md border border-slate-100 dark:border-slate-700">
                                                        <label class="font-medium text-sm text-slate-600 dark:text-slate-400">${c.name}</label><br>
                                                        <c:forEach var="sc" items="${subCategories}">
                                                            <c:if test="${c.id == sc.category_id}">
                                                                <input <c:forEach var="sc2" items="${listSubCategoryOfProduct}"><c:if test="${sc.id == sc2}">checked</c:if></c:forEach> type="checkbox" id="subcategory" name="subcategory" value="${sc.id}">
                                                                <label for="subcategory">${sc.name}</label><br>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="mb-2">
                                            <label for="description" class="font-medium text-sm text-slate-600 dark:text-slate-400">Description</label>
                                            <textarea id="description" name="description" rows="3" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-1 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500  dark:hover:border-slate-700" placeholder="Description ...">${p.description}</textarea>
                                        </div>

                                        <div class="mb-2">
                                            <label for="price" class="font-medium text-sm text-slate-600 dark:text-slate-400">Price</label>
                                            <input type="number" id="price" name="price" value="${p.price}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-brand-500 dark:focus:border-brand-500  dark:hover:border-slate-700" placeholder="Title" required>
                                        </div>

                                        <div class="mb-2">
                                            <label for="discount" class="font-medium text-sm text-slate-600 dark:text-slate-400">Discount</label>
                                            <select id="discount" name="discount" class="w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-brand-500 dark:focus:border-brand-500  dark:hover:border-slate-700">
                                                <c:forEach var="d" items="${listDiscount}">
                                                    <option <c:if test="${p.discountId == d.id}">selected</c:if> class="dark:text-slate-700" value="${d.id}">${d.discountPercent}%</option>
                                                </c:forEach>
                                            </select>                                       
                                        </div>

                                        <div class="mb-2">
                                            <label for="brand" class="font-medium text-sm text-slate-600 dark:text-slate-400">Brand</label>
                                            <select id="brand" name="brand" class="w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-brand-500 dark:focus:border-brand-500  dark:hover:border-slate-700">
                                                <c:forEach var="b" items="${listBrand}">
                                                    <option <c:if test="${p.brandId == b.id}">selected</c:if> class="dark:text-slate-700" value="${b.id}">${b.name}</option>
                                                </c:forEach>
                                            </select>                                       
                                        </div>

                                        <div class="mb-2">
                                            <label for="status" class="font-medium text-sm text-slate-600 dark:text-slate-400">Status</label>
                                            <select id="status" name="status" class="w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-brand-500 dark:focus:border-brand-500  dark:hover:border-slate-700">
                                                <c:forEach var="ps" items="${listProductStatus}">
                                                    <option <c:if test="${p.statusId == ps.id}">selected</c:if> class="dark:text-slate-700" value="${ps.id}">${ps.name}</option>
                                                </c:forEach>
                                            </select>                                       
                                        </div>

                                        <div class="mb-2">
                                            <label class="font-medium text-sm text-slate-600 dark:text-slate-400">Quantity</label><br>
                                            <div class="grid sm:grid-cols-12 md:grid-cols-12 lg:grid-cols-12 xl:grid-cols-12 gap-4">
                                                <c:forEach var="co" items="${listColor}">
                                                    <div class="sm:col-span-12 md:col-span-6 lg:col-span-4 xl:col-span-2 bg-slate-50 dark:bg-slate-700/25 p-4 rounded-md border border-slate-100 dark:border-slate-700">
                                                        <label class="font-medium text-sm text-slate-600 dark:text-slate-400">${co}</label><br>
                                                        <c:forEach var="s" items="${listSize}">
                                                            <c:forEach var="p1" items="${listOneProduct}">
                                                                <c:if test="${p1.size == s && p1.color == co}">
                                                                    <label>Size ${s}</label><br>
                                                                    <input style="width: 100%" type="text" id="quantity" name="${s}of${co}" value="${p1.quantity}"><br>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:forEach>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <div class="">
                                            <button class="px-2 py-2 lg:px-4 bg-brand  text-white text-sm  rounded hover:bg-brand-600 border border-brand-500">Save Product</button>                                           
                                        </div>
                                    </form>
                                </div><!--end card-body--> 
                            </div><!--end card-->                                  
                        </div><!--end col-->  
                        <div class="col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-3 xl:col-span-3">
                            <div class="w-full relative mb-4">  
                                <div class="flex-auto p-0 md:p-4">
                                    <div class="grid sm:grid-cols-12 md:grid-cols-12 lg:grid-cols-12 xl:grid-cols-12 gap-4">
                                        <div class="sm:col-span-12 md:col-span-6 lg:col-span-4 xl:col-span-6 bg-slate-50 dark:bg-slate-700/25 text-center p-4 rounded-md border border-slate-100 dark:border-slate-700">
                                            <p class="text-slate-700 text-base dark:text-slate-400">Thumbnail</p>
                                            <img src="${p.img1}" alt="" class="w-full h-auto"><br>
                                            <button data-toggle="modal" data-target="#addThumbnail" data-backdrop="false" class="inline-block focus:outline-none bg-brand-500 mt-1 text-white hover:bg-brand-600 hover:text-white  text-md font-medium py-2 px-4 rounded">
                                                Add Image
                                            </button>
                                        </div>
                                        <div class="sm:col-span-12 md:col-span-6 lg:col-span-4 xl:col-span-6 bg-slate-50 dark:bg-slate-700/25 text-center p-4 rounded-md border border-slate-100 dark:border-slate-700">
                                            <p class="text-slate-700 text-base dark:text-slate-400">Product Image 2</p>
                                            <img src="${p.img2}" alt="" class="w-full h-auto"><br>
                                            <button data-toggle="modal" data-target="#addImage" data-backdrop="false" class="inline-block focus:outline-none bg-brand-500 mt-1 text-white hover:bg-brand-600 hover:text-white  text-md font-medium py-2 px-4 rounded">
                                                Add Image
                                            </button>
                                        </div>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-700 text-base dark:text-slate-400">Product Title</p>
                                        <h4 class="text-xl font-semibold text-slate-700 dark:text-slate-300">${p.name}</h4>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-600 text-base dark:text-slate-400">Category</p>
                                        <c:forEach var="c" items="${categories}">
                                            <c:if test="${c.id == p.categoryId}">
                                                <h4 class="text-base font-medium text-slate-900 dark:text-slate-300">${c.name}</h4>
                                            </c:if>
                                        </c:forEach>
                                        <br><p class="text-slate-600 text-base dark:text-slate-400">Sub Category</p>
                                        <c:forEach var="sc" items="${subCategories}">
                                            <c:forEach var="scid" items="${p.subCategoryId}">
                                                <c:if test="${sc.id == scid}">
                                                    <h4 class="text-base font-medium text-slate-900 dark:text-slate-300">${sc.name}</h4>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-600 text-base dark:text-slate-400">Description</p>
                                        <h4 class="text-base font-medium text-slate-900 dark:text-slate-300">${p.description}</h4>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-700 text-base dark:text-slate-400">Price</p>
                                        <h4 class="text-xl font-semibold text-slate-700 dark:text-slate-300">$${p.price}</h4>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-700 text-base dark:text-slate-400">Discount</p>
                                        <c:forEach var="d" items="${listDiscount}">
                                            <c:if test="${d.id == p.discountId}">
                                                <h4 class="text-xl font-semibold text-slate-700 dark:text-slate-300">${d.discountPercent}%</h4>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-700 text-base dark:text-slate-400">Brand</p>
                                        <c:forEach var="b" items="${listBrand}">
                                            <c:if test="${b.id == p.brandId}">
                                                <h4 class="text-xl font-semibold text-slate-700 dark:text-slate-300">${b.name}</h4>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-700 text-base dark:text-slate-400">Status</p>
                                        <c:forEach var="ps" items="${listProductStatus}">
                                            <c:if test="${ps.id == p.statusId}">
                                                <h4 class="text-xl font-semibold text-slate-700 dark:text-slate-300">${ps.name}</h4>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-5">
                                        <p class="text-slate-700 text-base dark:text-slate-400">Color</p>
                                        <c:forEach var="c" items="${listColor}">
                                            <h4 class="text-xl font-semibold text-slate-700 dark:text-slate-300">${c}</h4>
                                        </c:forEach>
                                    </div>
                                </div><!--end card-body--> 
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

        <!--Add Thumbnail-->
        <div class="modal fade" id="addThumbnail" tabindex="-1" role="dialog" aria-labelledby="editProductModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <!--Modal Header--> 
                    <div class="modal-header" style="background-color: #007bff">
                        <h5 class="modal-title" id="editProductModalLabel">Add Thumbnail</h5>
                    </div>

                    <!--Modal Body--> 
                    <form action="uploadThumbnail" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        <input type="hidden" name="title" value="${p.name}">
                        
                        <div class="w-full h-56 mx-auto  mb-4 modal-body">
                            <input style="border: 1px solid #ced4da; height: 38px; border-radius: 5px" type="file" id="file" class="filepond h-56 input-group" name="file" accept="image/png, image/jpeg, image/gif" required>
                        </div>
                        <!--Modal Footer--> 
                            <div class="modal-footer" style="background-color: #007bff">
                                <button type="submit" class="btn btn-primary" >Upload</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeAddModal()"">Cancel</button>
                            </div>
                    </form>
                </div>
            </div>
        </div>
                        
        <!--Add Image-->
        <div class="modal fade" id="addImage" tabindex="-1" role="dialog" aria-labelledby="editProductModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <!--Modal Header--> 
                    <div class="modal-header" style="background-color: #007bff">
                        <h5 class="modal-title" id="editProductModalLabel">Add Thumbnail</h5>
                    </div>

                    <!--Modal Body--> 
                    <form action="uploadImage" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        <input type="hidden" name="title" value="${p.name}">
                        
                        <div class="w-full h-56 mx-auto  mb-4 modal-body">
                            <input style="border: 1px solid #ced4da; height: 38px; border-radius: 5px" type="file" id="file" class="filepond h-56 input-group" name="file" accept="image/png, image/jpeg, image/gif" required>
                        </div>
                        <!--Modal Footer--> 
                            <div class="modal-footer" style="background-color: #007bff">
                                <button type="submit" class="btn btn-primary" >Upload</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeAddModal()"">Cancel</button>
                            </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- JAVASCRIPTS -->
        <!-- <div class="menu-overlay"></div> -->
        <script src="${pageContext.request.contextPath}/viewsAdmin/viewsAdmin/assets/libs/lucide/umd/lucide.min.js"></script>
        <script src="${pageContext.request.contextPath}/viewsAdmin/viewsAdmin/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/viewsAdmin/viewsAdmin/assets/libs/flatpickr/flatpickr.min.js"></script>
        <script src="${pageContext.request.contextPath}/viewsAdmin/viewsAdmin/assets/libs/@frostui/tailwindcss/frostui.js"></script>

        <script src="${pageContext.request.contextPath}/viewsAdmin/viewsAdmin/assets/libs/apexcharts/apexcharts.min.js"></script>
        <script src="${pageContext.request.contextPath}/viewsAdmin/viewsAdmin/assets/js/pages/analytics-index.init.js"></script>
        <script src="${pageContext.request.contextPath}/viewsAdmin/viewsAdmin/assets/js/app.js"></script>
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

        <!-- JAVASCRIPTS -->
        <script src="${pageContext.request.contextPath}/viewsAdmin/assets/libs/filepond/filepond.min.js"></script>
<!--        <script src="${pageContext.request.contextPath}/viewsAdmin/assets/libs/filepond-plugin-image-preview/filepond-plugin-image-preview.min.js"></script>-->
        <script src="${pageContext.request.contextPath}/viewsAdmin/assets/libs/vanillajs-datepicker/js/datepicker-full.min.js"></script>
        <script src="${pageContext.request.contextPath}/viewsAdmin/assets/libs/mobius1-selectr/selectr.min.js"></script>
        <script src="${pageContext.request.contextPath}/viewsAdmin/assets/js/app.js"></script>
        <!-- JAVASCRIPTS -->
        <script>
                            FilePond.registerPlugin(FilePondPluginImagePreview);
                            // Get a reference to the file input element
                            const inputElement = document.querySelectorAll('input[type="file"]');

                            // Create a FilePond instance
                            inputElement.forEach(element => {
                                const pond = FilePond.create(element);
                            });


                            var elem = document.querySelector('input[name="foo"]');
                            new Datepicker(elem, {
                                // ...options
                            });
                            new Selectr('#sizing', {
                                taggable: true,
                                tagSeperators: [",", "|"]
                            });

                            // Example starter JavaScript for disabling form submissions if there are invalid fields
                            (function () {
                                'use strict';
                                window.addEventListener('load', function () {
                                    var forms = document.getElementsByClassName('needs-validation');
                                    var validation = Array.prototype.filter.call(forms, function (form) {
                                        form.addEventListener('submit', function (event) {
                                            if (form.checkValidity() === false) {
                                                event.preventDefault();
                                                event.stopPropagation();
                                            }
                                            form.classList.add('was-validated');
                                        }, false);
                                    });
                                }, false);
                            })();
        </script>
    </body>
</html>