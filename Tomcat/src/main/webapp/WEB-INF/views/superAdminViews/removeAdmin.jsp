<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Remove Admin</title>
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

        input[type="text"] {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            width: 100%;
            box-sizing: border-box;
        }

        button {
            padding: 12px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #c82333;
        }

        .message {
            text-align: center;
            padding: 10px;
            margin-top: 20px;
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
    <h1>Remove Admin</h1>

    <form method="POST" action="<%= request.getContextPath() %>/role/superAdmin/menu/removeAdmin">
        <label for="adminId">Admin ID:</label>
        <input type="text" id="adminId" name="adminId" required>
        <button type="submit">Remove Admin</button>
    </form>

    <div class="message">
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </div>

    <a href="<%= request.getContextPath() %>/role/superAdmin/menu" class="back-link">Back to Menu</a>
</div>

</body>
</html>
