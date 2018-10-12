package com.justinluoma.adlister.models;

public class User {
    private long id;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long id() {
        return id;
    }

    public void id(long id) {
        this.id = id;
    }

    public String username() {
        return username;
    }

    public void username(String username) {
        this.username = username;
    }

    public String email() {
        return email;
    }

    public void email(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void password(String password) {
        this.password = password;
    }
}
