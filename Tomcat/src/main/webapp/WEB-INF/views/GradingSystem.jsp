<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grading System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            margin: auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-top: 50px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        label {
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        input[type="radio"] {
            margin-right: 10px;
        }
        .submit-button {
            align-self: center;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .submit-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Select Your Role</h1>
    <form action="${pageContext.request.contextPath}/role" method="get">
        <label>
            <input type="radio" name="role" value="Student" required>
            Student
        </label>
        <label>
            <input type="radio" name="role" value="Teacher" required>
            Teacher
        </label>
        <label>
            <input type="radio" name="role" value="Admin" required>
            Admin
        </label>
        <label>
            <input type="radio" name="role" value="SuperAdmin" required>
            Super Admin
        </label>
        <input type="submit" class="submit-button" value="Submit">
    </form>
</div>
</body>
</html>
