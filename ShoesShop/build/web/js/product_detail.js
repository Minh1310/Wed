/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$('#addFromDetail').on('click', function () {
        var form = document.getElementById('detailFormAddToCart');
        var formData = new FormData(form);
        $.post("/SWP391/cart?service=addCart",
                {
                    name: formData.get("name"),
                    color: formData.get("color"),
                    size: formData.get("size"),
                    quantity: formData.get("quantity")

                },
                function (data, status) {
                    //      alert("Data: " + data + "\nStatus: " + status);
                    $('#exampleModal').click();
                    if(data==="true"){
                        Swal.fire({
                            title: 'Add success!',
                            text: 'Click \'continue\' to explore',
                            icon: 'success',
                            confirmButtonText: 'Continue'
                        });
                    }
                    else{
                        Swal.fire({
                            title: 'Add fail!',
                            text: 'Please must fill all types',
                            icon: 'error',
                            confirmButtonText: 'Continue'
                        });
                    }
                });
    });
    
