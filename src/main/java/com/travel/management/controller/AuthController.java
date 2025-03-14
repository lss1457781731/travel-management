package com.travel.management.controller;

import com.travel.management.common.Result;
import com.travel.management.dto.LoginRequest;
import com.travel.management.dto.LoginResponse;
import com.travel.management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/admin/login")
    public Result<LoginResponse> adminLogin(@Valid @RequestBody LoginRequest request) {
        return Result.success(authService.adminLogin(request));
    }

    @PostMapping("/user/login")
    public Result<LoginResponse> userLogin(@Valid @RequestBody LoginRequest request) {
        return Result.success(authService.userLogin(request));
    }
} 