package com.travel.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.management.entity.ScenicRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface ScenicRegionMapper extends BaseMapper<ScenicRegion> {
    
    /**
     * 删除景点的所有地区关联
     */
    @Delete("DELETE FROM scenic_region WHERE scenic_id = #{scenicId}")
    int deleteByScenicId(@Param("scenicId") Long scenicId);
} 