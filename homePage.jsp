<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Manager</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            color: #4C0050;
        }

        a {
            color: #00870A;
            text-decoration: none;
            font-weight: bold;
            margin-top: 10px;
            display: inline-block;
            padding: 10px;
            border: 2px solid #00870A;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        a:hover {
            background-color: #00870A;
            color: white;
        }

        a + a {
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <h1>Welcome to the Task Manager!!</h1>
    <a href="login">Click here to Sign in</a>
    <a href="signup">Click here to Register</a>
</body>
</html>
