<%-- 
    Document   : modal
    Created on : May 19, 2024, 4:25:21 PM
    Author     : Nhat Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Sign In Modal -->
<div id="sign-in-dialog" class="zoom-anim-dialog mfp-hide">
    <div class="modal_header">
        <h3>Sign In</h3>
    </div>
    <form action="login" method="post">
        <div class="sign-in-wrapper">
            <!--                    <a href="#0" class="social_bt facebook">Login with Facebook</a>
                                <a href="#0" class="social_bt google">Login with Google</a>
                                <div class="divider"><span>Or</span></div>-->
            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" name="email" id="email">
                <i class="ti-email"></i>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" name="password" id="password" value="">
                <i class="ti-lock"></i>
            </div>
            <div class="clearfix add_bottom_15">
                <div class="checkboxes float-start">
                    <label class="container_check">Remember me
                        <input type="checkbox">
                        <span class="checkmark"></span>
                    </label>
                </div>
                <div class="float-end mt-1"><a id="forgot" href="javascript:void(0);">Forgot Password?</a></div>
            </div>
            <div class="text-center">
                <input type="submit" value="Log In" class="btn_1 full-width">
                <h5 class="text-center" style="color: red">${sessionScope.error}</h5>
                Don’t have an account?
                <a style="width: 150px; color: blue" data-toggle="modal" data-target="#signUp" >
                    Sign Up
                </a>
            </div>
            <div id="forgot_pw">
                <div class="form-group">
                    <label>Please confirm login email below</label>
                    <input type="email" class="form-control" name="email_forgot" id="email_forgot">
                    <i class="ti-email"></i>
                </div>
                <p>You will receive an email containing a link allowing you to reset your password to a new preferred one.</p>
                <div class="text-center"><input type="submit" value="Reset Password" class="btn_1"></div>
            </div>
        </div>
    </form>
    <!--form -->
</div>
<!-- /Sign In Modal -->

<!-- Sign Up Modal -->
<div class="modal fade" id="signUp" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel" aria-hidden="true">
    <div style="margin-top: 100px" class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signUpModalLabel">Sign Up</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="otp" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input onkeyup="validateEmail(this)" type="email" class="form-control" name="email" id="email" required>
                        <span style="color: red" id="uEmail" class="danger_msg">${requestScope.email_err}</span>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input onkeyup="validatePassword(this)" type="password" class="form-control" name="password" id="password" required>
                        <div style="color: red" id="passwordMsg" class="danger_msg">${requestScope.password_err}</div>
                    </div>
                    <div class="form-group">
                        <label>Gender</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="true" required>
                            <label class="form-check-label" for="male">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="female" value="false">
                            <label class="form-check-label" for="female">Female</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" name="first_name" id="firstName" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" name="last_name" id="lastName" required>
                    </div>
                    <div class="form-group">
                        <label for="addressLine">Address Line</label>
                        <input type="text" class="form-control" name="address_line" id="addressLine" required>
                    </div>
                    <div class="form-group">
                        <label for="city">City</label>
                        <input type="text" class="form-control" name="city" id="city" required>
                    </div>
                    <div class="form-group">
                        <label for="country">Country</label>
                        <input type="text" class="form-control" name="country" id="country" required>
                    </div>
                    <div class="form-group">
                        <label for="telephone">Telephone</label>
                        <input onkeyup="validatePhoneNumber(this)" type="tel" class="form-control" name="telephone" id="telephone" required>
                        <span style="color: red" id="phoneNumber" class="danger_msg">${requestScope.phone_err}</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" value="Sign Up" class="btn_1 full-width">
                </div>
                <h5 class="text-center" style="color: red">${sessionScope.error}</h5>
            </form>
        </div>
    </div>
</div>
<!--/Sign Up Modal -->

<!-- Change Password Modal -->

<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="changePasswordModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changePasswordModalLabel">Change Password</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="changepassword" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="currentPassword">Current Password</label>
                        <input type="password" class="form-control" name="currentPassword" id="currentPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="newPassword">New Password</label>
                        <input onkeyup="validatePassword2(this)" type="password" class="form-control" name="newPassword" id="newPassword" required>
                        <div style="color: red" id="passwordMsg2" class="danger_msg">${requestScope.password_err}</div>
                    </div>
                    <div class="form-group">
                        <label for="confirmNewPassword">Confirm New Password</label>
                        <input onkeyup="checkPasswordAndCfPassword(this)" type="password" class="form-control" name="confirmNewPassword" id="confirmNewPassword" required>
                        <div style="color: red" id="cfPassowrdMsg" class="danger_msg">${requestScope.cfPassword_err}</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" value="Change Password" class="btn_1 full-width">
                </div>
                <h5 class="text-center" style="color: red">${sessionScope.error}</h5>
            </form>
        </div>
    </div>
</div>

<!-- /Change Password Modal -->

<!-- Size modal -->
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="size-modal" id="size-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Size guide</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-striped table-sm sizes">
                        <tbody><tr>
                                <th scope="row">US Sizes</th>
                                <td>6</td>
                                <td>6,5</td>
                                <td>7</td>
                                <td>7,5</td>
                                <td>8</td>
                                <td>8,5</td>
                                <td>9</td>
                                <td>9,5</td>
                                <td>10</td>
                                <td>10,5</td>
                            </tr>
                            <tr>
                                <th scope="row">Euro Sizes</th>
                                <td>39</td>
                                <td>39</td>
                                <td>40</td>
                                <td>40-41</td>
                                <td>41</td>
                                <td>41-42</td>
                                <td>42</td>
                                <td>42-43</td>
                                <td>43</td>
                                <td>43-44</td>
                            </tr>
                            <tr>
                                <th scope="row">UK Sizes</th>
                                <td>5,5</td>
                                <td>6</td>
                                <td>6,5</td>
                                <td>7</td>
                                <td>7,5</td>
                                <td>8</td>
                                <td>8,5</td>
                                <td>9</td>
                                <td>9,5</td>
                                <td>10</td>
                            </tr>
                            <tr>
                                <th scope="row">Inches</th>
                                <td>9.25"</td>
                                <td>9.5"</td>
                                <td>9.625"</td>
                                <td>9.75"</td>
                                <td>9.9375"</td>
                                <td>10.125"</td>
                                <td>10.25"</td>
                                <td>10.5"</td>
                                <td>10.625"</td>
                                <td>10.75"</td>
                            </tr>
                            <tr>
                                <th scope="row">CM</th>
                                <td>23,5</td>
                                <td>24,1</td>
                                <td>24,4</td>
                                <td>24,8</td>
                                <td>25,4</td>
                                <td>25,7</td>
                                <td>26</td>
                                <td>26,7</td>
                                <td>27</td>
                                <td>27,3</td>
                            </tr>
                        </tbody></table>
                </div>
                <!-- /table -->
            </div>
        </div>
    </div>
</div>


<!-- Add cart Modal -->
<div style="margin-top: 100px" class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="modalFormAddToCart">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="colorSelect">Color</label>
                        <select id="colorSelect" name="color" class="form-control">

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="sizeSelect">Size</label>
                        <select id="sizeSelect" name="size" class="form-control"></select>
                    </div>
                    <!-- Add other form fields if needed -->
                    <input type="hidden"  name="service" value="addCart">
                    <input type="hidden" id="productName"name="name" >
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" id="close">Close</button>
                    <button type="button" class="btn btn-primary" id="AddToCart">Add to cart</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Add cart Modal -->




<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var name = button.data('name'); // Extract info from data-* attributes
        var modal = $(this);
        modal.find('.modal-title').text(name); // Set the title
        modal.find('#productName').val(name); // Set the hidden input value
        var search = name.replaceAll(" ","_");
        $(document).ready(function () {
            $("#colorSelect").load("product?service=getAllColorByName&name="+search);
            $("#sizeSelect").load("product?service=getAllSizeByName&name="+search);

        });

    });


    $('#AddToCart').on('click', function () {
        var form = document.getElementById('modalFormAddToCart');
        var formData = new FormData(form);
        $.post("/SWP391/cart?service=addCart",
                {
                    name: formData.get("name"),
                    color: formData.get("color"),
                    size: formData.get("size")

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
                            text: data,
                            icon: 'error',
                            confirmButtonText: 'Continue'
                        });
                    }
                });
    });
    </script>
<!-- My Profile -->

<div class="modal fade" id="myProfile" tabindex="-1" role="dialog" aria-labelledby="myProfileModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changePasswordModalLabel">My Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="profileForm" action="myProfile" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="firstName" class="form-label">First Name:</label>
                            <input type="text" id="firstName" name="firstName" class="form-control" value="${profile.first_name}" required>
                        </div>
                    </div>  
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="lastName" class="form-label">Last Name:</label>
                            <input type="text" id="lastName" name="lastName" class="form-control" value="${profile.last_name}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" id="email" readonly="" disabled="" name="email" class="form-control" value="${profile.email}" required/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="address" class="form-label">Address:</label>
                            <input type="text" id="address" name="address" class="form-control" value="${profile.getUserAddress().addressLine}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="city" class="form-label">City:</label>
                            <input type="text" id="city" name="city" class="form-control" value="${profile.getUserAddress().city}" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="country" class="form-label">Country:</label>
                            <input type="text" id="country" name="country" class="form-control" value="${profile.getUserAddress().country}" required>
                        </div>
                    </div>
                </div>


                <button type="submit" class="btn btn-primary">Update Profile</button>
            </form>
        </div>
    </div>
</div>

<!-- jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- COMMON SCRIPTS -->
<script src="js/common_scripts.min.js"></script>
<script src="js/main.js"></script>
<script src="js/validate.js"></script>