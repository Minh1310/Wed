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
        <style>
            .error {
                color: red;
                font-size: 0.9em;
                margin-top: 5px;
            }
        </style>
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
                                            <h1 class="font-medium text-3xl block dark:text-slate-100">Dashboard</h1>
                                            <ol class="list-reset flex text-sm">
                                                <li><a href="#" class="text-gray-500 dark:text-slate-400">Robotech</a></li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-gray-500 dark:text-slate-400">Admin</li>
                                                <li><span class="text-gray-500 dark:text-slate-400 mx-2">/</span></li>
                                                <li class="text-primary-500 hover:text-primary-600 dark:text-primary-400">Dashboard</li>
                                            </ol>
                                        </div>
                                        <div class="flex items-center">
                                            <form action="dashboard" method="get" onsubmit="return validateForm()"> 
                                                <input placeholder="Select date" type="text" name="date" id="dateRange" 
                                                       value="${param.date}"
                                                       class="form-input w-64 rounded-md border inline-block border-primary-500/60 dark:border-primary-500/60 text-primary-500 bg-transparent px-3 py-1 focus:outline-none focus:ring-0 placeholder:text-slate-400/70 placeholder:font-normal placeholder:text-sm hover:border-primary-400 focus:border-primary-500 dark:focus:border-primary-500 dark:hover:border-slate-700">
                                                <button type="submit">View</button>
                                                <div id="errorMessage" class="error"></div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <form action="dashboard" method="get" class="bg-white p-6 rounded-lg shadow-md w-full max-w-sm">
                                    <h2 class="text-xl font-semibold mb-4">Select Date Range</h2>
                                    <div class="mb-4">
                                        <label for="startDate" class="block text-sm font-medium text-gray-700">Start Date</label>
                                        <input type="date" id="startDate" name="startDate" value="${startDate}" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                                    </div>
                                    <div class="mb-4">
                                        <label for="endDate" class="block text-sm font-medium text-gray-700">End Date</label>
                                        <input type="date" id="endDate" name="endDate" value="${endDate}" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                                    </div>
                                    <button type="submit" class="w-full bg-indigo-600 text-dark py-2 px-4 rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Submit</button>
                                </form>
                                <div class="mt-4">
                                    <h2 class="font-medium text-2xl block dark:text-slate-100">Statistics</h2>
                                    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
                                        <div class="bg-white dark:bg-gray-800 p-4 rounded shadow">
                                            <h3 class="font-medium text-xl dark:text-slate-100">Orders</h3>
                                            <p>Success: ${success}</p>
                                            <p>Cancelled: ${cancel}</p>
                                            <p>Process: ${process}</p>
                                        </div>
                                        <div class="bg-white dark:bg-gray-800 p-4 rounded shadow">
                                            <h3 class="font-medium text-xl dark:text-slate-100">Revenues</h3>
                                            <p>Total: ${totalRevenue}</p>
                                            <c:forEach var="entry" items="${revenueByCategory}">
                                                <span><strong>${entry.key}:</strong> <span class="revenue-value">${entry.value}</span>
                                                </span><br>
                                                </c:forEach>
                                        </div>
                                        <div class="bg-white dark:bg-gray-800 p-4 rounded shadow">
                                            <h3 class="font-medium text-xl dark:text-slate-100">Customers</h3>
                                            <p>Newly Registered: ${newUser}</p>
                                            <p>Newly Bought: ${totalUserOrder}</p>
                                        </div>
                                        <div class="bg-white dark:bg-gray-800 p-4 rounded shadow">
                                            <h3 class="font-medium text-xl dark:text-slate-100">Feedbacks</h3>
                                            <p>Average Star: ${totalStar}</p>
                                            <c:forEach var="entry" items="${avgStar}">
                                                <span><strong>${entry.key}:</strong> <span>${entry.value}</span></span><br>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="mt-4">
                                        <h2 class="font-medium text-2xl block dark:text-slate-100">Order Counts Trend</h2>
                                        <div id="orderCountsTrend" class="bg-white dark:bg-gray-800 p-4 rounded shadow"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--end container-->
            </div><!--end /div-->
        </div>
        <!-- JAVASCRIPTS -->
        <script src="viewsAdmin/assets/libs/lucide/umd/lucide.min.js"></script>
        <script src="viewsAdmin/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="viewsAdmin/assets/libs/flatpickr/flatpickr.min.js"></script>
        <script src="viewsAdmin/assets/libs/apexcharts/apexcharts.min.js"></script>
        <script>
                                                document.addEventListener('DOMContentLoaded', (event) => {
                                                    const today = new Date().toISOString().split('T')[0];
                                                    document.getElementById('startDate').setAttribute('max', today);
                                                    document.getElementById('endDate').setAttribute('max', today);
                                                });
        </script>
        <script src="viewsAdmin/assets/js/app.js"></script>
        <script>
                                                // Date range picker
                                                flatpickr("#dateRange", {
                                                    mode: "range",
                                                    dateFormat: "Y-m-d",
                                                    onClose: function (selectedDates, dateStr, instance) {
                                                        // Fetch and update the data based on the selected date range
                                                    }
                                                });

                                                // Sample data for chart
                                                var orderCountsData = {
                                                    series: [{
                                                            name: 'Success',
                                                            data: [10, 41, 35, 51, 49, 62, 69]
                                                        }, {
                                                            name: 'All',
                                                            data: [23, 42, 35, 27, 43, 22, 17]
                                                        }],
                                                    chart: {
                                                        height: 350,
                                                        type: 'line',
                                                        zoom: {
                                                            enabled: false
                                                        }
                                                    },
                                                    dataLabels: {
                                                        enabled: false
                                                    },
                                                    stroke: {
                                                        curve: 'straight'
                                                    },
                                                    title: {
                                                        text: 'Order Counts Trend',
                                                        align: 'left'
                                                    },
                                                    grid: {
                                                        row: {
                                                            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                                                            opacity: 0.5
                                                        },
                                                    },
                                                    xaxis: {
                                                        categories: ['2021-11-01', '2021-11-02', '2021-11-03', '2021-11-04', '2021-11-05', '2021-11-06', '2021-11-07'],
                                                    }
                                                };

                                                var chart = new ApexCharts(document.querySelector("#orderCountsTrend"), orderCountsData);
                                                chart.render();

                                                // Function to format number as currency
                                                function formatCurrency(num) {
                                                    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " VNĐ";
                                                }

                                                // Function to format all revenue values on the page
                                                function formatAllRevenues() {
                                                    const elements = document.querySelectorAll('.revenue-value');
                                                    elements.forEach(element => {
                                                        const value = parseFloat(element.textContent);
                                                        element.textContent = formatCurrency(value);
                                                    });
                                                }

                                                // Call formatAllRevenues on page load
                                                window.onload = formatAllRevenues;

                                                // Validate select date not null
                                                function validateForm() {
                                                    const dateInput = document.getElementById('dateRange');
                                                    const errorMessage = document.getElementById('errorMessage');

                                                    if (!dateInput.value) {
                                                        errorMessage.textContent = "Please select a date range.";
                                                        return false; // Prevent form submission
                                                    }

                                                    errorMessage.textContent = ""; // Clear any previous error messages
                                                    return true; // Allow form submission
                                                }
        </script>
    </body>
</html>
