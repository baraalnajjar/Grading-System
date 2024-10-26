package com.gradingSystem.servlets.role.superAdminServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import static com.gradingSystem.servlets.backend.GetMethods.getAdminsAndIDs;


@WebServlet("/role/superAdmin/menu/viewAdmins")
public class ViewAdminsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> adminsAndIds = getAdminsAndIDs();

        String[] adminIDs = adminsAndIds.get(0);
        String[] adminUsernames = adminsAndIds.get(1);
        String[] adminNames = adminsAndIds.get(2);

        request.setAttribute("adminIDs", adminIDs);
        request.setAttribute("adminUsernames", adminUsernames);
        request.setAttribute("adminNames", adminNames);

        // Forward to the JSP page
        request.getRequestDispatcher("/WEB-INF/views/superAdminViews/viewAdmins.jsp").forward(request, response);
    }

}