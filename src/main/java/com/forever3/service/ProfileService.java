package com.forever3.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.forever3.config.DbConfig;
import com.forever3.model.UserModel;

public class ProfileService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public ProfileService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public UserModel getUserProfile(String username) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT id, first_name, last_name, user_name, email, number, image_url, role FROM customer WHERE user_name = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                UserModel user = new UserModel();
                user.setId(result.getInt("id"));
                user.setFirstName(result.getString("first_name"));
                user.setLastName(result.getString("last_name"));
                user.setUserName(result.getString("user_name"));
                user.setEmail(result.getString("email"));
                user.setNumber(result.getString("number"));
                user.setImageUrl(result.getString("image_url"));
                user.setRole(result.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateUserProfile(String oldUsername, UserModel user) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return false;
        }

        // Start building SQL and parameters dynamically depending on password presence
        StringBuilder sql = new StringBuilder("UPDATE customer SET user_name = ?, first_name = ?, last_name = ?, email = ?, number = ?, image_url = ?");
        boolean updatePassword = user.getPassword() != null && !user.getPassword().trim().isEmpty();

        if (updatePassword) {
            sql.append(", password = ?");
        }

        sql.append(" WHERE user_name = ?");

        try (PreparedStatement ps = dbConn.prepareStatement(sql.toString())) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNumber());
            ps.setString(6, user.getImageUrl());

            int paramIndex = 7;

            if (updatePassword) {
                ps.setString(paramIndex++, user.getPassword());
            }

            ps.setString(paramIndex, oldUsername);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}