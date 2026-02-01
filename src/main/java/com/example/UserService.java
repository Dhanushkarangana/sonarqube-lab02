package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    // Fetch the password from an environment variable named 'DB_PASSWORD'
    private final String password = System.getenv("DB_PASSWORD");

    public void findUser(String username) throws SQLException {
        if (password == null) {
            throw new IllegalStateException("Database password environment variable is not set.");
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
                PreparedStatement ps = conn.prepareStatement("SELECT name FROM users WHERE name = ?")) {
            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    public void deleteUser(String username) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE name = ?")) {
            ps.setString(1, username);
            ps.execute();
        }
    }
}