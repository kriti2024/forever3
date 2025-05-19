package com.forever3.controller;

import com.forever3.model.CartItemModel;
import com.forever3.model.ItemModel;
import com.forever3.service.ItemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToCartController")
public class AddToCartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ItemService itemService = new ItemService();
        ItemModel item = itemService.getItemById(itemId);

        HttpSession session = request.getSession();
        List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean itemExists = false;
        for (CartItemModel cartItem : cart) {
            if (cartItem.getItem().getItemId() == itemId) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cart.add(new CartItemModel(item, quantity));
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cartlist"); // ✅ Correct redirect to servlet mapped for cart page
    }
}
