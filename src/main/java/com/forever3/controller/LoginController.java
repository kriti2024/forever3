package com.forever3.controller;
 
import java.io.IOException;
 
import com.forever3.model.UserModel;
import com.forever3.service.LoginService;
import com.forever3.util.CookieUtil;
import com.forever3.util.SessionUtil;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
/**
 * LoginController is responsible for handling login requests. It interacts with
 * the LoginService to authenticate users.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private final LoginService loginService;
 
    /**
     * Constructor initializes the LoginService.
     */
    public LoginController() {
        this.loginService = new LoginService();
    }
 
    /**
     * Handles GET requests to the login page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
 
    /**
     * Handles POST requests for user login.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Admin login hardcoded check
        if ("admin".equals(username) && "@Admin1234".equals(password)) {
        	System.out.println("Admin login successful");
            SessionUtil.setAttribute(req, "username", username);
            CookieUtil.addCookie(resp, "role", "admin", 3 * 50); // Role: admin
            req.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(req,resp);
            return; // Exit method after admin login
        }

        // For other users (students/customers)
        UserModel userModel = new UserModel(username, password);
        Boolean loginStatus = loginService.loginUser(userModel);
        System.out.println("Login Status: " + loginStatus);

        if (loginStatus != null && loginStatus) {
            SessionUtil.setAttribute(req, "username", username);
            CookieUtil.addCookie(resp, "role", "customer", 3 * 50); // Role: customer
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            handleLoginFailure(req, resp, loginStatus);
        }
    }

 
    /**
     * Handles login failures by setting attributes and forwarding to the login
     * page.
     *
     * @param req         HttpServletRequest object
     * @param resp        HttpServletResponse object
     * @param loginStatus Boolean indicating the login status
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "User credential mismatch. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
 
}