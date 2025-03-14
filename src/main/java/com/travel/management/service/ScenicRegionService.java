package com.travel.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.management.entity.ScenicRegion;
import java.util.List;

public interface ScenicRegionService extends IService<ScenicRegion> {
    
    /**
     * 批量保存景点-地区关联关系
     */
    boolean saveBatch(List<ScenicRegion> relations);

    /**
     * 删除景点的所有地区关联
     */
    boolean removeByScenicId(Long scenicId);
} 