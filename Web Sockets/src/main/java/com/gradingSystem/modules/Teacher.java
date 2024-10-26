package com.gradingSystem.modules;

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class Teacher {
    private String name;
    private String id;
    private String username;
    private String password;
    private List<String> courses;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gradingSystem";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    public Teacher(String username) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.username = username;
        this.courses = new ArrayList<>();
        setID();
        setName();
        setPassword();
        setCourses();
    }

    public void setID() {
        String id = null;
        String query = "SELECT id FROM teacher WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getString("id");
                } else {
                    throw new RuntimeException("Teacher not found with ID: " + id);
                }
            }
            } catch (SQLException e) {
                throw new RuntimeException("Error executing query", e);
            }

        if (id == null) {
            throw new RuntimeException("Username not found: " + username);
        }
        this.id = id;
    }
    public void setName() {
        String query = "SELECT name FROM teacher WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.name = resultSet.getString("name");
                } else {
                    throw new RuntimeException("Teacher not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving teacher name", e);
        }
    }
    public void setPassword() {
        String query = "SELECT password FROM teacher WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.password = resultSet.getString("password");
                } else {
                    throw new RuntimeException("Teacher not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving teacher password", e);
        }
    }
    public void setCourses() {
        String query = "SELECT name FROM course WHERE teacher_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                this.courses.clear(); // Clear previous courses if any
                while (resultSet.next()) {
                    this.courses.add(resultSet.getString("name"));
                }
                if (this.courses.isEmpty()) {
                    throw new RuntimeException("No courses found for teacher with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving teacher courses", e);
        }
    }

    public String getName() {
        return name;
    }
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
    public String getCourses() {
        StringBuilder sb = new StringBuilder();
        String query = "SELECT name, average, median, highest, lowest FROM course WHERE teacher_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String courseName = resultSet.getString("name");
                    String average = resultSet.getString("average");
                    String median = resultSet.getString("median");
                    String highest = resultSet.getString("highest");
                    String lowest = resultSet.getString("lowest");

                    sb.append(courseName).append(": ")
                            .append("Average: ").append(average).append(", ")
                            .append("Median: ").append(median).append(", ")
                            .append("Highest: ").append(highest).append(", ")
                            .append("Lowest: ").append(lowest).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving course details", e);
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
    public String getStudentsByCourse(String courseId) {
        StringBuilder sb = new StringBuilder();
        int index = 1;

        // SQL query to get student names and their grades for a specific course
        String query = "SELECT s.name, e.grade " +
                "FROM student s " +
                "JOIN enrollment e ON s.id = e.student_id " +
                "JOIN course c ON e.course_id = c.id " +
                "WHERE e.course_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, courseId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String studentName = resultSet.getString("name");
                    String grade = resultSet.getString("grade");
                    sb.append(index++).append(". ").append(studentName)
                            .append(": Grade: ").append(grade).append("\n");
                }

                // Handle case where no students are found
                if (sb.length() == 0) {
                    sb.append("No students found for the course with ID: ").append(courseId);
                } else {
                    // Remove the trailing newline if there are any students
                    sb.setLength(sb.length() - 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving students and their grades for course ID: " + courseId, e);
        }

        return sb.toString();
    }
    public void updateStudentGrade(String studentId, String courseId, double newGrade, PrintWriter writer) {
        try {
            // Update the grade in the database
            String updateGradeQuery = "UPDATE enrollment SET grade = ? WHERE student_id = ? AND course_id = ?";
            PreparedStatement updateGradeStmt = connection.prepareStatement(updateGradeQuery);
            updateGradeStmt.setDouble(1, newGrade);
            updateGradeStmt.setString(2, studentId);
            updateGradeStmt.setString(3, courseId);

            int rowsAffected = updateGradeStmt.executeUpdate();
            if (rowsAffected > 0) {
                writer.println("Grade updated successfully.");
            } else {
                writer.println("Failed to update grade. Ensure the student is enrolled in the course.");
            }
        } catch (SQLException e) {
            writer.println("Error updating grade: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Username: " + username + ", Courses: " + courses.toString();
    }
}
