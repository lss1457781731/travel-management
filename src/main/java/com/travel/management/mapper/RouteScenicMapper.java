package com.travel.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.management.entity.RouteScenic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouteScenicMapper extends BaseMapper<RouteScenic> {
    // 继承BaseMapper后默认包含基础的CRUD方法

    @Select("select scenic_id from route_scenic where route_id = #{routeId}")
    List<Long> selectScenicIdsByRouteId(Long routeId);
}