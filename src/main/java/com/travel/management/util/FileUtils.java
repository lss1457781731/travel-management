package com.travel.management.util;

import com.travel.management.config.FileUploadConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.UUID;

/**
 * 文件上传工具类
 */
public class FileUtils {

    /**
     * 上传文件
     *
     * @param file 文件
     * @param config 文件上传配置
     * @return 文件访问URL
     * @throws IOException IO异常
     */
    public static String upload(MultipartFile file, FileUploadConfig config) throws IOException {
        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new IOException("上传的文件不能为空");
        }

        // 检查文件大小
        if (file.getSize() > config.getMaxSize()) {
            throw new IOException("上传的文件大小超过限制");
        }

        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        
        // 检查文件类型
        String fileExtension = getFileExtension(originalFilename);
        if (!isAllowedFileType(fileExtension, config.getAllowTypes())) {
            throw new IOException("不支持的文件类型");
        }

        // 生成新的文件名
        String newFilename = generateUniqueFilename(fileExtension);
        
        // 创建年月日目录
        String datePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String uploadPath = config.getPath() + File.separator + datePath;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // 保存文件
        Path targetPath = Paths.get(uploadPath, newFilename);
        Files.copy(file.getInputStream(), targetPath);
        
        // 返回文件访问URL（完整的可访问URL，包含服务器地址）
        // 构建完整的URL，使用服务器地址 + context-path + 访问路径
        return "http://localhost:8081/api" + config.getAccessPath() + datePath + "/" + newFilename;
    }
    
    /**
     * 获取文件扩展名
     */
    private static String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }
    
    /**
     * 检查文件类型是否允许
     */
    private static boolean isAllowedFileType(String fileExtension, String[] allowTypes) {
        return Arrays.asList(allowTypes).contains(fileExtension);
    }
    
    /**
     * 生成唯一文件名
     */
    private static String generateUniqueFilename(String fileExtension) {
        return UUID.randomUUID().toString().replaceAll("-", "") + fileExtension;
    }
}