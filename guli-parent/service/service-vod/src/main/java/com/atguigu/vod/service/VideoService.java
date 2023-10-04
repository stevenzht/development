package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author steven
 * @create 2023-09-25 10:16
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);
}
