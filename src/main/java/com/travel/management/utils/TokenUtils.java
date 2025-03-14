package com.travel.management.utils;

import com.travel.management.entity.SysUser;
import com.travel.management.service.SysUserService;
import com.travel.management.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenUtils {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前请求的token
     */
    private String getToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    /**
     * 从token中解析用户ID
     */
    private Long getUserIdFromToken() {
        String token = getToken();
        if (token == null) {
            return null;
        }
        Claims claims = jwtUtils.getClaimsByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims)) {
            return null;
        }
        return Long.parseLong(claims.getSubject());
    }

    /**
     * 获取当前登录用户信息
     */
    public SysUser getCurrentUser() {
        Long userId = getUserIdFromToken();
        if (userId == null) {
            return null;
        }
        return sysUserService.getById(userId);
    }
}