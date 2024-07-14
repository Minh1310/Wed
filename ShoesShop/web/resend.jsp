<%-- 
    Document   : resend
    Created on : May 18, 2024, 11:40:50 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resend Email</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        #countdown {
            font-size: 1.5em;
            color: #555;
            margin: 20px 0;
        }
        #resendButton {
            display: none;
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        #resendButton:hover {
            background-color: #0056b3;
        }
        .hidden {
            display: none;
        }
    </style>
    <script>
        let countdownTime = 60; // 60 seconds countdown

        function startCountdown() {
            let countdownElement = document.getElementById('countdown');
            let resendButton = document.getElementById('resendButton');
            let interval = setInterval(function() {
                if (countdownTime <= 0) {
                    clearInterval(interval);
                    countdownElement.textContent = "You can now resend the email.";
                    resendButton.style.display = 'inline-block';
                } else {
                    countdownElement.textContent = "Please wait " + countdownTime + " seconds to resend the email.";
                    countdownTime--;
                }
            }, 1000);
        }

        window.onload = startCountdown;
    </script>
</head>
<body>
    <div class="container">
        <h1>Resend Verification Email</h1>
        <div id="countdown"></div>
        <form action="resend" method="get">
            <button id="resendButton" type="submit">Resend Email</button>
        </form>
    </div>
</body>
</html>
