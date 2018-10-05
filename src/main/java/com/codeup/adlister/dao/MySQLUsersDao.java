package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection = null;

    MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!");
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ?"
            );
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user from database");
        }
        return null;
    }

    @Override
    public Long insert(User user) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                    "INSERT INTO users (username, email, password) " +
                            "VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate user or email
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user into database!");
        }
        return null;
    }

    @Override
    public User getUserByID(Long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?"
            );
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return createUserFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user by id");
        }
        return null;
    }

    private User createUserFromResults(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")

        );
    }
}
