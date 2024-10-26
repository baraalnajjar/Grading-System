<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Super Admin Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            font-size: 24px;
            color: #333;
        }
        h2 {
            font-size: 20px;
            color: #555;
            margin-top: 10px;
        }
        .menu-item {
            margin-bottom: 20px;
        }
        .menu-item a {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            text-decoration: none;
            border-radius: 5px;
        }
        .menu-item a:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            text-align: center;
        }
        .back-link:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome Super Admin!</h1>

    <h2>Choose an option:</h2>
    <div class="menu-item">
        <a href="<%= request.getContextPath() %>/role/superAdmin/menu/addAdmin?action=addAdmin">Add Admin</a>
    </div>
    <div class="menu-item">
        <a href="<%= request.getContextPath() %>/role/superAdmin/menu/removeAdmin?action=removeAdmin">Remove Admin</a>
    </div>
    <div class="menu-item">
        <a href="<%= request.getContextPath() %>/role/superAdmin/menu/viewAdmins?action=viewAdmins">View Admins</a>
    </div>
    <a href="<%= request.getContextPath() %>/homepage" class="back-link">Back to Home Page</a>
</div>
</body>
</html>
