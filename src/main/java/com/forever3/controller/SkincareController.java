package com.forever3.controller;

import com.forever3.model.ItemModel;
import com.forever3.service.ItemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/skincare" })
public class SkincareController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SkincareController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        ItemService itemService = new ItemService();

        // Assuming 1 is the category_id for skincare
        int skincareCategoryId = 2;

        List<ItemModel> skincareItems = itemService.getItemsByCategory(skincareCategoryId);

        request.setAttribute("items", skincareItems);

        request.getRequestDispatcher("/WEB-INF/pages/skincare.jsp").forward(request, response);
    }
}
