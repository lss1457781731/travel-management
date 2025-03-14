package com.travel.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String userName;
    private Long scenicId;
    private Long routeId;
    private Integer type;
    private String content;
    private Integer rating;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
  
} 