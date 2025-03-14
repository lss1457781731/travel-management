package com.travel.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.travel.management.common.Result;
import com.travel.management.entity.SysUser;
import com.travel.management.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 创建用户
     */
    @PostMapping
    public Result<SysUser> createUser(@RequestBody SysUser user) {
        return Result.success(sysUserService.createUser(user));
    }

    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    public Result<SysUser> updateUser(@PathVariable Long id, @RequestBody SysUser user) {
        user.setId(id);
        return Result.success(sysUserService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        sysUserService.deleteUser(id);
        return Result.success();
    }

    /**
     * 分页查询用户列表
     */
    @GetMapping("/page")
    public Result<IPage<SysUser>> getUserPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String username) {
        return Result.success(sysUserService.getUserPage(page, size, username));
    }
}