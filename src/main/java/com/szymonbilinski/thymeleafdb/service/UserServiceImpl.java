package com.szymonbilinski.thymeleafdb.service;

import com.szymonbilinski.thymeleafdb.domain.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    List<User> userList;
    final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        jdbcTemplate.execute("SELECT * FROM parkit.users");
        return null;
    }
}
