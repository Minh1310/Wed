let currentSortColumnIndex = -1;
let sortAscending = true;

document.addEventListener("DOMContentLoaded", function () {
    let table = document.getElementById("tableFilter");
    let ths = table.querySelectorAll("th[data-sort]");
    ths.forEach(function (th, index) {
        th.addEventListener("click", function () {
            if (currentSortColumnIndex === index) {
                sortAscending = !sortAscending;
            } else {
                sortAscending = true;
                currentSortColumnIndex = index;
            }
            sortTable(table, index, th.getAttribute("data-sort"), sortAscending);
            updateSortIndicator(th);
        });
    });

    // Add event listener for search input
    document.getElementById('searchTable').addEventListener('input', searchTable);
});

function sortTable(table, columnIndex, dataType, ascending) {
    let tbody = table.querySelector("tbody");
    let tfoot = table.querySelector("tfoot");
    let rows = Array.from(tbody.getElementsByTagName("tr"));
    rows.push(...Array.from(tfoot.getElementsByTagName("tr"))); // Include footer rows

    let isNumber = dataType === "number";

    rows.sort(function (rowA, rowB) {
        let cellA = rowA.getElementsByTagName("td")[columnIndex].innerText;
        let cellB = rowB.getElementsByTagName("td")[columnIndex].innerText;
        if (isNumber) {
            return (parseInt(cellA) - parseInt(cellB)) * (ascending ? 1 : -1);
        } else {
            return cellA.localeCompare(cellB) * (ascending ? 1 : -1);
        }
    });

    // Remove all existing rows from tbody and tfoot
    tbody.innerHTML = "";
    tfoot.innerHTML = "";

    rows.forEach(function (row) {
        if (row.parentNode === tbody) {
            tbody.appendChild(row);
        } else if (row.parentNode === tfoot) {
            tfoot.appendChild(row);
        }
    });
}

function updateSortIndicator(th) {
    let indicator = th.querySelector(".sort-indicator");
    if (!indicator) {
        indicator = document.createElement("span");
        indicator.classList.add("sort-indicator");
        th.appendChild(indicator);
    }
    indicator.innerHTML = sortAscending ? "&#9660;" : "&#9650;";
}

function searchTable() {
    const input = document.getElementById('searchTable');
    const filter = input.value.toLowerCase();
    const table = document.getElementById('tableFilter');
    const trs = table.getElementsByTagName('tr');

    for (let i = 1; i < trs.length; i++) { // Start from 1 to skip the header row
        const tds = trs[i].getElementsByTagName('td');
        let showRow = false;

        for (let j = 0; j < tds.length; j++) {
            const td = tds[j];
            if (td) {
                if (td.textContent.toLowerCase().indexOf(filter) > -1) {
                    showRow = true;
                    break;
                }
            }
        }
        trs[i].style.display = showRow ? '' : 'none';
    }
}