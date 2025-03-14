package com.travel.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.travel.management.common.Result;
import com.travel.management.entity.Route;
import com.travel.management.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    /**
     * 创建路线
     */
    @PostMapping
    public Result<Route> createRoute(@RequestBody Route route) {
        return Result.success(routeService.createRoute(route, route.getRegionIds()));
    }

    /**
     * 更新路线
     */
    @PutMapping("/{id}")
    public Result<Route> updateRoute(@PathVariable Long id,
                                   @RequestBody Route route) {
        route.setId(id);
        return Result.success(routeService.updateRoute(route, route.getRegionIds()));
    }

    /**
     * 删除路线
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return Result.success();
    }

    /**
     * 获取路线详情
     */
    @GetMapping("/{id}")
    public Result<Route> getRouteDetail(@PathVariable Long id) {
        return Result.success(routeService.getRouteDetail(id));
    }

    /**
     * 分页查询路线列表
     */
    @GetMapping
    public Result<IPage<Route>> getRoutePage(@RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) Long regionId,
                                           @RequestParam(required = false) Integer days,
                                           @RequestParam(required = false) String suitableFor,
                                             @RequestParam(required = true) Integer routeType ) {
        return Result.success(routeService.getRoutePage(page, size, name, regionId, days, suitableFor, routeType));
    }
}