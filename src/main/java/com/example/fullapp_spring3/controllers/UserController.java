package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.dtos.UserDTO;
import com.example.fullapp_spring3.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public UserDTO findUser(@PathVariable("username") String username) {
        return userService.findUser(username);
    }

    @PutMapping("/")
    public ResponseEntity<UserDTO> updateCategory(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userService.deleteUser(id);
    }
}
