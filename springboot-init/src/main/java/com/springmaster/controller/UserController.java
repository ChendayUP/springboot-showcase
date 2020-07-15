package com.springmaster.controller;

import com.springmaster.pojo.User;
import com.springmaster.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/query")
    public User queryName(String name) {
        User user = userService.selectUserByName(name);
        return user;
    }

    @RequestMapping("/selectAllUser")
    public List<User> selectAllUser() {
        return userService.selectAllUser();
    }

    @RequestMapping("/insert")
    public List<User> insert(String name) {
        User user = userService.selectUserByName(name);
        if (user != null) {
            return null;
        }
        userService.insertService(name);
        return userService.selectAllUser();
    }

    @RequestMapping("/changemoney")
    public List<User> changemoney() {
        userService.changemoney();
        return userService.selectAllUser();
    }

    @RequestMapping("/delete")
    public String delete(int userId) {
        userService.deleteService(userId);
        return "OK";
    }
}
