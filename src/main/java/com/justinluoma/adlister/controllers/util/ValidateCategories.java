package com.justinluoma.adlister.controllers.util;

import com.justinluoma.adlister.dao.DaoFactory;
import com.justinluoma.adlister.models.Category;

import java.util.List;
import java.util.stream.Collectors;

public class ValidateCategories {
    private static boolean validate(Long category) {
        List<Category> categories = DaoFactory.getCategoriesDao().all();
        List<Long> validCategories = categories.stream().map(Category::id).collect(Collectors.toList());
        return validCategories.contains(category);
    }

    public static boolean validate(String... category) {
        boolean valid = true;
        try {
            for(String c:category) {
                valid = validate(Long.valueOf(c)) && valid;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return valid;
    }
}
