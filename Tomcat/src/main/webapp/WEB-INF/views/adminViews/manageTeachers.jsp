<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Manage Teachers</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    .container {
      width: 80%;
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
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    table, th, td {
      border: 1px solid #ddd;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
    .btn-container {
      text-align: center;
      margin-top: 20px;
    }
    .btn {
      background-color: #007bff;
      color: #fff;
      padding: 10px 20px;
      text-decoration: none;
      border-radius: 5px;
      margin: 5px;
      display: inline-block;
    }
    .btn:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Manage Teachers</h1>
  <table>
    <thead>
    <tr>
      <th>Teacher ID</th>
      <th>Teacher Name</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="teacher : ${TeacherAndID}">
      <td th:text="${teacher.teacherId}"></td>
      <td th:text="${teacher.teacherName}"></td>
    </tr>
    </tbody>
  </table>

  <div class="btn-container">
    <a href="/GradingSystem/admin/manageTeachers/addTeacher" class="btn">Add Teacher</a>
    <a href="/GradingSystem/admin/manageTeachers/removeTeacher" class="btn">Remove Teacher</a>
    <a href="/GradingSystem/admin/manageTeachers/assignTeacher" class="btn">Assign Teacher to Course</a>
  </div>
</div>
</body>
</html>
