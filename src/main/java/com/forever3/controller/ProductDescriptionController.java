package com.forever3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/product" })
public class ProductDescriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("id");

		// Optional: Validate the ID
		if (productId == null || productId.isEmpty()) {
			response.sendRedirect("makeup"); // Or an error page
			return;
		}

		// Set productId as a request attribute (or fetch product object from DB)
		request.setAttribute("item_id", productId);

		// Forward to a generic product description page
		request.getRequestDispatcher("/WEB-INF/pages/fentyBeautyGlossBomb.jsp").forward(request, response);
	}
}
