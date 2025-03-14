package com.travel.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScenicRegion {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long scenicId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long regionId;
    private LocalDateTime createTime;
} 