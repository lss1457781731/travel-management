package com.travel.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.management.entity.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RouteMapper extends BaseMapper<Route> {
    /**
     * 分页查询路线列表，包含关联的地区和景点信息
     *
     * @param page 分页参数
     * @param name 路线名称（可选）
     * @param regionId 地区ID（可选）
     * @param days 建议天数（可选）
     * @return 分页结果
     */
    IPage<Route> selectRoutePage(Page<Route> page,
                                @Param("name") String name,
                                @Param("regionId") Long regionId,
                                @Param("days") Integer days,
                                 @Param("suitableFor") String suitableFor,
                                 @Param("routeType") Integer routeType);
}