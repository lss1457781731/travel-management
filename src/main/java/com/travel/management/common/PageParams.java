package com.travel.management.common;

import lombok.Data;

@Data
public class PageParams {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String keyword;
    private String sortField;
    private String sortOrder;
} 