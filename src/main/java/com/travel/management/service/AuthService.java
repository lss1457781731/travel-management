package com.travel.management.service;

import com.travel.management.dto.LoginRequest;
import com.travel.management.dto.LoginResponse;

public interface AuthService {
    // 管理员登录
    LoginResponse adminLogin(LoginRequest request);
    
    // 用户登录
    LoginResponse userLogin(LoginRequest request);
} 