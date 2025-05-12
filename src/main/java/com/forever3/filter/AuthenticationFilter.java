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
    private static final String DASHBOARD = "/dashboard";
    private static final String SKINCARE = "/skincare";
    private static final String MAKEUP = "/makeup";
    private static final String ABOUTUS = "/aboutus";
    private static final String CONTACTUS = "/contactus";
    private static final String CART = "/cart";
    private static final String PROFILE = "/profile";
    private static final String LOGOUT = "/logout";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional initialization
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Allow static resources (images, css, js files)
        if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css") || uri.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
        String userRole = CookieUtil.getCookie(req, "role") != null
                ? CookieUtil.getCookie(req, "role").getValue()
                : null;

        // Check if user is logged in and has a valid role
        if (isLoggedIn && userRole != null) {
            if ("admin".equals(userRole)) {
                // Admin-specific redirects
                if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                    res.sendRedirect(req.getContextPath() + DASHBOARD);
                } else if (uri.startsWith(req.getContextPath() + DASHBOARD)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect(req.getContextPath() + DASHBOARD);
                }
            } else if ("customer".equals(userRole)) {
                // Customer-specific redirects
                if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                    res.sendRedirect(req.getContextPath() + HOME);
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            // User is not logged in, allow access to login, register, and public pages
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) ||
                uri.endsWith(HOME) || uri.endsWith(ROOT) || 
                uri.endsWith(SKINCARE) || uri.endsWith(MAKEUP) ||
                uri.endsWith(ABOUTUS) || uri.endsWith(CONTACTUS)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + LOGIN);  // Redirect to login if not logged in
            }
        }
    }



    @Override
    public void destroy() {
        // Optional cleanup
    }
}
