package com.gradingSystem.services.CLI.role.roleStrategy.strategyImplementaion;

import com.gradingSystem.modules.Teacher;
import com.gradingSystem.services.CLI.role.roleStrategy.RoleStrategyInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherStrategyImpl implements RoleStrategyInt {
    @Override
    public void handleRole(PrintWriter out, BufferedReader in) {
        try {
            out.println("Enter your username:");
            String username = in.readLine();
            System.out.println("Received from client: " + username);
            Teacher teacher = new Teacher(username);

            out.println("Enter your password:");
            String password = in.readLine();

            if (teacher.verifyPassword(password)) {
                out.println("Welcome " + teacher.getName() + "!");
                boolean continueMenu = true;

                while (continueMenu) {
                    out.println("\nOptions Menu:");
                    out.println("1. View Courses");
                    out.println("2. Manage Student Grades");
                    out.println("3. Exit");

                    String option = in.readLine();

                    switch (option) {
                        case "1" -> viewCourses(teacher, out);
                        case "2" -> manageStudentGrades(teacher, out, in);
                        case "3" -> {
                            out.println("Exiting the menu.");
                            continueMenu = false;
                        }
                        default -> out.println("Invalid option. Please try again.");
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

    private void viewCourses(Teacher teacher, PrintWriter out) {
        out.println("Your Courses are: ");
        out.println(teacher.getCourses());
    }

    private void manageStudentGrades(Teacher teacher, PrintWriter out, BufferedReader in) throws IOException {
        out.println("Enter a course id to manage students' grades:");
        String course = in.readLine();
        System.out.println("Received from client: " + course);

        out.println(teacher.getStudentsByCourse(course));

        out.println("Enter a student id if you want to edit the grade:");
        String student = in.readLine();

        out.println("Enter the new grade:");
        double grade;
        try {
            grade = Double.parseDouble(in.readLine());
        } catch (NumberFormatException e) {
            out.println("Invalid grade format.");
            return; // Exit the method to prevent further processing
        }

        teacher.updateStudentGrade(student, course, grade, out);
    }
}
