package com.travel.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.management.entity.SysUser;
import com.travel.management.mapper.SysUserMapper;
import com.travel.management.service.SysUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public SysUser createUser(SysUser user) {
        // 检查用户名是否已存在
        if (checkUsernameExists(user.getUsername(), null)) {
            throw new RuntimeException("用户名已存在");
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // 保存用户
        save(user);
        
        return user;
    }

    @Override
    @Transactional
    public SysUser updateUser(SysUser user) {


        // 如果密码不为空，则更新密码
        if (StringUtils.hasText(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // 不更新密码
            user.setPassword(null);
        }

        // 更新用户
        updateById(user);

        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        removeById(id);
    }

    @Override
    public IPage<SysUser> getUserPage(int page, int size, String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        
        // 根据用户名模糊查询
        if (StringUtils.hasText(username)) {
            queryWrapper.like(SysUser::getUsername, username);
        }
        
        // 按创建时间降序排序
        queryWrapper.orderByDesc(SysUser::getCreateTime);

        return page(new Page<>(page, size), queryWrapper);
    }

    @Override
    public boolean checkUsernameExists(String username, Long excludeId) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        
        if (excludeId != null) {
            queryWrapper.ne(SysUser::getId, excludeId);
        }
        
        return count(queryWrapper) > 0;
    }
}