package com.gradingSystem.servlets.role.teacherServlets;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import static com.gradingSystem.servlets.backend.GetMethods.getCoursesDetails;


@WebServlet("/role/teacher/courses")
public class TeacherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> courses = getCoursesDetails(request.getParameter("username"));
        String username = request.getParameter("username");

        request.setAttribute("courses", courses);
        request.setAttribute("username", username);
        request.getRequestDispatcher("/WEB-INF/views/teacherViews/teacherCourses.jsp").forward(request, response);
    }

}
