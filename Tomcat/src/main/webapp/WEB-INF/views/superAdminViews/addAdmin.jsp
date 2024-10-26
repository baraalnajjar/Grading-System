<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Admin</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="password"] {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            width: 100%;
            box-sizing: border-box;
        }
        input[type="submit"] {
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .message {
            text-align: center;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-size: 14px;
        }
        .success-message {
            background-color: #d4edda;
            color: #155724;
        }
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
        }
        .back-link {
            display: inline-block;
            text-align: center;
            padding: 10px;
            margin-top: 20px;
            background-color: #28a745;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
        }
        .back-link:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Add New Admin</h1>

    <%
        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");
    %>

    <% if (successMessage != null) { %>
    <div class="message success-message">
        <%= successMessage %>
    </div>
    <% } %>

    <% if (errorMessage != null) { %>
    <div class="message error-message">
        <%= errorMessage %>
    </div>
    <% } %>

    <form action="<%= request.getContextPath() %>/role/superAdmin/menu/addAdmin" method="post">
        <label for="name">Admin Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="username">Admin Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Admin Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Add Admin">
    </form>

    <a href="<%= request.getContextPath() %>/role/superAdmin/menu/viewSuperAdminOptions" class="back-link">Back to Menu</a>
</div>

</body>
</html>
