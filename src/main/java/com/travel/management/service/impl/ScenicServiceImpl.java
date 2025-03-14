package com.travel.management.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.management.entity.Region;
import com.travel.management.entity.Scenic;
import com.travel.management.entity.ScenicRegion;
import com.travel.management.mapper.ScenicMapper;
import com.travel.management.service.RegionService;
import com.travel.management.service.ScenicRegionService;
import com.travel.management.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScenicServiceImpl extends ServiceImpl<ScenicMapper, Scenic> implements ScenicService {

    @Autowired
    private RegionService regionService;
    @Autowired
    private ScenicRegionService scenicRegionService;

    @Override
    public IPage<Scenic> page(int current, int size, String name, Long regionId) {
        Page<Scenic> page = new Page<>(current, size);
        LambdaQueryWrapper<Scenic> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(name)) {
            wrapper.like(Scenic::getName, name);
        }
        if (regionId != null) {
            wrapper.inSql(Scenic::getId, "SELECT scenic_id FROM scenic_region WHERE region_id = " + regionId);
        }
        wrapper.orderByDesc(Scenic::getCreateTime);

        Page<Scenic> scenicPage = this.page(page, wrapper);
        if (CollUtil.isEmpty(scenicPage.getRecords())) {
            return scenicPage;
        }
        scenicPage.getRecords().forEach(scenic -> {
            List<Long> regionIds = baseMapper.selectRegionIds(scenic.getId());
            if (!regionIds.isEmpty()) {
                List<Region> regions = regionService.listByIds(regionIds);
                scenic.setRegions(regions);
            }
        });
        return scenicPage;

    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        Scenic scenic = new Scenic();
        scenic.setId(id);
        scenic.setStatus(status);
        return this.updateById(scenic);
    }

    @Override
    public Scenic getDetailById(Long id) {
        Scenic scenic = this.getById(id);
        if (scenic != null) {
            List<Long> regionIds = baseMapper.selectRegionIds(id);
            if (!regionIds.isEmpty()) {
                List<Region> regions = regionService.listByIds(regionIds);
                scenic.setRegions(regions);
            }
        }
        return scenic;
    }

    @Override
    public List<Scenic> getByRegionId(Long regionId) {
        return baseMapper.selectByRegionId(regionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveWithRegions(Scenic scenic, List<Long> regionIds) {
        // 设置初始状态和时间
        scenic.setStatus(1);
        scenic.setCreateTime(LocalDateTime.now());
        scenic.setUpdateTime(LocalDateTime.now());
        
        // 保存景点基本信息
        boolean success = this.save(scenic);
        
        // 保存景点-地区关联关系
        if (success && regionIds != null && !regionIds.isEmpty()) {
            List<ScenicRegion> relations = regionIds.stream().map(regionId -> {
                ScenicRegion relation = new ScenicRegion();
                relation.setScenicId(scenic.getId());
                relation.setRegionId(regionId);
                relation.setCreateTime(LocalDateTime.now());
                return relation;
            }).collect(Collectors.toList());
            scenicRegionService.saveBatch(relations);
        }
        
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateWithRegions(Scenic scenic, List<Long> regionIds) {
        scenic.setUpdateTime(LocalDateTime.now());
        
        // 更新景点基本信息
        boolean success = this.updateById(scenic);
        
        if (success && regionIds != null) {
            // 删除原有关联关系
            // TODO: 需要实现 ScenicRegionService 的删除方法
            
            // 重新建立关联关系
            if (!regionIds.isEmpty()) {
                List<ScenicRegion> relations = regionIds.stream().map(regionId -> {
                    ScenicRegion relation = new ScenicRegion();
                    relation.setScenicId(scenic.getId());
                    relation.setRegionId(regionId);
                    relation.setCreateTime(LocalDateTime.now());
                    return relation;
                }).collect(Collectors.toList());
                
                // 批量保存新的关联关系
                // TODO: 需要实现 ScenicRegionService 的批量保存方法
            }
        }
        
        return success;
    }
}