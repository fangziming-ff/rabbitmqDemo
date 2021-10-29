package com.fangming.rabbitmqdemo.vod;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ming
 * @date 2021/10/29 13:58
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);
}

