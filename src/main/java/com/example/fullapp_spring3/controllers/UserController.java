package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.models.User;
import com.example.fullapp_spring3.services.AuthService;
import com.example.fullapp_spring3.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userService.deleteUser(id);
    }
}
