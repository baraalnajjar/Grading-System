package com.gradingSystem.servlets.role.superAdminServlets;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/role/superAdmin/menu")
public class SuperMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "addAdmin":
                    response.sendRedirect(request.getContextPath() + "/role/superAdmin/menu/addAdmin");
                    break;
                case "removeAdmin":
                    response.sendRedirect(request.getContextPath() + "/role/superAdmin/menu/removeAdmin");
                    break;
                case "viewAdmins":
                    response.sendRedirect(request.getContextPath() + "/role/superAdmin/menu/viewAdmins");
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid action");
                    break;
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/views/superAdminViews/superAdminOptions.jsp").forward(request, response);
        }
    }
}
