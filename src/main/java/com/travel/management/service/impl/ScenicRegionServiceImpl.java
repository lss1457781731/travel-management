package com.travel.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.management.entity.ScenicRegion;
import com.travel.management.mapper.ScenicRegionMapper;
import com.travel.management.service.ScenicRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScenicRegionServiceImpl extends ServiceImpl<ScenicRegionMapper, ScenicRegion> implements ScenicRegionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(List<ScenicRegion> relations) {
        return super.saveBatch(relations);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByScenicId(Long scenicId) {
        return baseMapper.deleteByScenicId(scenicId) > 0;
    }
} 