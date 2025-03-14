package com.travel.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.travel.management.entity.Route;

import java.util.List;

public interface RouteService extends IService<Route> {
    /**
     * 创建路线
     *
     * @param route 路线信息
     * @param regionIds 关联的地区ID列
     * @return 创建后的路线信息
     */
    Route createRoute(Route route, List<Long> regionIds);

    /**
     * 更新路线
     *
     * @param route 路线信息
     * @param regionIds 关联的地区ID列表
     * @param scenicIds 关联的景点ID列表（按游览顺序排序）
     * @return 更新后的路线信息
     */
    Route updateRoute(Route route, List<Long> regionIds);

    /**
     * 删除路线
     *
     * @param id 路线ID
     */
    void deleteRoute(Long id);

    /**
     * 获取路线详情
     *
     * @param id 路线ID
     * @return 路线详情，包含关联的地区和景点信息
     */
    Route getRouteDetail(Long id);

    /**
     * 分页查询路线列表
     *
     * @param page 页码
     * @param size 每页大小
     * @param name 路线名称（可选）
     * @param regionId 地区ID（可选）
     * @param days 建议天数（可选）
     * @return 分页结果
     */
    IPage<Route> getRoutePage(int page, int size, String name, Long regionId, Integer days, String suitableFor, Integer routeType);
}