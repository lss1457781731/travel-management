package com.travel.management.controller;

import com.travel.management.common.Result;
import com.travel.management.entity.Region;
import com.travel.management.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    
    @Autowired
    private RegionService regionService;
    
    @PostMapping
    public Result<?> addRegion(@Valid @RequestBody Region region) {
        regionService.addRegion(region);
        return Result.success();
    }
    
    @PutMapping("/{id}")
    public Result<?> updateRegion(@PathVariable Long id, @Valid @RequestBody Region region) {
        region.setId(id);
        regionService.updateRegion(region);
        return Result.success();
    }
    
    @DeleteMapping("/{id}")
    public Result<?> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return Result.success();
    }
    
    @GetMapping("/{id}")
    public Result<Region> getRegion(@PathVariable Long id) {
        return Result.success(regionService.getRegionById(id));
    }
    
    @GetMapping("/tree")
    public Result<List<Region>> getRegionTree() {
        return Result.success(regionService.getRegionTree());
    }
    
    @GetMapping("/list")
    public Result<List<Region>> getRegionList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String code) {
        return Result.success(regionService.getRegionsByCondition(name, level, code));
    }
    
    @GetMapping("/children/{parentId}")
    public Result<List<Region>> getChildRegions(@PathVariable Long parentId) {
        return Result.success(regionService.getChildRegions(parentId));
    }
    
    @GetMapping("/check-code")
    public Result<Boolean> checkCode(
            @RequestParam String code,
            @RequestParam(required = false) Long excludeId) {
        return Result.success(regionService.isCodeExists(code, excludeId));
    }
} 