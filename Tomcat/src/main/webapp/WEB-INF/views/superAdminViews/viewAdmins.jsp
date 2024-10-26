<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Admins</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
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
    <h1>Admins and IDs:</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Retrieve attributes from the request
            String[] adminIDs = (String[]) request.getAttribute("adminIDs");
            String[] adminUsernames = (String[]) request.getAttribute("adminUsernames");
            String[] adminNames = (String[]) request.getAttribute("adminNames");

            // Ensure the arrays are not null and have the same length
            if (adminIDs != null && adminUsernames != null && adminNames != null &&
                    adminIDs.length == adminUsernames.length && adminUsernames.length == adminNames.length) {
                for (int i = 0; i < adminIDs.length; i++) {
        %>
        <tr>
            <td><%= adminIDs[i] %></td>
            <td><%= adminUsernames[i] %></td>
            <td><%= adminNames[i] %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">No admins found</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href="<%= request.getContextPath() %>/role/superAdmin/menu/viewSuperAdminOptions" class="back-link">Back to Super Admin Menu</a>
</div>
</body>
</html>
