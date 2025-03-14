package com.travel.management.controller;

import com.travel.management.common.Result;
import com.travel.management.config.FileUploadConfig;
import com.travel.management.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 上传结果
     */
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            // 上传文件并获取访问URL
            String fileUrl = FileUtils.upload(file, fileUploadConfig);
            
            // 返回文件信息
            Map<String, Object> data = new HashMap<>();
            data.put("url", fileUrl);
            data.put("name", file.getOriginalFilename());
            data.put("size", file.getSize());
            
            return Result.success(data);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
    }
}