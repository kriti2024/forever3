package com.forever3.service;

import com.forever3.config.DbConfig;
import com.forever3.model.ItemModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    /**
     * Fetches a single item by its ID
     * Used when adding to cart or viewing item details
     */
    public ItemModel getItemById(int itemId) {
        String sql = "SELECT * FROM item WHERE item_id = ?";
        ItemModel item = null;

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = new ItemModel(
                            rs.getInt("item_id"),
                            rs.getString("item_name"),
                            rs.getString("description"),
                            rs.getInt("category_id"),
                            rs.getDouble("item_price"),
                            rs.getInt("stock_quantity"),
                            rs.getInt("supplier_id"),
                            rs.getString("image_url")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // You can log this or throw a custom exception
        }

        return item;
    }

    /**
     * Fetches all items that belong to the given category.
     * Example: skincare = 2, makeup = 1
     */
    public List<ItemModel> getItemsByCategory(int categoryId) {
        List<ItemModel> items = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE category_id = ?";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemModel item = new ItemModel(
                            rs.getInt("item_id"),
                            rs.getString("item_name"),
                            rs.getString("description"),
                            rs.getInt("category_id"),
                            rs.getDouble("item_price"),
                            rs.getInt("stock_quantity"),
                            rs.getInt("supplier_id"),
                            rs.getString("image_url")
                    );
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}
