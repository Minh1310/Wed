<%-- 
    Document   : newjsp
    Created on : Jun 6, 2024, 11:55:52 AM
    Author     : Nhat Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagination Carousel</title>
    <style>
/*        .items {
            display: flex;
            flex-wrap: wrap;
            margin-bottom: 20px;
        }
        .item {
            width: 100px;
            margin: 10px;
            padding: 20px;
            background-color: #f0f0f0;
            text-align: center;
        }
        .pagination {
            display: flex;
            list-style-type: none;
            padding: 0;
        }
        .pagination li {
            margin: 0 5px;
        }
        .pagination a {
            padding: 5px 10px;
        }*/
    </style>
</head>
<body>

<div class="items" id="items"></div>
 <div class="pagination__wrapper d-flex justify-content-center my-4">
                                <ul class="pagination"  id="pagination">

                                </ul>
                            </div> 

<script>
	document.addEventListener('DOMContentLoaded', function () {
		const ITEMS_PER_PAGE = 7;
		const TOTAL_PAGINATION_BUTTONS = 15;
		const MAX_VISIBLE_PAGINATION_BUTTONS = 5;
		const itemsContainer = document.getElementById('items');
		const paginationContainer = document.getElementById('pagination');
		let currentPage = 0;
	
		// Generate dummy items
		const items = [];
		for (let i = 1; i <= 300; i++) {  // Assuming we have 300 items
			items.push(`Item `+ i);
		}
	
		function getItemsForPage(pageNumber) {
			const start = pageNumber * ITEMS_PER_PAGE;
			const end = Math.min(start + ITEMS_PER_PAGE, items.length);
			return items.slice(start, end);
		}
	
		function updateItems() {
			itemsContainer.innerHTML = '';
			const itemsForPage = getItemsForPage(currentPage);
			itemsForPage.forEach(item => {
				const itemElement = document.createElement('div');
				itemElement.classList.add('item');
				itemElement.textContent = item;
				itemsContainer.appendChild(itemElement);
			});
		}
	
		function updatePagination() {
			paginationContainer.innerHTML = '';
			const totalPages = Math.ceil(items.length / ITEMS_PER_PAGE);
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
			button.disabled = pageIndex === currentPage;
			button.classList.add('active');
                        button.href="#"+currentPage;
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
	
</script>
</body>
</html>

