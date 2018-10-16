package com.justinluoma.adlister.dao.interfaces;

import com.justinluoma.adlister.models.AdCategory;

import java.util.List;

public interface AdCategories {
    List<AdCategory> all();
    List<AdCategory> byCategoryID(Long categoryID);
    List<AdCategory> byAdID(Long adID);
    Boolean insert(Long adID, Long categoryID);
    Boolean delete(Long adID, Long categoryID);
}
