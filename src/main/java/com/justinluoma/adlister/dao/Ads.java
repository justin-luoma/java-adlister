package com.justinluoma.adlister.dao;

import com.justinluoma.adlister.models.Ad;

import java.sql.SQLException;
import java.util.List;

public interface Ads {
    List<Ad> all();
    List<Ad> users(Long user_id);
    Ad getFromID(Long id) throws SQLException;
    Long insert(Ad ad);
    List<Ad> search(String search, int type);
    Boolean delete(Long id);
}
