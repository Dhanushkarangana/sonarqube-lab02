package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    // The password is fetched once when the class is instantiated
    private final String dbPassword = System.getenv("DB_PASSWORD");
    private static final String DB_URL = "jdbc:mysql://localhost/db";
    private static final String DB_USER = "root";

    /**
     * Finds a user by their username.
     * 
     * @param username the name to search for
     * @throws SQLException if a database access error occurs
     */
    public void findUser(String username) throws SQLException {
        validateConfiguration();
        String sql = "SELECT name FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, dbPassword);
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeQuery();
            LOGGER.log(Level.INFO, "User search executed for: {0}", username);
        }
    }

    /**
     * Deletes a user by their username.
     * 
     * @param username the name to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteUser(String username) throws SQLException {
        validateConfiguration();
        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, dbPassword);
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.execute();
            LOGGER.log(Level.INFO, "User deletion executed for: {0}", username);
        }
    }

    /**
     * Validates that the environment variable is present before attempting
     * connection.
     */
    private void validateConfiguration() {
        if (dbPassword == null || dbPassword.trim().isEmpty()) {
            throw new IllegalStateException("Security Error: Environment variable 'DB_PASSWORD' is not set.");
        }
    }
}