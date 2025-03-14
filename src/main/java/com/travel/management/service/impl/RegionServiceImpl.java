package com.travel.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.management.entity.Region;
import com.travel.management.entity.Scenic;
import com.travel.management.mapper.RegionMapper;
import com.travel.management.mapper.ScenicMapper;
import com.travel.management.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>  implements RegionService {
    
    @Autowired
    private RegionMapper regionMapper;
    
    @Override
    @Transactional
    public void addRegion(Region region) {
        // 检查地区代码是否已存在
        if (isCodeExists(region.getCode(), null)) {
            throw new RuntimeException("地区代码已存在");
        }
        regionMapper.insert(region);
    }
    
    @Override
    @Transactional
    public void updateRegion(Region region) {
        // 检查地区代码是否已存在（排除自身）
        if (isCodeExists(region.getCode(), region.getId())) {
            throw new RuntimeException("地区代码已存在");
        }
        regionMapper.update(region);
    }
    
    @Override
    @Transactional
    public void deleteRegion(Long id) {
        // 检查是否有子地区
        List<Region> children = regionMapper.selectByParentId(id);
        if (!children.isEmpty()) {
            throw new RuntimeException("请先删除子地区");
        }
        regionMapper.deleteById(id);
    }
    
    @Override
    public Region getRegionById(Long id) {
        return regionMapper.selectById(id);
    }
    
    @Override
    public List<Region> getRegionTree() {
        return regionMapper.selectAllWithChildren();
    }
    
    @Override
    public List<Region> getRegionsByCondition(String name, String level, String code) {
        return regionMapper.selectByCondition(name, level, code);
    }
    
    @Override
    public boolean isCodeExists(String code, Long excludeId) {
        return regionMapper.checkCodeExists(code, excludeId) > 0;
    }
    
    @Override
    public List<Region> getChildRegions(Long parentId) {
        return regionMapper.selectByParentId(parentId);
    }
} 