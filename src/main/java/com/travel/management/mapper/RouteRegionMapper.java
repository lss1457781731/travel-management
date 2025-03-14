package com.travel.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.management.entity.RouteRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouteRegionMapper extends BaseMapper<RouteRegion> {
    // 继承BaseMapper后默认包含基础的CRUD方法

    // 自定义方法
    @Select("select region_id from route_region where route_id = #{routeId}")
    List<Long> selectRegionIdsByRouteId(Long routeId);

}