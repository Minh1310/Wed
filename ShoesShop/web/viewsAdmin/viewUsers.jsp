<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="scroll-smooth group" data-sidebar="brand" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <title>Robotech - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta content="Tailwind Multipurpose Admin & Dashboard Template" name="description"/>
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
    <body data-layout-mode="light" data-sidebar-size="default" data-theme-layout="vertical" class="bg-[#EEF0FC] dark:bg-gray-900">
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
                                        <div class="items-center">
                                            <h1 class="font-medium text-3xl block dark:text-slate-100">Users</h1>
                                            <ol class="list-reset flex text-sm">
                                                <li><a href="#" class="text-gray-500 dark:text-slate-400">Robotech</a></li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-gray-500 dark:text-slate-400">Admin</li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-primary-500 hover:text-primary-600 dark:text-primary-400">Users</li>
                                            </ol>
                                        </div>
                                        <div class="flex items-center">
                                            <div class="today-date leading-5 mt-2 lg:mt-0 form-input w-auto rounded-md border inline-block border-primary-500/60 dark:border-primary-500/60 text-primary-500 bg-transparent px-3 py-1 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-primary-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700">
                                                <input type="text" class="dash_date border-0 focus:border-0 focus:outline-none" readonly required="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--end container-->
                <div class="xl:w-full min-h-[calc(100vh-138px)] relative pb-14">
                    <div class="grid grid-cols-12 sm:grid-cols-12 md:grid-cols-12 lg:grid-cols-12 xl:grid-cols-12 gap-4">
                        <div class="col-span-12 sm:col-span-12 md:col-span-12 lg:col-span-12 xl:col-span-12">
                            <div class="w-full relative mb-4">
                                <div class="flex-auto p-0 md:p-4">
                                    <div class="mb-4 border-b border-gray-200 dark:border-slate-700" data-fc-type="tab">
                                        <ul class="flex flex-wrap -mb-px text-sm font-medium text-center" aria-label="Tabs"></ul>
                                    </div>
                                    <div class="flex flex-wrap gap-4 mb-3">
                                        <a href="updateFeedback">
                                            <div class="mb-2 w-36">
                                                <button class="px-2 py-1 bg-primary-500/10 border border-transparent collapse:bg-green-100 text-primary text-sm rounded hover:bg-blue-600 hover:text-white">Create User</button>
                                            </div>
                                        </a>
                                        <div class="ms-auto">
                                            <form action="user-list" method="get">
                                                <div class="relative flex gap-4">
                                                    <div class="relative">
                                                        <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
                                                            <i data-lucide="search" class="z-[1] w-5 h-5 stroke-slate-400"></i>
                                                        </div>
                                                        <input type="search" value="${param.search}" name="search" class="form-input w-52 rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700 pl-10 p-2.5" placeholder="Search">
                                                    </div>
                                                    <div class="relative">
                                                        <select name="status" class="form-select w-52 rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700 p-2.5">
                                                            <option value="">All Status</option>
                                                            <c:forEach var="status" items="${listStatus}">
                                                                <option value="${status.id}" <c:if test="${status.id == param.status}">selected</c:if>>${status.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="relative">
                                                        <select name="role" class="form-select w-52 rounded-md mt-1 border border-slate-300/60 dark:border-slate-700 dark:text-slate-300 bg-transparent px-3 py-2 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-slate-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700 p-2.5">
                                                            <option value="">All Roles</option>
                                                            <c:forEach var="role" items="${listRole}">
                                                                <option value="${role.id}" <c:if test="${role.id == param.role}">selected</c:if>>${role.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="relative">
                                                        <button type="submit" class="px-4 py-2 bg-primary-500 text-white rounded">Filter</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                    <div id="myTabContent">
                                        <div class="active p-4 bg-gray-50 rounded-lg dark:bg-gray-800/40" id="all" role="tabpanel" aria-labelledby="all-tab">
                                            <div class="grid grid-cols-1 p-0 md:p-4">
                                                <div class="sm:-mx-6 lg:-mx-8">
                                                    <div class="relative overflow-x-auto block w-full sm:px-6 lg:px-8">
                                                        <table id="userTable" class="w-full">
                                                            <thead class="bg-gray-50 dark:bg-gray-700/20">
                                                                <tr>
                                                                    <th scope="col" data-sort="number" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">No</th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">Email</th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">First name</th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">Last name</th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">Role</th>
                                                                    <th scope="col" data-sort="text" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">Created At</th>
                                                                    <th scope="col" data-sort="number" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">Status</th>
                                                                    <th scope="col" class="p-3 text-xs font-medium tracking-wider text-left text-gray-700 dark:text-gray-400 uppercase">Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="f" items="${users}" varStatus="status">
                                                                    <!-- Feedback Row -->
                                                                    <tr class="bg-white border-b border-dashed dark:bg-gray-900 dark:border-gray-700/40">
                                                                        <td class="p-3 text-sm font-medium whitespace-nowrap dark:text-white">${status.index + 1}</td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">${f.email}</td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">${f.first_name}</td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">${f.last_name}</td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">${f.role.name}</td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">${f.created_at}</td>
                                                                        <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                            <c:if test="${f.status_id == 1}"><span style="color: green">Active</span></c:if>
                                                                            <c:if test="${f.status_id == 2}"><span style="color: red">Inactive</span></c:if>
                                                                            </td>
                                                                            <td class="p-3 text-sm text-gray-500 whitespace-nowrap dark:text-gray-400">
                                                                                <a href="customerDetail?id=${f.id}">   <i class="fas fa-eye"></i> </a>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="flex justify-center mt-4">
                                                <div class="mt-5">
                                                    <nav aria-label="Page navigation example">
                                                        <ul class="pagination flex justify-center space-x-2">
                                                            <c:if test="${currentPage > 1}">
                                                                <li class="page-item">
                                                                    <a class="btn btn-primary text-lg px-4 py-2 bg-blue-500 text-white" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=${currentPage - 1}"><</a>
                                                                </li>
                                                            </c:if>
                                                            <c:choose>
                                                                <c:when test="${totalPages <= 5}">
                                                                    <c:forEach var="pageNum" begin="1" end="${totalPages}">
                                                                        <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                                                                            <a class="btn btn-primary text-lg px-4 py-2 ${pageNum == currentPage ? 'bg-blue-500 text-white' : 'text-blue-500'}" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=${pageNum}">${pageNum}</a>
                                                                        </li>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <li class="page-item ${currentPage == 1 ? 'active' : ''}">
                                                                        <a class="btn btn-primary text-lg px-4 py-2 ${currentPage == 1 ? 'bg-blue-500 text-white' : 'text-blue-500'}" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=1">1</a>
                                                                    </li>
                                                                    <li class="page-item ${currentPage == 2 ? 'active' : ''}">
                                                                        <a class="btn btn-primary text-lg px-4 py-2 ${currentPage == 2 ? 'bg-blue-500 text-white' : 'text-blue-500'}" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=2">2</a>
                                                                    </li>
                                                                    <c:if test="${currentPage > 3}">
                                                                        <li class="page-item">
                                                                            <span class="btn btn-primary text-lg px-4 py-2">...</span>
                                                                        </li>
                                                                    </c:if>
                                                                    <c:if test="${currentPage > 2 && currentPage < totalPages - 1}">
                                                                        <li class="page-item active">
                                                                            <a class="btn btn-primary text-lg px-4 py-2 bg-blue-500 text-white" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=${currentPage}">${currentPage}</a>
                                                                        </li>
                                                                    </c:if>
                                                                    <c:if test="${currentPage < totalPages - 2}">
                                                                        <li class="page-item">
                                                                            <span class="btn btn-primary text-lg px-4 py-2">...</span>
                                                                        </li>
                                                                    </c:if>
                                                                    <li class="page-item ${currentPage == totalPages - 1 ? 'active' : ''}">
                                                                        <a class="btn btn-primary text-lg px-4 py-2 ${currentPage == totalPages - 1 ? 'bg-blue-500 text-white' : 'text-blue-500'}" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=${totalPages - 1}">${totalPages - 1}</a>
                                                                    </li>
                                                                    <li class="page-item ${currentPage == totalPages ? 'active' : ''}">
                                                                        <a class="btn btn-primary text-lg px-4 py-2 ${currentPage == totalPages ? 'bg-blue-500 text-white' : 'text-blue-500'}" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=${totalPages}">${totalPages}</a>
                                                                    </li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <c:if test="${currentPage < totalPages}">
                                                                <li class="page-item">
                                                                    <a class="btn btn-primary text-lg px-4 py-2 bg-blue-500 text-white" href="?search=${param.search}&status=${param.status}&role=${param.role}&page=${currentPage + 1}">></a>
                                                                </li>
                                                            </c:if>
                                                        </ul>
                                                    </nav>
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
                                <footer class="footer bg-transparent text-center font-medium text-slate-600 dark:text-slate-400 md:text-left">
                                    &copy;
                                    <script>
                                        var year = new Date();
                                        document.write(year.getFullYear());
                                    </script>
                                    Robotech
                                    <span class="float-right hidden text-slate-600 dark:text-slate-400 md:inline-block">
                                        Crafted with <i class="ti ti-heart text-red-500"></i> by Mannatthemes
                                    </span>
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
            <script src="${pageContext.request.contextPath}/viewsAdmin/assets/js/dataTable.js"></script>
    </body>
</html>
