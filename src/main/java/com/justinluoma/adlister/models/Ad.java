package com.justinluoma.adlister.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ad implements Comparable<Ad> {
    private long id;
    private long createdBy;
    private String title;
    private String description;
    private LocalDateTime created;
    private List<String> categories;
    private List<Integer> categoryIDs;

    public Ad() {
    }

    public Ad(long id, long createdBy, String title, String description,
              Timestamp created, List<String> categories) {
        this.id = id;
        this.createdBy = createdBy;
        this.title = title;
        this.description = description;
        this.created = created.toLocalDateTime();
        this.categories = categories;
        Collections.sort(this.categories);
    }

//    public Ad(long createdBy, String title, String description) {
//        this.createdBy = createdBy;
//        this.title = title;
//        this.description = description;
//        this.created = null;
//        this.id = 0;
//    }

//    public Ad(long id, long createdBy, String title, String description,
//              Timestamp created, List<String> categories, List<Integer> categoryIDs) {
//        this.id = id;
//        this.createdBy = createdBy;
//        this.title = title;
//        this.description = description;
//        this.created = created.toLocalDateTime();
//        this.categories = categories;
//        this.categoryIDs = categoryIDs;
//        Collections.sort(this.categories);
//        Collections.sort(this.categories);
//    }

//    public Ad(long createdBy, String title, String description, String... categories) {
//        this.createdBy = createdBy;
//        this.title = title;
//        this.description = description;
//        this.categories = Arrays.asList(categories);
//        Collections.sort(this.categories);
//        this.created = null;
//        this.id = 0;
//    }

//    Temp
//    public Ad(long id, long createdBy, String title, String description,
//              Timestamp created) {
//        this.id = id;
//        this.createdBy = createdBy;
//        this.title = title;
//        this.description = description;
//        this.created = created.toLocalDateTime();
//    }


    public long id() {
        return id;
    }

    public void id(long id) {
        this.id = id;
    }

    public long createdBy() {
        return createdBy;
    }

    public void createdBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public String title() {
        return title;
    }

    public void title(String title) {
        this.title = title;
    }

    public String description() {
        return description;
    }

    public void description(String description) {
        this.description = description;
    }

    public LocalDateTime created() {
        return created;
    }

    public void created(LocalDateTime created) {
        this.created = created;
    }

    public List<String> categories() {
        return this.categories;
    }

    public void categories(List<String> categories) {
        this.categories = categories;
        Collections.sort(this.categories);
    }

    public void categories(String... categories) {
        this.categories = Arrays.asList(categories);
        Collections.sort(this.categories);
    }

    public List<Integer> categoryIDs() {
        return categoryIDs;
    }

    public void categoryIDs(List<Integer> categoryIDs) {
        this.categoryIDs = categoryIDs;
    }

    public void categoryIDs(Integer... categoryIDs) {
        this.categoryIDs = Arrays.asList(categoryIDs);
        Collections.sort(this.categoryIDs);
    }

    @Override
    public int compareTo(Ad ad) {
        if (created == null || ad.created() == null)
            return 0;
        return created().compareTo(ad.created());
    }
}
