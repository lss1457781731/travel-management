package com.travel.management.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("route_region")
public class RouteRegion {
    /**
     * 关联ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 路线ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long routeId;

    /**
     * 地区ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long regionId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 关联的路线信息（非数据库字段）
     */
    @TableField(exist = false)
    private Route route;

    /**
     * 关联的地区信息（非数据库字段）
     */
    @TableField(exist = false)
    private Region region;
}