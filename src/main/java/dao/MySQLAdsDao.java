package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
import model.Ad;

public class MySQLAdsDao implements Ads {
    private Connection connection;

    MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to connect to database");
        }
    }

    @Override
    public List<Ad> all() {
        return getAdsFromDB();
    }

    @Override
    public Long insert(Ad ad) {
        try {
            PreparedStatement insertAd = this.connection.prepareStatement(
                    "INSERT INTO ads (user_id, title, description) " +
                            "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            insertAd.setLong(1, ad.getUserId());
            insertAd.setString(2, ad.getTitle());
            insertAd.setString(3, ad.getDescription());
            insertAd.executeUpdate();
            ResultSet rs = insertAd.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to insert new ad");
        }
        return null;
    }

    private Ad createAdFromDB(ResultSet rs) {
        try {
            return new Ad(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getString("title"),
                    rs.getString("description")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to create ad from db result");
        }
    }

    private List<Ad> getAdsFromDB() {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ads");
            List<Ad> ads = new ArrayList<>();
            while (rs.next()) {
                ads.add(createAdFromDB(rs));
            }
            return ads;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to get ads from db");
        }
    }
}
