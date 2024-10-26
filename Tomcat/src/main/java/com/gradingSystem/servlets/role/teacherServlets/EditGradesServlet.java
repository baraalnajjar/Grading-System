package com.gradingSystem.servlets.role.teacherServlets;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.gradingSystem.servlets.backend.GetMethods.getStudentsAndGrades;
import static com.gradingSystem.servlets.backend.UpdateMethods.updateStudentGrade;


@WebServlet("/role/teacher/editGrades")
public class EditGradesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseID = request.getParameter("courseID");
        String username = request.getParameter("username");

        // Get the students and their grades for the given course
        List<String[]> studentsAndGrades = getStudentsAndGrades(courseID, username);

        request.setAttribute("students", studentsAndGrades.get(0));
        request.setAttribute("grades", studentsAndGrades.get(1));
        request.setAttribute("courseID", courseID);
        request.setAttribute("username", username); // Add username to request

        request.getRequestDispatcher("/WEB-INF/views/teacherViews/editGrades.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and courseId from the request
        String username = request.getParameter("username");
        String courseId = request.getParameter("courseId");

        System.out.println("Username: " + username);
        System.out.println("Course ID: " + courseId);

        int i = 0;

        while (true) {
            String paramName = "newGrade" + i;
            String gradeValue = request.getParameter(paramName);
            if (gradeValue == null) {
                break;
            }

            if (!gradeValue.isEmpty()) {
                try {
                    double grade = Double.parseDouble(gradeValue);
                    updateStudentGrade("1", courseId, grade); // Ensure you pass the correct student ID
                } catch (NumberFormatException e) {
                    System.out.println("  Error parsing grade: " + e.getMessage());
                }
            }
            i++;
        }

        // Send a success message back to the client
        response.setContentType("text/html");
        response.getWriter().println("<html><body><p class='success-message'>Grades updated successfully.</p></body></html>");
    }



}