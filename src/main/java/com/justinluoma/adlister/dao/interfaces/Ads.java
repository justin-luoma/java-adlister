package com.justinluoma.adlister.dao.interfaces;

import com.justinluoma.adlister.models.Ad;

import java.sql.SQLException;
import java.util.List;

public interface Ads {
    List<Ad> all();
    List<Ad> all(Long userID);
    List<Ad> users(Long user_id);
    List<Ad> search(String search, int type);
    List<Ad> getByCategory(Long categoryID);
    Ad getFromID(Long id) throws SQLException;
    Long insert(Long createdBy, String title, String description);
    Boolean delete(Long userID, Long adID);
    Boolean testUniqueTitle(String title);
    void update(Long adID, Ad newAd);
    void update(Long adID, String title, String description);
    void updateTitle(Long adID, String title);
    void updateDescription(Long adID, String description);
    void updateCategories(Long adId, List<Long> catsOld, List<Long> catsNew);
}
