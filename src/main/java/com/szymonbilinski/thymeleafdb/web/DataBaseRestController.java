package com.szymonbilinski.thymeleafdb.web;

import com.szymonbilinski.thymeleafdb.domain.entity.User;
import com.szymonbilinski.thymeleafdb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


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
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user/edit/{userId}")
    public String getSingleUserData(@PathVariable("userId") long userId, Model model){
        model.addAttribute("idUser",userService.getUserById(userId));
        model.addAttribute("oldPerson",userId);
        model.addAttribute("newUser",new User());
        userService.getAllUsers();
        return "editUser";
    }

    @PostMapping("/user/edit/{userId}")
    public String postEditUser(@PathVariable("userId") long userId, @ModelAttribute("idUser") User user){
        System.out.println(user);
        userService.editUser(user,userId);
        return "redirect:/users";
    }

    @GetMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId){
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    @GetMapping("/user/add")
    public String addNewUserForm(Model model){
        model.addAttribute("newUser",new User());
        return "addForm";
    }

    @PostMapping("/user/add")
    public String addNewUserSubmit(@ModelAttribute("newUser") User user) throws SQLException {
        userService.addUser(user);
        return "redirect:/users";
    }
}
