package com.travel.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.management.entity.Region;
import java.util.List;

public interface RegionService extends IService<Region> {
    // 添加地区
    void addRegion(Region region);
    
    // 更新地区
    void updateRegion(Region region);
    
    // 删除地区
    void deleteRegion(Long id);
    
    // 获取地区详情
    Region getRegionById(Long id);
    
    // 获取地区树形结构
    List<Region> getRegionTree();
    
    // 根据条件查询地区列表
    List<Region> getRegionsByCondition(String name, String level, String code);
    
    // 检查地区代码是否存在
    boolean isCodeExists(String code, Long excludeId);
    
    // 获取子地区列表
    List<Region> getChildRegions(Long parentId);
} 