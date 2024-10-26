package com.gradingSystem.servlets.role.studentServlets;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.gradingSystem.servlets.backend.GetMethods.getStudentGrades;
import static com.gradingSystem.servlets.backend.GetMethods.getStudentName;


@WebServlet("/role/student/viewGrades")
public class GradesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
       List<String[]> CoursesAndGrades = getStudentGrades(username);



       String[] courses = CoursesAndGrades.get(0);
        String[] grades = CoursesAndGrades.get(1);

        request.setAttribute("courses", courses);
        request.setAttribute("grades", grades);
        request.setAttribute("username", getStudentName(username));

        request.getRequestDispatcher("/WEB-INF/views/studentViews/viewGrades.jsp").forward(request, response);

    }
}
