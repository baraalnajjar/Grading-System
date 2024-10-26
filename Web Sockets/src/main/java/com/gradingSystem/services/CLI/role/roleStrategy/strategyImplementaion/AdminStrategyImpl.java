package com.gradingSystem.services.CLI.role.roleStrategy.strategyImplementaion;

import com.gradingSystem.modules.Admin;
import com.gradingSystem.services.CLI.role.roleStrategy.RoleStrategyInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminStrategyImpl implements RoleStrategyInt {
    @Override
    public void handleRole(PrintWriter out, BufferedReader in) {
        try {
            out.println("Enter your username:");
            String username = in.readLine();
            System.out.println("Received from client: " + username);
            Admin admin = new Admin(username);

            out.println("Enter your password:");
            String password = in.readLine();

            if (admin.verifyPassword(password)) {
                out.println("Welcome " + admin.getName() + "!");
                boolean continueMenu = true;

                while (continueMenu) {
                    out.println("\nOptions Menu:");
                    out.println("1. Manage Teachers");
                    out.println("2. Manage Students");
                    out.println("3. Manage Courses");
                    out.println("4. Exit");

                    String option = in.readLine();

                    switch (option) {
                        case "1" -> manageTeachers(admin, out, in);
                        case "2" -> manageStudents(admin, out, in);
                        case "3" -> manageCourses(admin, out, in);
                        case "4" -> {
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

    private void manageTeachers(Admin admin, PrintWriter out, BufferedReader in) throws IOException {
        boolean continueMenu = true;

        while (continueMenu) {
            // Display the menu options
            out.println("\nTeacher Management Menu:");
            out.println("1. Show Teachers and their Courses");
            out.println("2. Add a Teacher");
            out.println("3. Remove a Teacher");
            out.println("4. Assign Course to Teacher");
            out.println("5. Exit");

            // Read the user's choice
            String option = in.readLine();

            switch (option) {
                case "1" -> {
                    // Show Teachers and their Courses
                    out.println("Teachers and their courses:");
                    out.println(admin.getTeachersAndCourses());
                }
                case "2" -> {
                    // Add a Teacher
                    out.println("Enter teacher name:");
                    String name = in.readLine();
                    out.println("Enter teacher username:");
                    String username = in.readLine();
                    out.println("Enter teacher password:");
                    String password = in.readLine();
                    admin.addTeacher(name, username, password);
                    out.println("Teacher added successfully.");
                }
                case "3" -> {
                    // Remove a Teacher
                    out.println("Enter teacher ID to remove:");
                    String teacherID = in.readLine();
                    admin.removeTeacherById(teacherID);
                    out.println("Teacher removed successfully.");
                }
                case "4" -> {
                    // Assign Course to Teacher
                    out.println("Enter teacher ID:");
                    String teacherID = in.readLine();
                    out.println("Enter course ID:");
                    String courseID = in.readLine();
                    admin.assignCourseToTeacher(teacherID, courseID, out);
                    out.println("Course assigned successfully.");
                }
                case "5" -> {
                    // Exit the menu
                    out.println("Exiting Teacher Management Menu.");
                    continueMenu = false;
                }
                default -> out.println("Invalid option. Please try again.");
            }
        }
    }
    private void manageStudents(Admin admin, PrintWriter out, BufferedReader in) throws IOException {
        boolean continueMenu = true;
        while (continueMenu) {
            out.println("\nStudent Management Menu:");
            out.println("1. Show Students and Their IDs");
            out.println("2. Add Student");
            out.println("3. Remove Student");
            out.println("4. Assign Student to Course");
            out.println("5. Exit");

            String option = in.readLine();

            switch (option) {
                case "1" -> {
                    // Show students and their IDs
                    out.println(admin.getStudentsAndIds());
                }
                case "2" -> {
                    // Add a student
                    out.println("Enter student name:");
                    String name = in.readLine();
                    out.println("Enter student username:");
                    String username = in.readLine();
                    out.println("Enter student password:");
                    String password = in.readLine();
                    admin.addStudent(name, username, password);
                }
                case "3" -> {
                    // Remove a student
                    out.println("Enter student ID:");
                    String studentId = in.readLine();
                    admin.removeStudentById(studentId);
                }
                case "4" -> {
                    // Assign student to course
                    out.println("Enter student ID:");
                    String studentIdToAssign = in.readLine();
                    out.println("Enter course ID:");
                    String courseIdToAssign = in.readLine();
                    admin.assignStudentToCourse(studentIdToAssign, courseIdToAssign);
                }
                case "5" -> {
                    // Exit menu
                    out.println("Exiting the menu.");
                    continueMenu = false;
                }
                default -> out.println("Invalid option. Please try again.");
            }
        }
    }
    private void manageCourses(Admin admin, PrintWriter out, BufferedReader in) throws IOException {
        boolean continueMenu = true;

        while (continueMenu) {
            out.println("\nCourse Management Menu:");
            out.println("1. Show Courses and Their Teachers");
            out.println("2. Add Course");
            out.println("3. Remove Course");
            out.println("4. Assign Course to Teacher");
            out.println("5. Exit");

            String option = in.readLine();

            switch (option) {
                case "1" -> {
                    // Show courses and their teachers
                    out.println(admin.getTeachersAndCourses());
                }
                case "2" -> {
                    out.println("Enter teacher id:");
                    String teacherID = in.readLine();

                    out.println("Enter course name:");
                    String courseName = in.readLine();
                    admin.addCourse(courseName);
                    out.println("Course added successfully.");
                }
                case "3" -> {
                    // Remove a course
                    out.println("Enter course ID:");
                    String courseId = in.readLine();
                    admin.removeCourseById(courseId);
                    out.println("Course removed successfully.");
                }
                case "4" -> {
                    // Assign course to teacher
                    out.println("Enter teacher ID:");
                    String teacherId = in.readLine();
                    out.println("Enter course ID:");
                    String courseId = in.readLine();
                    admin.assignCourseToTeacher(teacherId, courseId, out);
                    out.println("Course assigned to teacher successfully.");
                }
                case "5" -> {
                    // Exit menu
                    out.println("Exiting the menu.");
                    continueMenu = false;
                }
                default -> out.println("Invalid option. Please try again.");
            }
        }
    }

}
