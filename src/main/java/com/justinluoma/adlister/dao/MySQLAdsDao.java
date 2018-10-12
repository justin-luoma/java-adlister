package com.justinluoma.adlister.dao;

import com.justinluoma.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ads ORDER BY created DESC");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(created_by, title, description, created) VALUES (?, ?, ?, (SELECT NOW()))";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.createdBy());
            stmt.setString(2, ad.title());
            stmt.setString(3, ad.description());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public List<Ad> users(Long user_id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ads WHERE created_by = ?");
            stmt.setLong(1, user_id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Ad getFromID(Long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ?");
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next())
            return extractAd(rs);
        else
            throw new SQLException("id not found");
    }

    @Override
    public List<Ad> search(String search, int type) {
        String query;
        switch (type) {
            case 1:
                query = "select *, MATCH (title) against (? IN NATURAL LANGUAGE MODE) as rel from ads WHERE MATCH (title) against (? IN NATURAL LANGUAGE MODE) > 0 order by rel desc limit 10";
                break;
            case 2:
                query = "select *, MATCH (description) against (? IN NATURAL LANGUAGE MODE) as rel from ads WHERE MATCH (description) against (? IN NATURAL LANGUAGE MODE) > 0 order by rel desc limit 10";
                break;
            default:
                query = "select *, MATCH (title) against " +
                        "(? IN NATURAL LANGUAGE MODE) score," +
                        "          MATCH (title) against (? IN BOOLEAN MODE) " +
                        "rel from ads " +
                        "HAVING score > 0 order by rel desc limit 10";
                break;
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, search);
            String[] words = search.split("\\W+");
            String booleanSearch = "";
            for (String word:words) {
                booleanSearch = booleanSearch.concat("+");
                booleanSearch = booleanSearch.concat(word);
                booleanSearch = booleanSearch.concat(" ");
            }
            stmt.setString(2, booleanSearch.trim());
            List<Ad> ads = createAdsFromResults(stmt.executeQuery());
            if (ads.size() > 0) return ads;
        } catch (SQLException e) {
            throw new RuntimeException("Error searching database for ads with title and description", e);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        String query = "DELETE FROM ads WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            return stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad from database", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("created_by"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getTimestamp("created")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
