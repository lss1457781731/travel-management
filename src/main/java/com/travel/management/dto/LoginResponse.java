package com.travel.management.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private String nickname;
    private String avatar;
} 