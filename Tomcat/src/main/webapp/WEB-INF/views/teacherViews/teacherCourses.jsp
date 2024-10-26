<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Courses</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 900px;
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
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
            color: #333;
        }
        .edit-link {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .edit-link:hover {
            text-decoration: underline;
        }
        .no-data {
            text-align: center;
            color: #777;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Course Details</h1>

    <%
        // Retrieve the courses data from the request
        List<String[]> courses = (List<String[]>) request.getAttribute("courses");

        if (courses != null && courses.size() == 6) { // Adjusted for 6 lists (including IDs)
            // Extract arrays from the list
            String[] ids = courses.get(0); // Added for IDs
            String[] names = courses.get(1);
            String[] averages = courses.get(2);
            String[] medians = courses.get(3);
            String[] highs = courses.get(4);
            String[] lows = courses.get(5);

            // Validate all arrays are of the same length
            if (names.length == averages.length &&
                    names.length == medians.length &&
                    names.length == highs.length &&
                    names.length == lows.length &&
                    names.length == ids.length) { // Added validation for IDs
    %>

    <table>
        <thead>
        <tr>
            <th>Course ID</th> <!-- Added Course ID column -->
            <th>Course Name</th>
            <th>Average</th>
            <th>Median</th>
            <th>Highest</th>
            <th>Lowest</th>
            <th>Edit Grades</th>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < names.length; i++) { %>
        <tr>
            <td><%= ids[i] %></td> <!-- Display Course ID -->
            <td><%= names[i] %></td>
            <td><%= averages[i] %></td>
            <td><%= medians[i] %></td>
            <td><%= highs[i] %></td>
            <td><%= lows[i] %></td>
            <td><a href="<%= request.getContextPath() %>/role/teacher/editGrades?courseID=<%= ids[i] %>" class="edit-link">Edit</a></td> <!-- Use Course ID in Edit link -->
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

    <% } else { %>
    <p class="no-data">No courses available.</p>
    <% } %>
</div>
</body>
</html>
