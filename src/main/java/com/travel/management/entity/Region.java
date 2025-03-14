package com.travel.management.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Region {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;
    private String name;
    private String level;
    private String code;
    private String description;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableField(exist = false)
    // 非数据库字段
    private List<Region> children;
    @TableField(exist = false)
    private String parentName;
} 