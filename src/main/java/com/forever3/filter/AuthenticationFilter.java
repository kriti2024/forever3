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
    private static final String ORDERLIST = "/orderlist";   // fixed typo here
    private static final String CARTLIST = "/cartlist";     // new constant added
    private static final String PROFILE = "/profile";
    private static final String LOGOUT = "/logout";

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
		
		// Allow access to resources
		if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css")) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		String userRole = CookieUtil.getCookie(req, "role") != null ? CookieUtil.getCookie(req, "role").getValue()
				: null;

		if ("admin".equals(userRole)) {
			// Admin is logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + DASHBOARD);
			} else if (uri.endsWith(DASHBOARD) || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
				chain.doFilter(request, response);
			} else if (uri.endsWith(ORDERLIST) || uri.endsWith(CARTLIST)) {
				res.sendRedirect(req.getContextPath() + DASHBOARD);
			} else {
				res.sendRedirect(req.getContextPath() + DASHBOARD);
			}
		} else if ("customer".equals(userRole)) {
			// User is logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + HOME);
			} else if (uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUTUS) || uri.endsWith(PROFILE)
					|| uri.endsWith(CONTACTUS) || uri.endsWith(ORDERLIST) || uri.endsWith(CARTLIST)|| uri.endsWith(MAKEUP)
					|| uri.endsWith(SKINCARE) || uri.endsWith(LOGOUT)) {
				chain.doFilter(request, response);
			} else if (uri.endsWith(DASHBOARD)) {
				res.sendRedirect(req.getContextPath() + HOME);
			} else {
				res.sendRedirect(req.getContextPath() + HOME);
			}
		} else {
			// Not logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.endsWith(MAKEUP) || uri.endsWith(SKINCARE)
					|| uri.endsWith(ABOUTUS) || uri.endsWith(CONTACTUS)  || uri.endsWith(ROOT) ) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + LOGIN);
			}
		}
	}

	@Override
	public void destroy() {
		// Cleanup logic, if required
	}
}