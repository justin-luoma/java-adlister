package com.justinluoma.adlister.dao;

import com.justinluoma.adlister.dao.interfaces.AdCategories;
import com.justinluoma.adlister.models.AdCategory;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdCategoryDao implements AdCategories {
    private Connection connection;

    public MySQLAdCategoryDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<AdCategory> all() {
        String query = "SELECT * FROM ad_category";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            return createAdCategoryFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ad_categories", e);
        }
    }

    @Override
    public List<AdCategory> byCategoryID(Long categoryID) {
        String query = "SELECT * FROM ad_category WHERE category_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, categoryID);
            return createAdCategoryFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error getting ad_category by category_id", e);
        }
    }

    @Override
    public List<AdCategory> byAdID(Long adID) {
        String query = "SELECT * FROM ad_category WHERE ad_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adID);
            return createAdCategoryFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error getting ad_category by ad_id", e);
        }
    }

    @Override
    public Boolean insert(Long adID, Long categoryID) {
        String query = "INSERT INTO ad_category (ad_id, category_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, adID);
            stmt.setLong(2, categoryID);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting ad_category", e);
        }
    }

    private AdCategory extractAdCategory(ResultSet rs) throws SQLException {
        return new AdCategory(
                rs.getLong("ad_id"),
                rs.getLong("category_id")
        );
    }

    private List<AdCategory> createAdCategoryFromResults(ResultSet rs) throws SQLException {
        List<AdCategory> ac = new ArrayList<>();
        while (rs.next()) {
            ac.add(extractAdCategory(rs));
        }
        return ac;
    }
}
