<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>    
<%@ page import="java.util.List" %>
<%@ page import="com.bean.TaskBean" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Tasks</title>
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

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            border: 1px solid #4C0050;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #4C0050;
            color: white;
        }

        .toggle-button {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            border: 2px solid #ffb6c1;
            background-color: #fff;
            cursor: pointer;
            transition: background-color 0.3s, border-color 0.3s;
        }

        .filled {
            background-color: #ffb6c1;
            border-color: #ffb6c1;
        }

        .remove-button {
            display: none;
            background-color: #FF0000;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        .remove-button:hover {
            background-color: #cc0000;
        }

        a {
            color: #4C0050;
            text-decoration: none;
            font-weight: bold;
            margin-top: 10px;
        }

        a:hover {
            color: #00870A;
        }
    </style>
</head>
<body>
    <% 
        String fname = (String)session.getAttribute("fname");
        List<TaskBean> all_tasks = (List<TaskBean>)session.getAttribute("alltasks");
     // Set the initial value in some method or controller
        session.setAttribute("toggleValue", 0);
    %>

    <h1><%=fname %>'s Tasks</h1>

    <table>
        <tr>
            <th>Task Name</th>
            <th>Task Description</th>
            <th>Completed</th>
            <th>Actions</th>
            <!-- Add more columns as needed -->
        </tr>
        <% if (all_tasks != null) { %>
            <% for (TaskBean row : all_tasks) { %>
                <tr>
                    <td><%= row.getTask_name() %></td>
                    <td><%= row.getTask_description() %></td>
                    <td>
                        <input type="button" id="myButton_<%= row.getTid()%>" class="toggle-button" onclick="toggleButton('<%= row.getTid()%>')">
                    </td>
                    <td>
                        <button id="removeButton_<%= row.getTid()%>" class="remove-button" onclick="removeTask('<%= row.getTid()%>')">  Remove Task</button>
                    </td>
                    
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="4">No tasks available</td>
            </tr>
        <% } %>
    </table>

    <a href="addtask"> Click here to add a task!</a>
    
    <script>
        function toggleButton(id) {
            var button = document.getElementById('myButton_' + id);
            button.classList.toggle('filled');

            var removeButton = document.getElementById('removeButton_' + id);
            removeButton.style.display = removeButton.style.display === 'none' ? 'inline-block' : 'none';

            var xhr = new XMLHttpRequest();
            xhr.open('POST', '${pageContext.request.contextPath}/changeTaskStatus?taskId=' + id, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        console.log(xhr.responseText);
                        // Handle the response from the controller if needed
                    } else {
                        console.error('Error sending data to controller. Status:', xhr.status);
                    }
                }
            };
            xhr.send();
        }
        
        function removeTask(taskId) {
            console.log('Remove Task clicked for Task ID:', taskId);
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '${pageContext.request.contextPath}/deleteTask?taskId=' + taskId, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        console.log(xhr.responseText);
                        // Handle the response from the controller if needed
                    } else {
                        console.error('Error sending data to controller. Status:', xhr.status);
                    }
                }
            };
            xhr.send();
        }
    </script>
</body>
</html>
