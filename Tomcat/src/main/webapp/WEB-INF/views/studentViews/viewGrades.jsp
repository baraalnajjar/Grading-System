<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Grades</title>
    <style>
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
        h2 {
            font-size: 20px;
            color: #555;
            margin-top: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            text-align: center;
        }
        .back-link:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome, <%= request.getAttribute("username") %>!</h1>
    <h2>Your Courses and Grades:</h2>
    <table>
        <thead>
        <tr>
            <th>Course</th>
            <th>Grade</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Retrieve attributes from the request
            String[] courses = (String[]) request.getAttribute("courses");
            String[] grades = (String[]) request.getAttribute("grades");

            // Ensure that both arrays are not null and have the same length
            if (courses != null && grades != null && courses.length == grades.length) {
                for (int i = 0; i < courses.length; i++) {
        %>
        <tr>
            <td><%= courses[i] %></td>
            <td><%= grades[i] %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="2">No data available</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="<%= request.getContextPath() %>/homepage" class="back-link">Back to Home page and Log out</a>
</div>
</body>
</html>
