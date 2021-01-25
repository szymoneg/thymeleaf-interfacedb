package com.szymonbilinski.thymeleafdb.service;

import com.szymonbilinski.thymeleafdb.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
}
