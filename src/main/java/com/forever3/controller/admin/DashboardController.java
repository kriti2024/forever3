package com.forever3.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.forever3.util.SessionUtil;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboardController" })
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user has the admin role in session or cookie
        String role = (String) SessionUtil.getAttribute(request, "role");

        if (role != null && role.equals("admin")) {
            // If the user is an admin, forward them to the dashboard page
            request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } else {
            // If not an admin, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}

 