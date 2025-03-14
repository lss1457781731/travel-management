package com.travel.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.management.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    /**
     * 创建用户
     */
    SysUser createUser(SysUser user);

    /**
     * 更新用户
     */
    SysUser updateUser(SysUser user);

    /**
     * 删除用户
     */
    void deleteUser(Long id);

    /**
     * 分页查询用户列表
     */
    IPage<SysUser> getUserPage(int page, int size, String username);

    /**
     * 检查用户名是否存在
     */
    boolean checkUsernameExists(String username, Long excludeId);
}