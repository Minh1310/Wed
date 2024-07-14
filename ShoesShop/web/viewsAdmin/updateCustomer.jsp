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
        
        <jsp:include page="components/notification.jsp"/>



        <div class="ltr:flex flex-1 rtl:flex-row-reverse">
            <div class="page-wrapper relative ltr:ms-auto rtl:me-auto rtl:ms-0 w-[calc(100%-260px)] px-4 pt-[64px] duration-300">
                <div class="xl:w-full">        
                    <div class="flex flex-wrap">
                        <div class="flex items-center py-4 w-full">
                            <div class="w-full">                    
                                <div class="">
                                    <div class="flex flex-wrap justify-between">
                                        <div class="items-center ">
                                            <h1 class="font-medium text-3xl block dark:text-slate-100">Update Customer</h1>
                                            <h1 style="color: red" class="font-medium text-3xl block dark:text-slate-100">
                                                <c:if test="${not empty messSuccess}">
                                                    ${messSuccess}
                                                    <c:set  var="messSuccess" value="${null}" scope="session" />
                                                </c:if>

                                                <c:if test="${not empty messError}">
                                                    ${messError}
                                                    <c:set var="messError" value="${null}" scope="session" />
                                                </c:if>
                                            </h1>

                                            <ol class="list-reset flex text-sm">
                                                <li><a href="#" class="text-gray-500 dark:text-slate-400">Robotech</a></li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-gray-500 dark:text-slate-400">Admin</li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-primary-500 hover:text-primary-600 dark:text-primary-400">Update Customer</li>
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
                <form onsubmit="return validate()" method="Post" action="updateCustomer">
                    <input type="text" name="id" hidden="" value="${user.id}">
                    <div class="xl:w-full min-h-[calc(100vh-138px)] relative pb-14"> 
                        <div class="grid grid-cols-12 gap-4 justify-between">
                            <div class="col-span-12">
                                <div class="w-full relative mb-4">  
                                    <div class="flex-auto p-0 md:p-4">  
                                        <div class="mb-2">
                                            <label for="first_name" class="font-medium text-sm text-slate-600 dark:text-slate-400">First Name</label>
                                            <input type="text" id="first_name" name="first_name" value="${user.first_name}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="First Name" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="last_name" class="font-medium text-sm text-slate-600 dark:text-slate-400">Last Name</label>
                                            <input type="text" id="last_name" name="last_name" value="${user.last_name}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="Last Name" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="email" class="font-medium text-sm text-slate-600 dark:text-slate-400">Email</label>
                                            <input type="email" id="email" name="email" value="${user.email}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="Email" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="password" class="font-medium text-sm text-slate-600 dark:text-slate-400">Password</label>
                                            <input type="password" id="password" name="password" value="${user.password}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="Password" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="re-password" class="font-medium text-sm text-slate-600 dark:text-slate-400">re-password</label>
                                            <input type="password" id="re-password" name="re-password" value="${user.password}" class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="Password" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="telephone" class="font-medium text-sm text-slate-600 dark:text-slate-400">Telephone</label>
                                            <input type="text" id="telephone" name="telephone" value="${user.telephone}"  class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="Telephone" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="gender" class="font-medium text-sm text-slate-600 dark:text-slate-400">Gender</label>
                                            <select id="gender" class="form-select w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" required>
                                                <option value="" disabled selected>-- Select Gender --</option>
                                                <option value="true" ${user.gender?"selected":""}>Male</option>
                                                <option value="false"  ${!user.gender?"selected":""}>Female</option>
                                            </select>
                                        </div>
                                        <div class="mb-2">
                                            <label for="" class="font-medium text-sm text-slate-600 dark:text-slate-400">Create At</label>
                                            <input type="text" id="created_at" name="created_at" value="${user.created_at}"  class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="CreatedAt" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="" class="font-medium text-sm text-slate-600 dark:text-slate-400">Modified At</label>
                                            <input type="text" id="modified_at" name="" value="${user.modified_at}"  class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" placeholder="ModifiedAt" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="" class="font-medium text-sm text-slate-600 dark:text-slate-400">Address Line</label>
                                            <input type="text" id="" name="" value="${user.userAddress.addressLine}"  class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" readonly  placeholder="Address Line" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="" class="font-medium text-sm text-slate-600 dark:text-slate-400">City</label>
                                            <input type="text" id="" name="" value="${user.userAddress.city}"  class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" readonly  placeholder="City" required>
                                        </div>
                                        <div class="mb-2">
                                            <label for="" class="font-medium text-sm text-slate-600 dark:text-slate-400">Country</label>
                                            <input type="text" id="" name="" value="${user.userAddress.country}"  class="form-input w-full rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700" readonly  placeholder="Country" required>
                                        </div>
                                        <div class="mb-2">
                                            <label class="font-medium text-sm text-slate-600 dark:text-slate-400">Role</label>
                                            <div class="flex items-center space-x-4">
                                                <c:forEach var="role" items="${listRole}">
                                                    <c:if test="${role.name == 'Customer'}">
                                                        <input type="radio" id="${role.name}" ${user.role_id == role.id ? "checked" : ""} name="role" value="${role.id}" class="form-radio text-primary-500 dark:text-primary-300 focus:ring-primary-500 dark:focus:ring-primary-300" required>
                                                        <label for="${role.name}" class="text-sm text-slate-600 dark:text-slate-400">${role.name}</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="mb-2">
                                            <label class="font-medium text-sm text-slate-600 dark:text-slate-400">Status</label>
                                            <div class="flex items-center space-x-4">
                                                <c:forEach var="s" items="${listStatus}">
                                                    <input type="radio" id="${s.name}" ${user.status_id == s.id ? "checked" : ""} name="status" value="${s.id}" class="form-radio text-primary-500 dark:text-primary-300 focus:ring-primary-500 dark:focus:ring-primary-300" required>
                                                    <label for="${s.name}" class="text-sm text-slate-600 dark:text-slate-400">${s.name}</label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="">
                                            <button type="submit" class="px-4 py-2 bg-primary-500 text-white text-sm rounded hover:bg-primary-600">Save User</button>
                                        </div>
                                    </div><!--end card-body--> 
                                </div><!--end card-->                                  
                            </div><!--end col-->  
                        </div> <!--end grid-->                                        
                    </div><!--end container-->
                </form>
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
        <script src="${pageContext.request.contextPath}/js/dataTable.js"></script>
        <script src="${pageContext.request.contextPath}/js/validateForm.js"></script>

    </body>
</html>