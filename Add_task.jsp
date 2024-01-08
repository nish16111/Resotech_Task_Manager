<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Task</title>
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

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            box-sizing: border-box;
        }

        .input-box {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
        }

        .pretty-button {
            background-color: #4C0050;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 4px;
            transition: background-color 0.3s;
            cursor: pointer;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .pretty-button:hover {
            background-color: #00870A;
        }
    </style>
</head>
<body>
    <% String fname = (String)session.getAttribute("fname"); %>
    <h1>Welcome <%=fname %></h1>
    <form action="addtask" method="post">
        <input type="text" class="input-box" placeholder="Task Name" name="task_name" required><br>
        <input type="text" class="input-box" placeholder="Task Description" name="task_description" required><br>
        <input type="submit" class="pretty-button" value="Add Task">
    </form>
</body>
</html>
