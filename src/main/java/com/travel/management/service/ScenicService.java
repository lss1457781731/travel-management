package com.travel.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.travel.management.entity.Scenic;
import java.util.List;

public interface ScenicService extends IService<Scenic> {
    /**
     * 分页查询景点列表
     */
    IPage<Scenic> page(int current, int size, String name, Long regionId);

    /**
     * 更新景点状态
     */
    boolean updateStatus(Long id, Integer status);

    /**
     * 获取景点详情（包含地区信息）
     */
    Scenic getDetailById(Long id);

    /**
     * 根据地区ID查询景点列表
     */
    List<Scenic> getByRegionId(Long regionId);

    /**
     * 保存景点信息（包含地区关联）
     */
    boolean saveWithRegions(Scenic scenic, List<Long> regionIds);

    /**
     * 更新景点信息（包含地区关联）
     */
    boolean updateWithRegions(Scenic scenic, List<Long> regionIds);
} 