package com.gradingSystem.servlets.role.adminServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import static com.gradingSystem.servlets.backend.GetMethods.getCoursesDetails;


@WebServlet("/role/admin/menu")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/adminViews/menu.jsp").forward(request, response);

    }

}