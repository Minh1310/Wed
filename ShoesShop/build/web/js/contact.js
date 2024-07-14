/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function getTotalContact() {
// Select all elements with the class name 'value'
    const elements = document.getElementsByClassName('subtotal');
    var total = 0;
    // Loop through the elements and add their values to the total
    Array.from(elements).forEach(element => {
        var value = parseFloat(element.textContent); // Parse the text content as a float
        // alert(value);
        if (!isNaN(value)) { // Check if the value is a valid number
            total += value; // Double the value and add to the total
        }
    });
    //  lert(total);
    // Display the total in the element with id 'total'
    document.getElementById('total').textContent = "$" + total.toFixed(2);
    document.getElementById('total_form').value = total.toFixed(2);
}

$(document).ready(function () {
//Take province
    $.getJSON('https://esgoo.net/api-tinhthanh/1/0.htm', function (data_tinh) {
        if (data_tinh.error === 0) {
            $.each(data_tinh.data, function (key_tinh, val_tinh) {
                $("#province").append('<option value="' + val_tinh.id + '">' + val_tinh.full_name_en + '</option>');
            });
            $("#province").change(function (e) {
                var idtinh = $(this).val();
                setTimeout(function () {
                    setAddress();
                }, 1000);
                //Take district
                $.getJSON('https://esgoo.net/api-tinhthanh/2/' + idtinh + '.htm', function (data_quan) {
                    if (data_quan.error === 0) {
                        $("#district").html('<option value="" disabled selected>Select District</option>');
                        $("#commune").html('<option value="" disabled selected>Select Commune</option>');
                        $.each(data_quan.data, function (key_quan, val_quan) {
                            $("#district").append('<option value="' + val_quan.id + '">' + val_quan.full_name_en + '</option>');
                        });
                        //Take commune 
                        $("#district").change(function (e) {
                            var idquan = $(this).val();
                            $.getJSON('https://esgoo.net/api-tinhthanh/3/' + idquan + '.htm', function (data_phuong) {
                                if (data_phuong.error === 0) {
                                    $("#commune").html('<option value="" disabled selected>Select Commune</option>');
                                    $.each(data_phuong.data, function (key_phuong, val_phuong) {
                                        $("#commune").append('<option value="' + val_phuong.id + '">' + val_phuong.full_name_en + '</option>');
                                    });
                                    setTimeout(function () {
                                        setAddress();
                                    }, 1000);
                                }
                            });
                            $("#commune").change(function (e) {
                                setTimeout(function () {
                                    setAddress();
                                }, 1000);
                            });
                        });
                    }
                });
            });
        }
    });
    setTimeout(function () {
        getAddress();
    }, 1000);
});

function setAddress() {
            var address="";
            var province = document.getElementById("province");
            if(province){
              address = address + province.options[province.selectedIndex].text;      
            }
            var district = document.getElementById("district");
            if(district){
                address = address + " " + district.options[district.selectedIndex].text;
            }
            var commune = document.getElementById("commune");
            if(commune){
                address = address + " " + commune.options[commune.selectedIndex].text;
            }
             
            document.getElementById("address").value = address;
    }
