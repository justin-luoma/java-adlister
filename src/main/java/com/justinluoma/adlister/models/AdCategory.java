package com.justinluoma.adlister.models;

public class AdCategory {
    private Long adID;
    private Long categoryID;

    public AdCategory(Long adID, Long categoryID) {
        this.adID = adID;
        this.categoryID = categoryID;
    }

    public Long adID() {
        return adID;
    }

    public void adID(Long adID) {
        this.adID = adID;
    }

    public Long categoryID() {
        return categoryID;
    }

    public void categoryID(Long categoryID) {
        this.categoryID = categoryID;
    }
}
