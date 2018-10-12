package com.justinluoma.adlister.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Ad implements Comparable<Ad> {
    private long id;
    private long createdBy;
    private String title;
    private String description;
    private LocalDateTime created;

    public Ad(long id, long createdBy, String title, String description,
              Timestamp created) {
        this.id = id;
        this.createdBy = createdBy;
        this.title = title;
        this.description = description;
        this.created = created.toLocalDateTime();
    }

    public Ad(long createdBy, String title, String description) {
        this.createdBy = createdBy;
        this.title = title;
        this.description = description;
        this.created = null;
        this.id = 0;
    }

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

    @Override
    public int compareTo(Ad ad) {
        if (created == null || ad.created() == null)
            return 0;
        return created().compareTo(ad.created());
    }
}
