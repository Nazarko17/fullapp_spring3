package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.models.JwtRequest;
import com.example.fullapp_spring3.models.JwtResponse;
import com.example.fullapp_spring3.models.RegisterRequest;
import com.example.fullapp_spring3.models.User;
import com.example.fullapp_spring3.services.AuthService;
import com.example.fullapp_spring3.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService service;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(
            @RequestBody JwtRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
