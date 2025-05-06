package com.forever3.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.forever3.util.CookieUtil;
import com.forever3.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String SKINCARE = "/skincare.jsp";
    private static final String MAKEUP = "/makeup.jsp";
    private static final String ABOUT_US = "/aboutus.jsp";
    private static final String CONTACT_US = "/contactus.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if required
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        System.out.println("Requested URI: " + uri);

        // ✅ Allow static resources (images, CSS, JS, etc.)
        if (path.startsWith("/resources/")) {
            chain.doFilter(request, response);
            return;
        }

        // ✅ Check if user is logged in
        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
        System.out.println("User Logged In: " + isLoggedIn);

        // ✅ Get user role from cookie
        String userRole = CookieUtil.getCookie(req, "role") != null ? CookieUtil.getCookie(req, "role").getValue() : null;
        System.out.println("User Role: " + userRole);

        // 🔒 Admin role
        if ("admin".equals(userRole)) {
            if (path.equals(LOGIN) || path.equals(REGISTER)) {
                res.sendRedirect(contextPath + "/dashboard");
            } else if (path.equals(ROOT) || path.equals(HOME)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(contextPath + "/dashboard");
            }
        }
        // 👤 Customer role
        else if ("customer".equals(userRole)) {
            if (path.equals(LOGIN) || path.equals(REGISTER)) {
                res.sendRedirect(contextPath + HOME);
            } else if (path.equals(SKINCARE) || path.equals(MAKEUP) || path.equals(ABOUT_US) || path.equals(CONTACT_US)
                    || path.equals(HOME) || path.equals(ROOT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(contextPath + HOME);
            }
        }
        // 🚫 Not logged in
        else {
            if (path.equals(LOGIN) || path.equals(REGISTER) || path.equals(HOME) || path.equals(ROOT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(contextPath + LOGIN);
            }
        }
    }

    @Override
    public void destroy() {
        // Cleanup logic, if required
    }
}
