package com.justinluoma.adlister.dao.interfaces;

import com.justinluoma.adlister.models.User;

import java.sql.SQLException;

public interface Users {
    User findByUsername(String username);
    User findByEmail(String email);
    User getByID(long id);
    Long insert(User user) throws SQLException;
    void updateUsername(Long id, String username) throws SQLException;
    void updateEmail(Long id, String email) throws SQLException;
    void updatePassword(Long id, String hash) throws SQLException;
}
