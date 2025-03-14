package com.travel.management.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("route")
public class Route {
    /**
     * 路线ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 路线名称
     */
    private String name;

    /**
     * 建议天数
     */
    private Integer days;

    /**
     * 适合人群
     */
    private String suitableFor;

    /**
     * 适合时间
     */
    private String bestTime;

    /**
     * 建议人数
     */
    private Integer groupSize;

    /**
     * 合适车型
     */
    private String vehicleType;

    /**
     * 路线描述
     */
    private String description;

    private Integer routeType;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 关联的地区列表（非数据库字段）
     */
    @TableField(exist = false)
    private List<Region> regions;

    /**
     * 关联的景点列表（非数据库字段）
     */
    @TableField(exist = false)
    private List<Scenic> scenics;

    /**
     * 地区ID列表（用于接收前端数据）
     */
    @TableField(exist = false)
    private List<Long> regionIds;

    /**
     * 景点ID列表（用于接收前端数据）
     */
    @TableField(exist = false)
    private List<Long> scenicIds;
}