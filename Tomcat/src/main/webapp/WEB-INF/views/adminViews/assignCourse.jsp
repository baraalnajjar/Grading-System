<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assign Teacher to Course</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 40%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-size: 18px;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            width: 100%;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        #message {
            margin-top: 20px;
            color: green;
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Assign Teacher to Course</h1>
    <form id="assignCourseForm">
        <label for="teacherID">Teacher ID:</label>
        <input type="text" id="teacherID" name="teacherID" required>

        <label for="courseID">Course ID:</label>
        <input type="text" id="courseID" name="courseID" required>

        <button type="button" class="btn" onclick="submitAssignCourse()">Assign Teacher</button>
    </form>
    <div id="message"></div>
</div>

<script>
    function submitAssignCourse() {
        var form = document.getElementById('assignCourseForm');
        var formData = new FormData(form);

        fetch('/admin/manageCourses/assignCourse', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                document.getElementById('message').style.display = 'block';
                if (data.success) {
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').textContent = 'Course assigned to teacher successfully!';
                } else {
                    document.getElementById('message').style.color = 'red';
                    document.getElementById('message').textContent = 'Failed to assign Course to course: ' + data.error;
                }
            })
            .catch(error => {
                document.getElementById('message').style.display = 'block';
                document.getElementById('message').style.color = 'red';
                document.getElementById('message').textContent = 'An error occurred: ' + error.message;
            });
    }
</script>
</body>
</html>
