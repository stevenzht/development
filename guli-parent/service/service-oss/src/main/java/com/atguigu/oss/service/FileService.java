package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author steven
 * @create 2023-09-24 9:27
 */
public interface FileService {
    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
