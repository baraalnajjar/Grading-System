package com.gradingSystem.servlets.backend;

import com.gradingSystem.modules.Student;
import com.gradingSystem.modules.SuperAdmin;
import com.gradingSystem.modules.Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.regex.*;

public class GetMethods {

    public static List<String[]> getStudentGrades(String username) {
        Student student = new Student(username);

        // Get the courses and grades string from the student object
        String coursesAndGrades = student.getCoursesAndGrades();

        // Initialize lists to hold courses and grades
        List<String> courses = new ArrayList<>();
        List<String> grades = new ArrayList<>();

        // Split the courses and grades string into individual lines
        String[] courseGradePairs = coursesAndGrades.split("\n");

        for (String pair : courseGradePairs) {
            // Split each pair by ": " to separate course names from grades
            String[] parts = pair.split(": ");
            if (parts.length == 2) {
                courses.add(parts[0].trim());
                grades.add(parts[1].trim());
            }
        }

        // Create a list to hold the courses and grades arrays
        List<String[]> result = new ArrayList<>();
        result.add(courses.toArray(new String[0]));
        result.add(grades.toArray(new String[0]));

        return result;
    }
    public static String getStudentName(String username) {
        Student student = new Student(username);

        return student.getName();
    }
    public static List<String[]> getAdminsAndIDs() {
        SuperAdmin superAdmin = new SuperAdmin();
        String adminsAndIDs = superAdmin.getAdminsAndIds();

        // Initialize lists to hold IDs, usernames, and names
        List<String> ids = new ArrayList<>();
        List<String> usernames = new ArrayList<>();
        List<String> names = new ArrayList<>();

        // Split the string into lines
        String[] lines = adminsAndIDs.split("\n");

        for (String line : lines) {
            // Check if the line contains admin data
            if (line.startsWith("Admin ID: ")) {
                // Extract admin ID and details from the line
                String[] parts = line.split(", ");
                if (parts.length >= 3) {
                    // Extract ID
                    String id = parts[0].substring("Admin ID: ".length());
                    ids.add(id);

                    // Extract username and name
                    String username = parts[1].substring("Username: ".length());
                    String name = parts[2].substring("Name: ".length());
                    usernames.add(username);
                    names.add(name);
                }
            } else if (line.equals("No admins found.")) {
                // Handle case where no admins are found
                ids.add("No admins found.");
                usernames.add("");
                names.add("");
            }
        }

        // Create a list to hold the IDs, usernames, and names arrays
        List<String[]> result = new ArrayList<>();
        result.add(ids.toArray(new String[0]));
        result.add(usernames.toArray(new String[0]));
        result.add(names.toArray(new String[0]));

        return result;
    }
    public static List<String[]> getCoursesDetails(String username) {
        Teacher teacher = new Teacher(username);
        String coursesData = teacher.getCourses();

        List<String[]> result = new ArrayList<>();
        List<String> idsList = new ArrayList<>();
        List<String> namesList = new ArrayList<>();
        List<String> averagesList = new ArrayList<>();
        List<String> mediansList = new ArrayList<>();
        List<String> highestList = new ArrayList<>();
        List<String> lowestList = new ArrayList<>();

        // Split coursesData into lines
        String[] lines = coursesData.split("\n");
        Pattern pattern = Pattern.compile("Course ID:\\s*(\\d+),\\s*Course Name:\\s*(.*?):\\s*Average:\\s*(.*?),\\s*Median:\\s*(.*?),\\s*Highest:\\s*(.*?),\\s*Lowest:\\s*(.*)");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                idsList.add(matcher.group(1).trim());
                namesList.add(matcher.group(2).trim());
                averagesList.add(matcher.group(3).trim());
                mediansList.add(matcher.group(4).trim());
                highestList.add(matcher.group(5).trim());
                lowestList.add(matcher.group(6).trim());
            } else {
                System.out.println("Unexpected data format: " + line);
            }
        }

        result.add(idsList.toArray(new String[0]));
        result.add(namesList.toArray(new String[0]));
        result.add(averagesList.toArray(new String[0]));
        result.add(mediansList.toArray(new String[0]));
        result.add(highestList.toArray(new String[0]));
        result.add(lowestList.toArray(new String[0]));

        return result;
    }
    public static List<String[]> getStudentsAndGrades(String courseID, String username) {
        Teacher teacher = new Teacher("teacheruser");
        String studentsData = teacher.getStudentsByCourse("1");

        List<String[]> result = new ArrayList<>();

        // Split studentsData into lines
        String[] lines = studentsData.split("\n");

        // Initialize lists to hold student names and grades
        List<String> namesList = new ArrayList<>();
        List<String> gradesList = new ArrayList<>();

        for (String line : lines) {
            // Skip the first line if it contains "No students found"
            if (line.startsWith("No students found")) {
                continue;
            }

            // Extract student name and grade
            String[] parts = line.split(": Grade: ");
            if (parts.length == 2) {
                namesList.add(parts[0].substring(parts[0].indexOf('.') + 2).trim());
                gradesList.add(parts[1].trim());
            }
        }

        // Convert lists to arrays
        String[] studentNames = namesList.toArray(new String[0]);
        String[] grades = gradesList.toArray(new String[0]);

        // Add arrays to the result list
        result.add(studentNames);
        result.add(grades);

        return result;
    }



}
