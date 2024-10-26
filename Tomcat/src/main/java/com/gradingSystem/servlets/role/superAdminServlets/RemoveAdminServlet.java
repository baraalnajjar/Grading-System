package com.gradingSystem.servlets.role.superAdminServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import static com.gradingSystem.servlets.backend.DeleteMethods.removeAdmin;


@WebServlet("/role/superAdmin/menu/removeAdmin")
public class RemoveAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the remove admin JSP form
        request.getRequestDispatcher("/WEB-INF/views/superAdminViews/removeAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the admin ID from the form
        String adminId = request.getParameter("id");
        System.out.println(adminId);

        // Call the method to remove the admin by ID
        try {
            removeAdmin(adminId);
            // Set success message
            request.setAttribute("message", "Admin with ID " + adminId + " has been successfully removed.");
        } catch (Exception e) {
            // Set error message in case of failure
            request.setAttribute("message", "Failed to remove Admin with ID " + adminId + ": " + e.getMessage());
        }

        // Forward back to the JSP to display the message
        request.getRequestDispatcher("/WEB-INF/views/superAdminViews/removeAdmin.jsp").forward(request, response);
    }
}