package com.fangming.rabbitmqdemo.controller;

import com.fangming.rabbitmqdemo.vod.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ming
 * @date 2021/10/29 13:56
 */
@RestController
@RequestMapping("/vod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public String uploadAlyiVideo(MultipartFile file) {
//返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return videoId;
    }
}
