package com.justinluoma.adlister.controllers.util;

import com.justinluoma.adlister.dao.DaoFactory;

public class ValidateAd {
    public static boolean validate(String title, String description, String[] categories) throws InvalidAdException {
        if (title == null || description == null || categories == null)
            throw new InvalidAdException(DaoFactory.gson.toJson(Json.gen(new String[] {"errors"}, "invalid data")));
        else if (title.length() < 5)
            throw new InvalidAdException(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "title"}, true, "title too short")));
        else if (description.length() < 15)
            throw new InvalidAdException(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "description"}, true, "description too short")));
        else if (categories.length < 1)
            throw new InvalidAdException(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "categories"}, true, "must be at least one category")));
        else if (DaoFactory.getAdsDao().testUniqueTitle(title))
            throw new InvalidAdException(DaoFactory.gson.toJson(Json.gen(new String[] {"errors", "title"}, true, "title must be unique")));
        else
            return true;
    }
}
