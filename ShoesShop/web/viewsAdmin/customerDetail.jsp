<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    </head>

    <body data-layout-mode="light"  data-sidebar-size="default" data-theme-layout="vertical" class="bg-[#EEF0FC] dark:bg-gray-900">

        <jsp:include page="components/leftBar.jsp" />

        <jsp:include page="components/topBar.jsp" />


        <div class="ltr:flex flex-1 rtl:flex-row-reverse">
            <div class="page-wrapper relative ltr:ms-auto rtl:me-auto rtl:ms-0 w-[calc(100%-260px)] px-4 pt-[64px] duration-300">
                <div class="xl:w-full">        
                    <div class="flex flex-wrap">
                        <div class="flex items-center py-4 w-full">
                            <div class="w-full">                    
                                <div class="">
                                    <div class="flex flex-wrap justify-between">
                                        <div class="items-center ">
                                            <h1 class="font-medium text-3xl block dark:text-slate-100">Customers</h1>
                                            <ol class="list-reset flex text-sm">
                                                <li><a href="#" class="text-gray-500 dark:text-slate-400">Robotech</a></li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-gray-500 dark:text-slate-400">Admin</li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-primary-500 hover:text-primary-600 dark:text-primary-400">Details</li>
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
                        <div class="col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-4 xl:col-span-3">
                            <div class="">
                                <div class="text-center">
                                    <img src="viewsAdmin/assets/images/users/avatar-1.png" alt="" class="rounded-full mx-auto inline-block">
                                    <div class="my-4">                                                                        
                                        <h5 class="text-xxl font-semibold text-slate-700 dark:text-gray-400">${u.first_name} ${u.last_name}</h5>
                                    </div>
                                </div>
                                <div class="grid grid-cols-12 sm:grid-cols-12 md:grid-cols-12 lg:grid-cols-12 xl:grid-cols-12 gap-4">
                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">Email :</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.email}</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">Phone :</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.telephone}</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">Gender :</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.gender?"Male":"Female"}</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">Status :</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.usersStatus.name}</span>
                                    </div><!--end col-->
<!--                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">Modified At :</span>
                                    </div>end col
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.modified_at}</span>
                                    </div>end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">Address Line :</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.userAddress.addressLine}</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">City :</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.userAddress.city}</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-5 text-end">
                                        <span class="dark:text-slate-300">City :</span>
                                    </div><!--end col-->
                                    <div class="col-span-12 sm:col-span-12 md:col-span-7">
                                        <span class="dark:text-slate-400">${u.userAddress.country}</span>
                                    </div><!--end col-->
                                </div><!--end grid-->
                            </div>
                        </div><!--end col-->
                        <div class="col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-8 xl:col-span-9">
                            <div class="w-full relative mb-4">  
                                <div class="flex-auto p-0 md:p-4">
                                    <div class="mb-4 border-b border-gray-200 dark:border-slate-700" data-fc-type="tab">
                                        <ul class="flex flex-wrap -mb-px text-sm font-medium text-center" aria-label="Tabs">
                                            <li class="me-2" role="presentation">
                                                <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300" id="wishlist-tab" data-fc-target="#wishlist" type="button" role="tab" aria-controls="wishlist" aria-selected="false">History <span class="text-slate-400">(${userHistories.size()})</span></button>
                                            </li>
                                        </ul>
                                    </div>


                                    <div id="myTabContent">
                                        <div class="p-4 bg-gray-50 rounded-lg dark:bg-gray-800" id="wishlist" role="tabpanel" aria-labelledby="wishlist-tab">
                                            <div class="grid grid-cols-1 p-0 md:p-4">
                                                <div class="sm:-mx-6 lg:-mx-8">
                                                    <div class="relative overflow-x-auto block w-full sm:px-6 lg:px-8">
                                                        <table class="w-full">
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        User ID
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        First Name
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Last Name
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Role Name
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Status
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Telephone
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Modify At
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="userHistory" items="${userHistories}">
                                                                    <tr class="bg-white border-b border-dashed dark:bg-gray-900 dark:border-gray-700/40">
                                                                        <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                            ${userHistory.userId}
                                                                        </td>
                                                                        <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                            ${userHistory.firstName}
                                                                        </td>
                                                                        <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                            ${userHistory.lastName}
                                                                        </td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            ${userHistory.roleName}
                                                                        </td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            ${userHistory.status}
                                                                        </td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            ${userHistory.telephone}
                                                                        </td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            ${userHistory.modifyAt}
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div><!--end div-->
                                                </div><!--end div-->
                                            </div><!--end grid-->
                                        </div>
                                        <div class="hidden p-4 bg-gray-50 rounded-lg dark:bg-gray-800" id="ratings" role="tabpanel" aria-labelledby="ratings-tab">
                                            <div class="grid grid-cols-1 p-0 md:p-4">
                                                <div class="sm:-mx-6 lg:-mx-8">
                                                    <div class="relative overflow-x-auto block w-full sm:px-6 lg:px-8">
                                                        <table class="w-full">
                                                            <thead class="bg-gray-50 dark:bg-gray-600/20">
                                                                <tr>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Items
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Review
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Status
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Date
                                                                    </th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">
                                                                        Action
                                                                    </th>
                                                                </tr>
                                                            </thead>
<!--                                                            <tbody>

                                                                 1 
                                                                <tr class="bg-white border-b border-dashed dark:bg-gray-900 dark:border-gray-700/40">
                                                                    <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                        <div class="flex items-center">
                                                                            <img src="assets/images/products/pro-5.png" alt="" class="me-2 h-8 inline-block">
                                                                            <div class="self-center">                                                                        
                                                                                <h5 class="text-sm font-semibold text-slate-700 dark:text-gray-400">Mannat Watch 3 Active </h5>
                                                                                <span class="block  font-medium text-slate-500">Latest Model 2023</span>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <ul class="flex">
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                        </ul>
                                                                    </td>

                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <span class="bg-green-600/5 text-green-600 text-[11px] font-medium px-2.5 py-0.5 rounded h-5">SUCCESS</span>
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        20 Feb 2023
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <a href="#"><i class="icofont-ui-edit text-lg text-gray-500 dark:text-gray-400"></i></a>
                                                                        <a href="#"><i class="icofont-ui-delete text-lg text-red-500 dark:text-red-400"></i></a>
                                                                    </td>
                                                                </tr>
                                                                 2 
                                                                <tr class="bg-white border-b border-dashed dark:bg-gray-900 dark:border-gray-700/40">
                                                                    <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                        <div class="flex items-center">
                                                                            <img src="assets/images/products/pro-1.png" alt="" class="me-2 h-8 inline-block">
                                                                            <div class="self-center">                                                                        
                                                                                <h5 class="text-sm font-semibold text-slate-700 dark:text-gray-400">New Colorfull Shoes </h5>
                                                                                <span class="block  font-medium text-slate-500">Size-04-15 (Model 2023)</span>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <ul class="flex">
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                        </ul>
                                                                    </td>

                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <span class="bg-green-600/5 text-green-600 text-[11px] font-medium px-2.5 py-0.5 rounded h-5">SUCCESS</span>
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        20 Feb 2023
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <a href="#"><i class="icofont-ui-edit text-lg text-gray-500 dark:text-gray-400"></i></a>
                                                                        <a href="#"><i class="icofont-ui-delete text-lg text-red-500 dark:text-red-400"></i></a>
                                                                    </td>
                                                                </tr>
                                                                 3 
                                                                <tr class="bg-white border-b border-dashed dark:bg-gray-900 dark:border-gray-700/40">
                                                                    <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">
                                                                        <div class="flex items-center">
                                                                            <img src="assets/images/products/pro-3.png" alt="" class="me-2 h-8 inline-block">
                                                                            <div class="self-center">                                                                        
                                                                                <h5 class="text-sm font-semibold text-slate-700 dark:text-gray-400">Mannat HD, Smart LED Fire TV </h5>
                                                                                <span class="block  font-medium text-slate-500">32 inc (Model 2023)</span>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <ul class="flex">
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                            <li><i class="icofont-star text-yellow-400"></i></li>
                                                                        </ul>
                                                                    </td>

                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <span class="bg-green-600/5 text-green-600 text-[11px] font-medium px-2.5 py-0.5 rounded h-5">SUCCESS</span>
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        20 Feb 2023
                                                                    </td>
                                                                    <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                        <a href="#"><i class="icofont-ui-edit text-lg text-gray-500 dark:text-gray-400"></i></a>
                                                                        <a href="#"><i class="icofont-ui-delete text-lg text-red-500 dark:text-red-400"></i></a>
                                                                    </td>
                                                                </tr>
                                                            </tbody>-->
                                                        </table>
                                                    </div><!--end div-->
                                                </div><!--end div-->
                                            </div><!--end grid-->
                                            <div class="flex justify-between">
                                                <div class="self-center">
                                                    <p class="dark:text-slate-400">Showing 1 - 20 of 1,524</p>
                                                </div>
                                                <div class="self-center">
                                                    <ul class="inline-flex items-center -space-x-px">
                                                        <li>
                                                            <a href="#" class=" py-2 px-3 ms-0 leading-tight text-gray-500 bg-white rounded-l-lg border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-900 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                                                <i class="icofont-simple-left"></i>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="#" class="py-2 px-3 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-900 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">1</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" aria-current="page" class="z-10 py-2 px-3 leading-tight text-brand-600 bg-brand-50 border border-brand-300 hover:bg-brand-100 hover:text-brand-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white">2</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" class="py-2 px-3 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-900 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">3</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" class=" py-2 px-3 leading-tight text-gray-500 bg-white rounded-r-lg border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-900 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                                                <i class="icofont-simple-right"></i>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>                                        
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
    </body>
</html>