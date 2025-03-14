package com.travel.management.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("scenic")
public class Scenic {
    /**
     * 主键ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 景点名称
     */
    private String name;

    /**
     * 景点描述
     */
    private String description;

    /**
     * 开放时间
     */
    private LocalDateTime openTime;

    /**
     * 关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * 门票价格
     */
    private BigDecimal ticketPrice;

    /**
     * 景点图片
     */
    private String image;
    
    /**
     * 详细地址
     */
    private String address;

    /**
     * 状态 (0:禁用 1:启用)
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    /**
     * 关联的地区列表（非数据库字段）
     */
    @TableField(exist = false)
    private List<Long> regionsId;
    @TableField(exist = false)
    private List<Region> regions;

}