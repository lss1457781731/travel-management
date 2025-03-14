package com.travel.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.travel.management.common.Result;
import com.travel.management.entity.Scenic;
import com.travel.management.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scenics")
public class ScenicController {

    @Autowired
    private ScenicService scenicService;

    /**
     * 添加景点
     */
    @PostMapping
    public Result<?> add(@RequestBody Scenic scenic) {
        boolean success = scenicService.saveWithRegions(scenic, scenic.getRegionsId());
        return success ? Result.success() : Result.error("添加失败");
    }

    /**
     * 更新景点
     */
    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Scenic scenic) {
        scenic.setId(id);
        boolean success = scenicService.updateWithRegions(scenic, scenic.getRegionsId());
        return success ? Result.success() : Result.error("更新失败");
    }

    /**
     * 删除景点
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        boolean success = scenicService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }

    /**
     * 获取景点详情
     */
    @GetMapping("/{id}")
    public Result<Scenic> getById(@PathVariable Long id) {
        Scenic scenic = scenicService.getDetailById(id);
        return Result.success(scenic);
    }

    /**
     * 分页查询景点列表
     */
    @GetMapping("/page")
    public Result<IPage<Scenic>> page(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long regionId) {
        IPage<Scenic> page = scenicService.page(current, size, name, regionId);
        return Result.success(page);
    }

    /**
     * 根据地区ID查询景点列表
     */
    @GetMapping("/region/{regionId}")
    public Result<List<Scenic>> getByRegionId(@PathVariable Long regionId) {
        List<Scenic> scenics = scenicService.getByRegionId(regionId);
        return Result.success(scenics);
    }
} 