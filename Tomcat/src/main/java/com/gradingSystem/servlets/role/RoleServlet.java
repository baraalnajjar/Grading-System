package com.gradingSystem.servlets.role;


import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authToken".equals(cookie.getName())) {
                    authToken = cookie.getValue();
                    break;
                }
            }
        }

        boolean isAuthenticated = (authToken != null );

        if (isAuthenticated) {
            String role = request.getParameter("role");
            String username = request.getParameter("username");

            if (role != null && !role.isEmpty() &&username!=null && !username.isEmpty()) {

                // Redirect based on role
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new ServletException("MySQL JDBC Driver not found", e);
                }

                redirectToRolePage(role, username, request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Role is required");
            }

            expireToken(response);
        } else {
            String role = request.getParameter("role");
            String fullRedirectURL = request.getRequestURL().toString()+ "?role=" + role;
            String loginURL = "http://localhost:8081/authservice/login?redirectURL=" + fullRedirectURL;
            response.sendRedirect(loginURL);
        }
    }

    private void redirectToRolePage(String role, String username, HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (role) {
            case "Student":
                response.sendRedirect(request.getContextPath() + "/role/student/viewGrades?username=" + username);
                break;
            case "Teacher":
                response.sendRedirect(request.getContextPath() + "/role/teacher/courses?username=" + username);
                break;
            case "Admin":
                response.sendRedirect(request.getContextPath() + "/role/admin/menu?username=" + username);
                break;
            case "SuperAdmin":
                response.sendRedirect(request.getContextPath() + "/role/superAdmin/menu/viewSuperAdminOptions?username=" + username);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid role");
                break;
        }
    }

    private void expireToken(HttpServletResponse response) {
        Cookie authCookie = new Cookie("authToken", null);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(0);
        response.addCookie(authCookie);
    }
}