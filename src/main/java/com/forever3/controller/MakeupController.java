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

@WebServlet(asyncSupported = true, urlPatterns = { "/makeup" })
public class MakeupController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MakeupController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        ItemService itemService = new ItemService();

        // Assuming 2 is the category_id for makeup
        int makeupCategoryId = 1;

        List<ItemModel> makeupItems = itemService.getItemsByCategory(makeupCategoryId);

        request.setAttribute("items", makeupItems);

        request.getRequestDispatcher("/WEB-INF/pages/makeup.jsp").forward(request, response);
    }
}
