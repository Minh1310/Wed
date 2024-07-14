/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function () {
    var otpInputs = document.querySelectorAll(".otp-input");
    var emailOtpInputs = document.querySelectorAll(".email-otp-input");

    function setupOtpInputListeners(inputs) {
        inputs.forEach(function (input, index) {
            input.addEventListener("paste", function (ev) {
                var clip = ev.clipboardData.getData("text").trim();
                if (!/^\d{6}$/.test(clip)) {
                    ev.preventDefault();
                    return;
                }

                var characters = clip.split("");
                inputs.forEach(function (otpInput, i) {
                    otpInput.value = characters[i] || "";
                });

                enableNextBox(inputs[0], 0);
                inputs[5].removeAttribute("disabled");
                inputs[5].focus();
                updateOTPValue(inputs);
            });

            input.addEventListener("input", function () {
                var currentIndex = Array.from(inputs).indexOf(this);
                var inputValue = this.value.trim();

                if (!/^\d$/.test(inputValue)) {
                    this.value = "";
                    return;
                }

                if (inputValue && currentIndex < 5) {
                    inputs[currentIndex + 1].removeAttribute("disabled");
                    inputs[currentIndex + 1].focus();
                }

                if (currentIndex === 4 && inputValue) {
                    inputs[5].removeAttribute("disabled");
                    inputs[5].focus();
                }

                updateOTPValue(inputs);
            });

            input.addEventListener("keydown", function (ev) {
                var currentIndex = Array.from(inputs).indexOf(this);

                if (!this.value && ev.key === "Backspace" && currentIndex > 0) {
                    inputs[currentIndex - 1].focus();
                }
            });
        });
    }

    function updateOTPValue(inputs) {
        var otpValue = "";
        inputs.forEach(function (input) {
            otpValue += input.value;
        });

        if (inputs === otpInputs) {
            document.getElementById("verificationCode").value = otpValue;
        } else if (inputs === emailOtpInputs) {
            document.getElementById("emailverificationCode").value = otpValue;
        }
    }

    // Setup listeners for OTP inputs
    setupOtpInputListeners(otpInputs);
    setupOtpInputListeners(emailOtpInputs);

    // Add event listener for verify button
    document.getElementById("verifyMobileOTP").addEventListener("click", function () {
        var otpValue = document.getElementById("verificationCode").value;
        alert("Submitted OTP: " + otpValue);
        // Add your submit logic here (e.g., AJAX request or form submission)
    });

    document.getElementById("verifyEmailOTP").addEventListener("click", function () {
        var otpValue = document.getElementById("emailverificationCode").value;
        alert("Submitted Email OTP: " + otpValue);
        // Add your submit logic here
    });

    // Initial focus on first OTP input field
    otpInputs[0].focus();
    emailOtpInputs[0].focus();

    function submitChangePassword() {
        var form = document.getElementById('changepassword');
        if (form.checkValidity()) {
            var currentPassword = document.getElementById('currentPassword').value;
            var newPassword = document.getElementById('newPassword').value;
            var confirmNewPassword = document.getElementById('confirmNewPassword').value;

            if (newPassword !== confirmNewPassword) {
                alert('New passwords do not match.');
                return;
            }

            // Add your password change logic here
            // Example: AJAX request to the server

            alert('Password changed successfully.');
            $('#changePasswordModal').modal('hide');
        } else {
            form.reportValidity();
        }
    }

    function checkEmail(element) {
        const pattern = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        // Get the value of the input field
        var value = element.value;
        // Get the message
        var msg = document.querySelector('#email_err');
        console.log(msg);
        console.log(element);
        // Test if the value matches the regex pattern
        if (value.match(pattern)) {
            msg.style.display = 'none';
            element.style.borderBottomColor = '#059862';
            console.log("Input matches the regex pattern");
            flag = true;
            return true;
        } else {
            msg.innerHTML = 'The email is not incorrect format!';
            msg.style.display = 'block';
            element.style.borderBottomColor = 'red';
            console.log(pattern);
            console.log("Input does not match the regex pattern");
            flag = false;
            return false;
        }
    }

});


