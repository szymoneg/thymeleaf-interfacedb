package com.szymonbilinski.thymeleafdb.web;

import com.szymonbilinski.thymeleafdb.domain.entity.User;
import com.szymonbilinski.thymeleafdb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class DataBaseRestController {
    final UserService userService;

    public DataBaseRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String get(){
        return "serverTest";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        User user1 = User.builder()
                .id(1)
                .firstname("Szymon")
                .lastname("Biliński")
                .build();
        User user2 = User.builder()
                .id(2)
                .firstname("Przemek")
                .lastname("Kuca")
                .build();

        List<User> userList = Arrays.asList(user1,user2);

        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/user/edit/{userId}")
    public String getSingleUserData(@PathVariable("userId") int userId, Model model){
        model.addAttribute("idUser",userId);
        userService.getAllUsers();
        return "editUser";
    }
}
