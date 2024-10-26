package com.gradingSystem.servlets.role.superAdminServlets;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.gradingSystem.servlets.backend.PostMethods.addAdmin;


@WebServlet("/role/superAdmin/menu/addAdmin")
public class AddAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/superAdminViews/addAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (name != null && !name.isEmpty() && username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            addAdmin(name, username, password);
            // Set success message
            request.setAttribute("successMessage", "Admin added successfully!");
        } else {
            // Set error message
            request.setAttribute("errorMessage", "All fields are required.");
        }

        // Forward back to the same JSP page to display the message
        request.getRequestDispatcher("/WEB-INF/views/superAdminViews/addAdmin.jsp").forward(request, response);
    }

}