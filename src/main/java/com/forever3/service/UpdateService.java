package com.forever3.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.forever3.config.DbConfig;
import com.forever3.model.UserModel;

public class UpdateService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public UpdateService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Updates user profile in the database, allowing username change.
     *
     * @param user The UserModel object containing updated data.
     * @param oldUsername The old username to identify the correct record.
     * @return true if update successful, false if no rows affected, null if error.
     */
    public Boolean updateCustomerProfile(UserModel user, String oldUsername) {
        if (isConnectionError) {
            return null;
        }

        // Base update query including image_url (mandatory)
        StringBuilder updateSQL = new StringBuilder(
            "UPDATE customer SET user_name = ?, first_name = ?, last_name = ?, email = ?, number = ?, image_url = ?"
        );

        // Check if password should be updated
        boolean updatePassword = user.getPassword() != null && !user.getPassword().trim().isEmpty();
        if (updatePassword) {
            updateSQL.append(", password = ?");
        }

        updateSQL.append(" WHERE user_name = ?");

        try (PreparedStatement ps = dbConn.prepareStatement(updateSQL.toString())) {
            ps.setString(1, user.getUserName());    // new username
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNumber());
            ps.setString(6, user.getImageUrl());    // image URL mandatory

            int paramIndex = 7;
            if (updatePassword) {
                ps.setString(paramIndex++, user.getPassword()); // encrypted password
            }

            ps.setString(paramIndex, oldUsername);   // old username for WHERE clause

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}