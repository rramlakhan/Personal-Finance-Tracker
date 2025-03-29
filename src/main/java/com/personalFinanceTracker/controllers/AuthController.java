package com.personalFinanceTracker.controllers;

import com.personalFinanceTracker.entities.User;
import com.personalFinanceTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }
}
