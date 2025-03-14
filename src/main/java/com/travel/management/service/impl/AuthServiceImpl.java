package com.travel.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.travel.management.dto.LoginRequest;
import com.travel.management.dto.LoginResponse;
import com.travel.management.entity.SysUser;
import com.travel.management.mapper.SysUserMapper;
import com.travel.management.service.AuthService;
import com.travel.management.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse adminLogin(LoginRequest request) {
        // 查询管理员用户
        SysUser admin = userMapper.selectOne(
            new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, request.getUsername())
                .eq(SysUser::getRole, "admin")
        );

        if (admin == null) {
            throw new BadCredentialsException("管理员账号不存在");
        }

        // 验证密码
        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }

        // 检查账号状态
        if (admin.getStatus() != 1) {
            throw new BadCredentialsException("账号已被禁用");
        }

        LoginResponse loginResponse = createLoginResponse(admin);
        String token = loginResponse.getToken();
        admin.setToken(token);
        return loginResponse;
    }

    @Override
    public LoginResponse userLogin(LoginRequest request) {
        // 查询普通用户
        SysUser user = userMapper.selectOne(
            new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, request.getUsername())
                .eq(SysUser::getRole, "user")
        );

        if (user == null) {
            throw new BadCredentialsException("用户不存在");
        }

        // 验证密码
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }

        // 检查账号状态
        if (user.getStatus() != 1) {
            throw new BadCredentialsException("账号已被禁用");
        }
        LoginResponse loginResponse = createLoginResponse(user);
        String token = loginResponse.getToken();
        user.setToken(token);
        return loginResponse;
    }

    private LoginResponse createLoginResponse(SysUser user) {
        // 生成JWT令牌
        String token = jwtUtils.generateToken(user.getId());

        // 构建响应对象
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setNickname(user.getNickname());
        response.setAvatar(user.getAvatar());

        return response;
    }
} 