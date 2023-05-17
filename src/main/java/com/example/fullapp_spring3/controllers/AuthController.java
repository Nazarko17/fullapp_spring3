package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.dtos.UserDTO;
import com.example.fullapp_spring3.models.JwtRequest;
import com.example.fullapp_spring3.models.JwtResponse;
import com.example.fullapp_spring3.models.RegisterRequest;
import com.example.fullapp_spring3.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(
            @RequestBody JwtRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/current-user")
    public UserDTO findCurrentUser(Principal principal) {
        return authService.findCurrentUser(principal);
    }
}
