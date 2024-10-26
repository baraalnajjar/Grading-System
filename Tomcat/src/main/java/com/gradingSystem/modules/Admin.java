package com.gradingSystem.modules;

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class Admin {
    private String name;
    private String id;
    private String username;
    private String password;
    private List<String> teachers;

    // Constructor
    public Admin(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.teachers = new ArrayList<String>();
    }

    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gradingSystem";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    public Admin(String username) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.username = username;
        this.teachers = new ArrayList<>();
        setID();
        setName();
        setPassword();
        setTeachers();
    }
    public void setID() {
        String id = null;  // Initialize id to null
        String query = "SELECT id FROM admin WHERE username = ?";

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

        if (id == null) {
            throw new RuntimeException("Username not found: " + username);
        }
        this.id = id;
    }
    public void setName() {
        String query = "SELECT name FROM admin WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.name = resultSet.getString("name");
                } else {
                    throw new RuntimeException("Admin not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving admin name", e);
        }
    }
    public void setPassword() {
        String query = "SELECT password FROM admin WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.password = resultSet.getString("password");
                } else {
                    throw new RuntimeException("Admin not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving admin password", e);
        }
    }
    public void setTeachers() {
        String query = "SELECT DISTINCT teacher.name FROM course " +
                "JOIN teacher ON course.teacher_id = teacher.id " +
                "JOIN enrollment ON course.id = enrollment.course_id " +
                "WHERE enrollment.student_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                this.teachers.clear(); // Clear previous teachers if any
                while (resultSet.next()) {
                    this.teachers.add(resultSet.getString("name"));
                }
                if (this.teachers.isEmpty()) {
                    throw new RuntimeException("No teachers found for student with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving teachers", e);
        }
    }
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public String getName() {
        return name;
    }
    public void addTeacher(String name, String username, String password) {
        // SQL query to insert a new teacher into the teacher table
        String query = "INSERT INTO teacher (name, username, password) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the parameters for the query
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Teacher added successfully!");
            } else {
                System.out.println("Failed to add the teacher.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding the teacher", e);
        }
    }
    public void removeTeacherById(String teacherId) {
        // SQL query to delete a teacher from the teacher table based on the teacher's ID
        String query = "DELETE FROM teacher WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the ID parameter for the query
            preparedStatement.setString(1, teacherId);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Teacher removed successfully!");
            } else {
                System.out.println("No teacher found with ID: " + teacherId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing the teacher", e);
        }
    }
    public String getTeachersAndCourses() {
        StringBuilder sb = new StringBuilder();

        // SQL query to get teacher ids, names, course ids, and course names
        String query = "SELECT t.id AS teacher_id, t.name AS teacher_name, c.id AS course_id, c.name AS course_name " +
                "FROM teacher t " +
                "JOIN course c ON t.id = c.teacher_id " +
                "ORDER BY t.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String teacherId = resultSet.getString("teacher_id");
                    String teacherName = resultSet.getString("teacher_name");
                    String courseId = resultSet.getString("course_id");
                    String courseName = resultSet.getString("course_name");

                    sb.append(". Teacher ID: ").append(teacherId)
                            .append(", Name: ").append(teacherName)
                            .append(", Course ID: ").append(courseId)
                            .append(", Course: ").append(courseName)
                            .append("\n");
                }

                // Handle case where no teachers or courses are found
                if (sb.length() == 0) {
                    sb.append("No teachers or courses found.");
                } else {
                    // Remove the trailing newline
                    sb.setLength(sb.length() - 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving teachers and courses.", e);
        }

        return sb.toString();
    }
    public void assignCourseToTeacher(String teacherId, String courseId, PrintWriter writer) {
        // SQL query to update the course with the teacher's ID
        String query = "UPDATE course SET teacher_id = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the teacher ID and course ID in the prepared statement
            preparedStatement.setString(1, teacherId);
            preparedStatement.setString(2, courseId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                writer.println("Course successfully assigned to teacher.");
            } else {
                writer.println("Failed to assign course. Ensure the course and teacher exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            writer.println("Error assigning course to teacher: " + e.getMessage());
        }
    }
    public String getStudentsAndIds() {
        StringBuilder sb = new StringBuilder();

        // SQL query to get student names and their IDs
        String query = "SELECT id, name FROM student";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String studentId = resultSet.getString("id");
                String studentName = resultSet.getString("name");
                sb.append(". ID: ").append(studentId)
                        .append(", Name: ").append(studentName).append("\n");
            }

            // Handle case where no students are found
            if (sb.length() == 0) {
                sb.append("No students found.");
            } else {
                // Remove the trailing newline if there are any students
                sb.setLength(sb.length() - 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving students and their IDs", e);
        }

        return sb.toString();
    }
    public void addStudent(String name, String username, String password) {
        String query = "INSERT INTO student (name, username, password) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Failed to add student.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding student", e);
        }
    }
    public void removeStudentById(String studentId) {
        String query = "DELETE FROM student WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("Failed to remove student. Student ID may not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing student", e);
        }
    }
    public void assignStudentToCourse(String studentId, String courseId) {
        String query = "INSERT INTO enrollment (student_id, course_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student assigned to course successfully.");
            } else {
                System.out.println("Failed to assign student to course. Check student ID and course ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error assigning student to course", e);
        }
    }
    public void addCourse(String courseName) {
        String addCourseQuery = "INSERT INTO course (name) VALUES (?)";
        try {
            // Add the course
            try (PreparedStatement addCourseStmt = connection.prepareStatement(addCourseQuery)) {
                addCourseStmt.setString(1, courseName);
                int rowsAffected = addCourseStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Course added and assigned to teacher successfully.");
                } else {
                    System.out.println("Failed to add course.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding course and assigning to teacher: " + e.getMessage());
        }
    }
    public void removeCourseById(String courseId) {
        String query = "DELETE FROM course WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, courseId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course removed successfully.");
            } else {
                System.out.println("Course not found with ID: " + courseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing course: " + e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "Admin Name: " + name + ", Username: " + username;
    }
}
