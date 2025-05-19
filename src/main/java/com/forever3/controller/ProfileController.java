package com.forever3.controller;

import java.io.IOException;

import com.forever3.model.UserModel;
import com.forever3.service.ProfileService;
import com.forever3.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ProfileController handles requests related to viewing the user's profile.
 */
@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ProfileService profileService;

    public ProfileController() {
        this.profileService = new ProfileService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) SessionUtil.getAttribute(request, "username");

        if (username == null) {
            request.setAttribute("error", "Please log in to access your profile.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }

        UserModel user = profileService.getUserProfile(username);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "User not found. Please log in again.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}