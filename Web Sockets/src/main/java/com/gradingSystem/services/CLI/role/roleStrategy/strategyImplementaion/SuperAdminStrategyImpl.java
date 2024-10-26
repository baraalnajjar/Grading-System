package com.gradingSystem.services.CLI.role.roleStrategy.strategyImplementaion;

import com.gradingSystem.modules.SuperAdmin;
import com.gradingSystem.services.CLI.role.roleStrategy.RoleStrategyInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SuperAdminStrategyImpl implements RoleStrategyInt {

    @Override
    public void handleRole(PrintWriter out, BufferedReader in) {
        try {
            out.println("Enter your username:");
            String username = in.readLine();
            System.out.println("Received from client: " + username);
            SuperAdmin superAdmin = new SuperAdmin(username);

            out.println("Enter your password:");
            String password = in.readLine();


            if (superAdmin.verifyPassword(password)) {
                out.println("Welcome " + superAdmin.getName());

                boolean continueMenu = true;
                while (continueMenu) {
                    out.println("\nOptions Menu:");
                    out.println("1. Show Admins and their IDs");
                    out.println("2. Add Admin");
                    out.println("3. Remove Admin");
                    out.println("4. Exit");

                    String option = in.readLine();

                    switch (option) {
                        case "1" -> showAdmins(superAdmin, out);
                        case "2" -> addAdmin(superAdmin, out, in);
                        case "3" -> removeAdmin(superAdmin, out, in);
                        case "4" -> {
                            out.println("Exiting...");
                            continueMenu = false;
                        }
                        default -> out.println("Invalid choice. Please choose a valid option.");
                    }
                }
            } else {
                out.println("Invalid username or password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            out.println("An error occurred while processing your input. Please try again.");
        }
    }

    private void showAdmins(SuperAdmin superAdmin, PrintWriter out) {
        out.println("Showing admins and their IDs:");
        out.println(superAdmin.getAdminsAndIds());
    }

    private void addAdmin(SuperAdmin superAdmin, PrintWriter out, BufferedReader in) throws IOException {
        out.println("Add Admin:");
        out.println("Enter admin name:");
        String adminName = in.readLine();
        out.println("Enter admin username:");
        String adminUsername = in.readLine();
        out.println("Enter admin password:");
        String adminPassword = in.readLine();
        superAdmin.addAdmin(adminName, adminUsername, adminPassword);
        out.println("Admin added successfully.");
    }

    private void removeAdmin(SuperAdmin superAdmin, PrintWriter out, BufferedReader in) throws IOException {
        out.println("Remove Admin:");
        out.println("Enter admin ID:");
        String adminID = in.readLine();
        superAdmin.removeAdminById(adminID);
        out.println("Admin removed successfully.");
    }
}
