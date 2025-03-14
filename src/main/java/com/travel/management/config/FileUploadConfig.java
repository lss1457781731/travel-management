package com.travel.management.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * 文件上传配置类
 */
@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig implements WebMvcConfigurer {

    /**
     * 文件上传路径
     */
    private String path = System.getProperty("user.dir") + File.separator + "uploads";

    /**
     * 文件访问路径前缀
     */
    private String accessPathPattern = "/uploads/**";

    /**
     * 文件访问路径映射
     */
    private String accessPath = "/uploads/";

    /**
     * 允许上传的文件类型
     */
    private String[] allowTypes = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx"};

    /**
     * 最大文件大小，默认10MB
     */
    private long maxSize = 10 * 1024 * 1024;

    /**
     * 配置静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 创建上传目录
        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // 配置静态资源映射，使上传的文件可以通过URL访问
        registry.addResourceHandler(accessPathPattern)
                .addResourceLocations("file:" + path + File.separator);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAccessPathPattern() {
        return accessPathPattern;
    }

    public void setAccessPathPattern(String accessPathPattern) {
        this.accessPathPattern = accessPathPattern;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public String[] getAllowTypes() {
        return allowTypes;
    }

    public void setAllowTypes(String[] allowTypes) {
        this.allowTypes = allowTypes;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}