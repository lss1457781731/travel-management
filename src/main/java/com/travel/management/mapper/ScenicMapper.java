package com.travel.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.management.entity.Scenic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScenicMapper extends BaseMapper<Scenic> {
    
    /**
     * 根据地区ID查询景点列表
     */
    @Select("SELECT s.* FROM scenic s " +
            "LEFT JOIN scenic_region sr ON s.id = sr.scenic_id " +
            "WHERE sr.region_id = #{regionId}")
    List<Scenic> selectByRegionId(@Param("regionId") Long regionId);

    /**
     * 根据景点ID查询关联的地区ID列表
     */
    @Select("SELECT region_id FROM scenic_region WHERE scenic_id = #{scenicId}")
    List<Long> selectRegionIds(@Param("scenicId") Long scenicId);
} 