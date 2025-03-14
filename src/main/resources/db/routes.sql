
-- 路线表
CREATE TABLE route (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '路线ID',
    name VARCHAR(100) NOT NULL COMMENT '路线名称',
    days INT NOT NULL COMMENT '建议天数',
    suitable_for VARCHAR(100) NOT NULL COMMENT '适合人群，多个用、分隔',
    best_time VARCHAR(100) COMMENT '适合时间',
    group_size INT NOT NULL COMMENT '建议人数',
    vehicle_type VARCHAR(50) COMMENT '合适车型',
    description TEXT COMMENT '路线描述',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_name (name)
) COMMENT '旅游路线表';

-- 路线地区关联表
CREATE TABLE route_region (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '关联ID',
    route_id BIGINT NOT NULL COMMENT '路线ID',
    region_id BIGINT NOT NULL COMMENT '地区ID',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_route_region (route_id, region_id),
    INDEX idx_region_id (region_id),
    FOREIGN KEY (route_id) REFERENCES route(id) ON DELETE CASCADE,
    FOREIGN KEY (region_id) REFERENCES region(id) ON DELETE CASCADE
) COMMENT '路线地区关联表';

-- 路线景点关联表
CREATE TABLE route_scenic (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '关联ID',
    route_id BIGINT NOT NULL COMMENT '路线ID',
    scenic_id BIGINT NOT NULL COMMENT '景点ID',
    visit_order INT NOT NULL COMMENT '游览顺序',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_route_scenic_order (route_id, visit_order),
    INDEX idx_scenic_id (scenic_id),
    FOREIGN KEY (route_id) REFERENCES route(id) ON DELETE CASCADE,
    FOREIGN KEY (scenic_id) REFERENCES scenic(id) ON DELETE CASCADE
) COMMENT '路线景点关联表';