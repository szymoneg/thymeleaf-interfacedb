package com.szymonbilinski.thymeleafdb.service;

import com.szymonbilinski.thymeleafdb.domain.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void editUser(User user,long userId);

    void deleteUser(long userId);

    void addUser(User user) throws SQLException;
}
