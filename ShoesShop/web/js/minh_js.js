/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function load(selector, path) {
    const cached = localStorage.getItem(path);
    if (cached) {
        document.querySelector(selector).innerHTML = cached;
    }
    fetch(path)
            .then((res) => res.text())
            .then((html) => {
                if (html !== cached) {
                    document.querySelector(selector).innerHTML = html;
                    localStorage.setItem(path, html);
                }
            });
}

function attachQtyInputHandlers() {
    var $qtyInputs = $(".qty-input");

    if (!$qtyInputs.length) {
        return;
    }

    var $inputs = $qtyInputs.find(".product-qty");
    var $countBtn = $qtyInputs.find(".qty-count");
    var qtyMin = parseInt($inputs.attr("min"));
    var qtyMax = parseInt($inputs.attr("max"));

    function updateQuantity($input, qty) {
        var $minusBtn = $input.siblings(".qty-count_minus");
        var $addBtn = $input.siblings(".qty-count_add");

        if (isNaN(qty) || qty <= qtyMin) {
            $input.val(qtyMin);
            qty = 1;
        } else {
            $minusBtn.attr("disabled", false);

            if (qty >= qtyMax) {
                $input.val(qtyMax);
                qty = 1;
            } else {
                $input.val(qty);
                $addBtn.attr('disabled', false);
            }
        }
    }

    $inputs.off('change').on('change', function () {
        var $this = $(this);
        var qty = parseInt($this.val());
        updateQuantity($this, qty);
    });

    $countBtn.off('click').on('click', function () {
        var operator = this.dataset.action;
        var $this = $(this);
        var $input = $this.siblings(".product-qty");
        var qty = parseInt($input.val());

        if (operator === "add") {
            qty += 1;
            if (qty >= qtyMin + 1) {
                $this.siblings(".qty-count_minus").attr("disabled", false);
            }

            if (qty >= qtyMax) {
                qty = 1;
            }
        } else {
            qty = qty <= qtyMin ? qtyMin : (qty -= 1);

            if (qty === qtyMin) {
                qty = 1;
            }

            if (qty < qtyMax) {
                $this.siblings(".qty-count_add").attr("disabled", false);
            }
        }

        updateQuantity($input, qty);
    });
}


function update(action, idProduct) {
    let update = document.querySelector('#quantity_' + idProduct);
    var quantity = parseInt(update.value);
    if (action === 'add') {
        quantity++;
    }
    if (action === "minus") {
        quantity = quantity > 1 ? quantity - 1 : 1;
    }
    if (quantity <= 0 || isNaN(quantity)) {
        messege('warning', 'Must is positive number!');
        loadCart();
        return;
    }
    if (quantity > 1000) {
        messege('info', 'Must contact me with large product');
        loadCart();
        return;
    }
    $.get("/SWP391/cart?service=update",
            {
                id: idProduct,
                quantity: quantity

            },
            function (data, status) {
                if (data === "true") {
                    messege('success', 'Update success!');
                    loadCart();
                } else {
                    messege('error', 'Fail to update!');
                }

            });
}

function remove(id) {
    Swal.fire({
        title: "Are you sure?",
        text: "You want remove this product!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
    }).then((result) => {
        if (result.isConfirmed) {
            $.get("/SWP391/cart?service=remove",
                    {
                        id: id

                    },
                    function (data, status) {
                        if (data === "true") {
                            messege('success', 'Remove success!');
                            loadCart();
                        } else {
                            messege('error', 'Fail to remove!');
                        }

                    });
        }
    });

}

function messege(action, message) {
    const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.style.marginTop = '100px';
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
        }
    });
    Toast.fire({
        icon: action,
        title: message
    });
}

function getToTal() {
    // Select all elements with the class name 'value'
    const elements = getProduct();
    var total = 0;
    // Loop through the elements and add their values to the total
    elements.forEach(element => {
//                    alert(element);
        let subTotal = document.getElementById('subtotal_product_' + element);
        var value = parseFloat(subTotal.textContent); // Parse the text content as a float
//                    alert(value);
        if (!isNaN(value)) { // Check if the value is a valid number
            total += value; // Double the value and add to the total
        }
    });
//                alert(total);
    // Display the total in the element with id 'total'
    document.getElementById('total_product1').textContent = "$" + total.toFixed(2);
    document.getElementById('total_product2').textContent = "$" + total.toFixed(2);
}


const getProduct = () => {
    // Select all checkboxes with the class name 'product'
    const checkboxes = document.querySelectorAll('.product');
    const checkedValues = [];

    // Loop through the checkboxes and add the value of checked ones to the array
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            checkedValues.push(checkbox.value);
        }
    });

    // Return the array of checked values
    return checkedValues;
};

document.addEventListener('DOMContentLoaded', function () {
    loadCart();
    getToTal();
    // Initially attach handlers
    attachQtyInputHandlers();
});

function loadCart() {

    $.get("/SWP391/cart?service=loadCart",
            {
                pagination: 1

            },
            function (data, status) {
//                                alert(currentPage + 1);

                $('#container_cart').html(data);
                attachQtyInputHandlers();
                getToTal();
                //      document.getElementById('#div1').innerHTML=data;
            });
}

function checkout() {
    const elements = getProduct();
    if (elements.length === 0) {
        Swal.fire({
            title: 'Empty!',
            text: 'Please buy more or click product to check out',
            icon: 'info',
            confirmButtonText: 'Continue'
        });
    } else {
        document.getElementById('checkout').value = elements;
        $('#checkoutSubmit').click();
    }
}

function getAllProduct() {
    const checkboxes = document.querySelectorAll('.product');

    // Loop through the checkboxes and add the value of checked ones to the array
    checkboxes.forEach(checkbox => {
        if (checkbox.type === 'checkbox') {
            checkbox.checked = true;
        }
    });
    getToTal();
}
