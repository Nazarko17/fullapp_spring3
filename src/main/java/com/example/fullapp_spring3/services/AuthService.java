package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.UserDAO;
import com.example.fullapp_spring3.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .isEnabled(true)
                .build();
        userDAO.save(user);
        var jwtToken = jwtService.generateToken(user);
        return JwtResponse.builder()
                .token(jwtToken)
                .build();
    }

    public JwtResponse authenticate(JwtRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userDAO.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return JwtResponse.builder()
                .token(jwtToken)
                .build();
    }
}
