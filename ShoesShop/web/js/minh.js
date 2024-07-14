
//Pagination of product list
document.addEventListener('DOMContentLoaded', function () {
                const ITEMS_PER_PAGE = 5;
                const TOTAL_PAGINATION_BUTTONS = 50;
                const MAX_VISIBLE_PAGINATION_BUTTONS = 5;
                const itemsContainer = document.getElementById('containerProduct');
                const paginationContainer = document.getElementById('pagination');
                let currentPage = 0;

                function getItemsForPage(pageNumber) {
                    const start = pageNumber * ITEMS_PER_PAGE;
                    const end = Math.min(start + ITEMS_PER_PAGE, TOTAL_PAGINATION_BUTTONS);
                }

                function updateItems() {
                    $.get("/SWP391/pagination",
                            {
                                pagination: currentPage + 1

                            },
                            function (data, status) {
//                                alert(currentPage + 1);
                                $('#containerProduct').html(data);
                                $('#toTop').click();

                                //      document.getElementById('#div1').innerHTML=data;
                            });
                }

                function updatePagination() {
                    paginationContainer.innerHTML = '';
                    const totalPages = Math.ceil(TOTAL_PAGINATION_BUTTONS / ITEMS_PER_PAGE);
                    const startPage = Math.max(0, Math.min(currentPage - Math.floor(MAX_VISIBLE_PAGINATION_BUTTONS / 2), totalPages - MAX_VISIBLE_PAGINATION_BUTTONS));
                    const endPage = Math.min(totalPages, startPage + MAX_VISIBLE_PAGINATION_BUTTONS);

                    if (currentPage > 0) {
                        paginationContainer.appendChild(createButton('<<', 0));
                        paginationContainer.appendChild(createButton('<', currentPage - 1));
                    }

                    for (let i = startPage; i < endPage; i++) {
                        paginationContainer.appendChild(createButton(i + 1, i));
                    }

                    if (currentPage < totalPages - 1) {
                        paginationContainer.appendChild(createButton('>', currentPage + 1));
                        paginationContainer.appendChild(createButton('>>', totalPages - 1));
                    }
                }

                function createButton(text, pageIndex) {
                    const li = document.createElement('li');
                    const button = document.createElement('a');
                    button.textContent = text;
//			button.disabled = pageIndex === currentPage;
                    if (pageIndex === currentPage) {
                        button.classList.add('active');
                        button.classList.add('btn_1');
//                           button.style.color='white';
                        button.style.backgroundColor = "#FFC107";
                    }
                    let index = parseInt(pageIndex)+ 1;
                    button.href = "#" + index;
                    button.addEventListener('click', function () {
                        currentPage = pageIndex;
                        updateItems();
                        updatePagination();
                    });
                    li.appendChild(button);
                    return li;
                }

                // Initialize the pagination
                updateItems();
                updatePagination();
            });
            
