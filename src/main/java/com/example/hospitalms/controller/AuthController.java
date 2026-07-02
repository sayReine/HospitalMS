package com.example.hospitalms.controller;

import com.example.hospitalms.dto.AuthResponse;
import com.example.hospitalms.dto.LoginRequest;
import com.example.hospitalms.model.AppUser;
import com.example.hospitalms.repository.UserRepository;
import com.example.hospitalms.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Load standard user details framework
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        // Prepare extra claims map to satisfy Module 2 requirements (Username + Role)
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("username", user.getEmail());

        // Generate the token using your code's multi-parameter method signature
        String generatedToken = jwtService.generateToken(claims, userDetails);

        AuthResponse response = new AuthResponse();
        response.setMessage("Authentication successful");
        response.setToken(generatedToken);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());

        return ResponseEntity.ok(response);
    }
}