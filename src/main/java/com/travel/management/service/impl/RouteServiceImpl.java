package com.travel.management.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.travel.management.entity.*;
import com.travel.management.mapper.RouteMapper;
import com.travel.management.mapper.RouteRegionMapper;
import com.travel.management.mapper.RouteScenicMapper;
import com.travel.management.service.RegionService;
import com.travel.management.service.RouteService;
import com.travel.management.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {

    @Autowired
    private RouteRegionMapper routeRegionMapper;
    @Autowired
    private RegionService regionService;
    @Autowired
    private ScenicService scenicService;
    @Autowired
    private RouteScenicMapper routeScenicMapper;
    @Autowired
    private RouteMapper baseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Route createRoute(Route route, List<Long> regionIds) {
        route.setCreatedAt(LocalDateTime.now());
        route.setUpdatedAt(LocalDateTime.now());
        // 保存路线基本信息
        save(route);

        // 保存路线-地区关联
        regionIds.forEach(regionId -> {
            RouteRegion routeRegion = new RouteRegion();
            routeRegion.setRouteId(route.getId());
            routeRegion.setRegionId(regionId);
            routeRegion.setCreatedAt(LocalDateTime.now());
            routeRegionMapper.insert(routeRegion);
        });

        for (int i = 0; i < route.getScenics().size(); i++) {
            RouteScenic routeScenic = new RouteScenic();
            routeScenic.setRouteId(route.getId());
            routeScenic.setScenicId(route.getScenics().get(i).getId());
            routeScenic.setVisitOrder(i + 1);
            routeScenic.setCreatedAt(LocalDateTime.now());
            routeScenicMapper.insert(routeScenic);
        }

        return getRouteDetail(route.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Route updateRoute(Route route, List<Long> regionIds) {
        // 更新路线基本信息
        updateById(route);

        // 删除原有关联
        routeRegionMapper.delete(new QueryWrapper<RouteRegion>().eq("route_id", route.getId()));
        routeScenicMapper.delete(new QueryWrapper<RouteScenic>().eq("route_id", route.getId()));

        // 重新建立关联
        regionIds.forEach(regionId -> {
            RouteRegion routeRegion = new RouteRegion();
            routeRegion.setRouteId(route.getId());
            routeRegion.setRegionId(regionId);
            routeRegion.setCreatedAt(LocalDateTime.now());
            routeRegionMapper.insert(routeRegion);
        });

        for (int i = 0; i < route.getScenics().size(); i++) {
            RouteScenic routeScenic = new RouteScenic();
            routeScenic.setRouteId(route.getId());
            routeScenic.setScenicId(route.getScenics().get(i).getId());
            routeScenic.setVisitOrder(i + 1);
            routeScenic.setCreatedAt(LocalDateTime.now());
            routeScenicMapper.insert(routeScenic);
        }
        return getRouteDetail(route.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoute(Long id) {
        // 删除路线时会级联删除关联记录
        removeById(id);
    }

    @Override
    public Route getRouteDetail(Long id) {
        Route routeDetails = baseMapper.selectById(id);

        // 路线和地区
        List<Long> regionIds = routeRegionMapper.selectRegionIdsByRouteId(routeDetails.getId());
        List<Region> regions = regionService.listByIds(regionIds);
        routeDetails.setRegions(regions);
        // 路线和景点
        List<Long> scenicIds = routeScenicMapper.selectScenicIdsByRouteId(routeDetails.getId());
        routeDetails.setScenicIds(scenicIds);
        List<Scenic> scenics = scenicService.listByIds(scenicIds);
        routeDetails.setScenics(scenics);
        return routeDetails;
    }

    @Override
    public IPage<Route> getRoutePage(int page, int size, String name, Long regionId, Integer days,
                                     String suitableFor, Integer routeType) {
        IPage<Route> routeIPage = baseMapper.selectRoutePage(
                new Page<>(page, size),
                name,
                regionId,
                days,
                suitableFor,
                routeType
        );
        if (CollUtil.isEmpty(routeIPage.getRecords())) {
            return routeIPage;
        }
        List<Route> routes = routeIPage.getRecords();
        routes.forEach(route -> {
            // 路线和地区
            List<Long> regionIds = routeRegionMapper.selectRegionIdsByRouteId(route.getId());
            List<Region> regions = regionService.listByIds(regionIds);
            route.setRegions(regions);
            // 路线和景点
            List<Long> scenicIds = routeScenicMapper.selectScenicIdsByRouteId(route.getId());
            route.setScenicIds(scenicIds);
            List<Scenic> scenics = scenicService.listByIds(scenicIds);
            route.setScenics(scenics);
        });
        return routeIPage;
    }
}