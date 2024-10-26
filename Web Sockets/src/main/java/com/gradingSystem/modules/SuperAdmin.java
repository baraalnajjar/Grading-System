package com.gradingSystem.modules;

import java.sql.*;
import java.util.*;

public class SuperAdmin {
    private String name;
    private String id;
    private String username;
    private String password;

    // Constructor
    public SuperAdmin(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gradingSystem";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    public SuperAdmin(String username) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.username = username;
        setID();
        setName();
        setPassword();

    }
    // Getter for name
    public String getName() {
        return name;
    }
    public void setID()  {
        String id;
        String query = "SELECT id FROM super_admin WHERE username = ?";

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
        String query = "SELECT name FROM super_admin WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.name = resultSet.getString("name");
                } else {
                    throw new RuntimeException("SuperAdmin not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving superadmin name", e);
        }
    }
    public void setPassword() {
        String query = "SELECT password FROM super_admin WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.password = resultSet.getString("password");
                } else {
                    throw new RuntimeException("SuperAdmin not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving superadmin password", e);
        }
    }
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
    public String getAdminsAndIds() {
        StringBuilder sb = new StringBuilder();

        // SQL query to get admin IDs and names
        String query = "SELECT id, username, name FROM admin ORDER BY id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");

                sb.append("Admin ID: ").append(id)
                        .append(", Username: ").append(username)
                        .append(", Name: ").append(name)
                        .append("\n");
            }

            // Handle case where no admins are found
            if (sb.length() == 0) {
                sb.append("No admins found.");
            } else {
                // Remove the trailing newline
                sb.setLength(sb.length() - 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving admins and their IDs.", e);
        }

        return sb.toString();
    }
    public void addAdmin(String name, String username, String password) {
        String addAdminQuery = "INSERT INTO admin (name, username, password) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(addAdminQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin added successfully.");
            } else {
                System.out.println("Failed to add admin.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding admin", e);
        }
    }
    public void removeAdminById(String adminId) {
        String removeAdminQuery = "DELETE FROM admin WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(removeAdminQuery)) {
            preparedStatement.setString(1, adminId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin removed successfully.");
            } else {
                System.out.println("No admin found with ID: " + adminId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing admin", e);
        }
    }


    // toString method to print the super admin's info
    @Override
    public String toString() {
        return "SuperAdmin Name: " + name + ", Username: " + username;
    }
}
