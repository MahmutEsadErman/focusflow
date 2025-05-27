package org.focusflow.controller;

import org.focusflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String login() {
        userService.registerUser("test@test.de", "Password123!", "firstName", "LastName");
        return "User registered";
    }
}
