package com.gradingSystem.modules;


import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Student {

    private String name;
    private String id;
    private String username;
    private String password;
    private List<String> courses;
    private Map<String, Double> grades;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gradingSystem";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    public Student(String username) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.username = username;
        setID();
        this.courses = new ArrayList<>();
        this.grades = new HashMap<>();
        setName();
        setCourses();
        setGrades();
    }
    public void setID()  {
        String id;
        String query = "SELECT id FROM student WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getString("id");
                } else {
                    throw new RuntimeException("Username not found: " + username);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query", e);
        }

        if (id==null) {
            throw new RuntimeException("Username not found: " + username);
        }
        this.id=id;
    }
    public void setName() {
        String query = "SELECT name FROM student WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.name = resultSet.getString("name");
                } else {
                    throw new RuntimeException("Student not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving student name", e);
        }
    }
    public void setCourses() {
        String query = "SELECT course.name FROM enrollment " +
                "JOIN course ON enrollment.course_id = course.id " +
                "WHERE enrollment.student_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                this.courses.clear(); // Clear previous courses if any
                while (resultSet.next()) {
                    this.courses.add(resultSet.getString("name"));
                }
                if (this.courses.isEmpty()) {
                    throw new RuntimeException("No courses found for student with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving student courses", e);
        }
    }
    public void setGrades() {
        String query = "SELECT course.name, enrollment.grade FROM enrollment " +
                "JOIN course ON enrollment.course_id = course.id " +
                "WHERE enrollment.student_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                this.grades.clear(); // Clear previous grades if any
                while (resultSet.next()) {
                    String courseName = resultSet.getString("name");
                    Double grade = resultSet.getDouble("grade");
                    this.grades.put(courseName, grade); // Store in the map
                }
                if (this.grades.isEmpty()) {
                    throw new RuntimeException("No grades found for student with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving student grades", e);
        }
    }

    public String getName() {
        return name;
    }
    public String getCoursesAndGrades() {
        StringBuilder sb = new StringBuilder();
        for (String course : courses) {
            Double grade = grades.get(course);
            sb.append(course).append(": ").append(grade != null ? grade : "Not Graded Yet").append("\n");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Username: " + username + ", Courses: " + courses.toString();
    }

}
