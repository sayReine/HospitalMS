package com.example.hospitalms.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private String token;
    private String email;
    private String role;
}