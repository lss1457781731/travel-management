package com.travel.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.management.entity.Region;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RegionMapper extends BaseMapper<Region> {
    // 插入地区
    int insert(Region region);
    
    // 更新地区
    int update(Region region);
    
    // 删除地区
    int deleteById(Long id);
    
    // 根据ID查询地区
    Region selectById(Long id);
    
    // 查询所有顶级地区
    List<Region> selectTopRegions();
    
    // 根据父ID查询子地区
    List<Region> selectByParentId(Long parentId);
    
    // 根据条件查询地区列表
    List<Region> selectByCondition(@Param("name") String name, 
                                 @Param("level") String level, 
                                 @Param("code") String code);
    
    // 检查地区代码是否存在
    int checkCodeExists(@Param("code") String code, @Param("excludeId") Long excludeId);
    
    // 获取地区树形结构
    List<Region> selectAllWithChildren();
} 