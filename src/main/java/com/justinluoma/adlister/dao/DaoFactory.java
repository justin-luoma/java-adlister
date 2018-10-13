package com.justinluoma.adlister.dao;

import com.google.gson.Gson;
import com.justinluoma.adlister.dao.interfaces.Ads;
import com.justinluoma.adlister.dao.interfaces.Users;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Config config = new Config();
    public static Gson gson = new Gson();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }
}
