<%-- 
    Document   : test.jsp
    Created on : May 21, 2024, 4:01:54 PM
    Author     : Nhat Anh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*, dao.*, util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modal Example with AJAX</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        .modal-title {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- Trigger Button -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" 
            data-name="Product 1" 
            data-colors='["Red", "Green", "Blue"]' data-sizes='["S", "M", "L"]'>
        Open Modal for Product 1
    </button>
    <h1><%=request.getAttribute("hello")%></h1>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="modalForm">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="colorSelect">Color</label>
                            <select id="colorSelect" name="color" class="form-control"></select>
                        </div>
                        <div class="form-group">
                            <label for="sizeSelect">Size</label>
                            <select id="sizeSelect" name="size" class="form-control"></select>
                        </div>
                        <!-- Add other form fields if needed -->
                        <input type="hidden" id="productName" name="name">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="close">Close</button>
                        <button type="button" class="btn btn-primary" id="saveChanges">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Success Message -->
    <div id="successMessage" class="alert alert-success" role="alert" style="display:none;">
        Your changes have been saved successfully!
    </div>


    <script>
        $('#exampleModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var name = button.data('name'); // Extract info from data-* attributes
            var colors = button.data('colors');
            var sizes = button.data('sizes');

            var modal = $(this);
            modal.find('.modal-title').text(name); // Set the title
            modal.find('#productName').val(name); // Set the hidden input value

            // Populate the color dropdown
            var colorSelect = modal.find('#colorSelect');
            colorSelect.empty();
            colors.forEach(function(color) {
                colorSelect.append('<option value="' + color + '">' + color + '</option>');
            });

            // Populate the size dropdown
            var sizeSelect = modal.find('#sizeSelect');
            sizeSelect.empty();
            sizes.forEach(function(size) {
                sizeSelect.append('<option value="' + size + '">' + size + '</option>');
            });
        });

        $('#saveChanges').on('click', function() {
            var formData = $('#modalForm').serialize();

            $.ajax({
                url: '/SE1841/Demo',
                type: 'POST',
                data: formData,
                success: function(response) {
                    // Handle the response from the servlet
                    $('#exampleModal').click();
                    $('#successMessage').show().delay(3000).fadeOut();
                    // Ensure the modal button is re-enabled
//                    $('[data-target="#exampleModal"]').prop('disabled', false);
                },
                error: function(xhr, status, error) {
                    // Handle the error
                    console.error(error);
                }
            });
        });
    </script>
     <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

